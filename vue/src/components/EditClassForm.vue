<template>
  <div class="container">
    <form
      @submit.prevent="editClass()"
      class="d-flex flex-column align-items-center"
    >
      <div class="mb-3 input">
        <label for="subclassSelect" class="form-label">choose subclass</label>
        <select
          required
          id="subclassSelect"
          class="form-select"
          aria-label="select class"
          v-model="editedCharacterClassSubclass.subclass"
        >
          <option selected disabled>---</option>
          <option v-for="subclass in subclasses" :key="subclass">
            {{ subclass }}
          </option>
        </select>
      </div>
      <div class="mb-3 input">
        <label for="level" class="form-label">class level</label>
        <input
          required
          type="number"
          class="form-control"
          min="1"
          max="20"
          id="level"
          v-model="editedCharacterClassSubclass.classLevel"
        />
        <p class="form-text">class level must be between 1 and 20</p>
      </div>

      <button
        v-if="disableSubmitButton"
        type="submit"
        class="btn btn-primary"
        disabled
      >
        add class
      </button>
      <button v-else type="submit" class="btn btn-primary">add class</button>
    </form>
  </div>
</template>

<script>
// TODO: CHANGE CLASS FROM STRING TO OBJECT
import ClassService from "../services/ClassService";
import CharacterService from "../services/CharacterService";

export default {
  props: ["character", "class"],
  data() {
    return {
      subclasses: [],
      editedCharacterClassSubclass: {
        characterClass: this.class,
        subclass: "",
        classLevel: 0,
      },
    };
  },
  methods: {
    // editClass() {
    //   if (this.editedCharacterClassSubclass.subclass == '') {
    //     this.editedCharacterClassSubclass.subclass == this.character.subclass
    //   }
    //   if (this.editedCharacterClassSubclass.classLevel < 0 ||)
    // }
    getCurrentClass() {
      let currentClass = this.character.classesSubclasses.find(
        (charClass) => charClass.characterClass === this.class
      );
      return currentClass;
    },
  },
  computed: {
    disableSubmitButton() {
      const currentClass = this.getCurrentClass();
      const currentLevel = currentClass.classLevel;
      const currentSubclass = currentClass.subclass;
      console.log(currentSubclass);
      console.log(this.editedCharacterClassSubclass.subclass);
      console.log(currentLevel);
      console.log(this.editedCharacterClassSubclass.currentLevel);
      console.log(currentLevel == this.editedCharacterClassSubclass.classLevel);
      if (
        currentSubclass == this.editedCharacterClassSubclass.subclass &&
        currentLevel == this.editedCharacterClassSubclass.classLevel
      ) {
        return true;
      }
      return false;
    },
  },
  created() {
    ClassService.getSubclassesByClassName(this.class)
      .then((response) => {
        this.subclasses = response.data;
      })
      .catch((error) => {
        alert("Could not connect to database. Please try again later.");
      });

    let currentClass = this.getCurrentClass();
    this.editedCharacterClassSubclass.subclass = currentClass.subclass;
    this.editedCharacterClassSubclass.classLevel = currentClass.classLevel;
  },
};
</script>

<style scoped>
.input {
  width: 100%;
}

@media screen and (min-width: 750px) {
  .input {
    width: 50% !important;
  }
}
</style>
