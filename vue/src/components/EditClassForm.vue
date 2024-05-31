<template>
  <div class="container">
    <form
      @submit.prevent="addClass()"
      class="d-flex flex-column align-items-center"
    >
      <div class="mb-3">
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
      <div class="mb-3">
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

      <button type="submit" class="btn btn-primary">add class</button>
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
  created() {
    ClassService.getSubclassesByClassName(this.class)
      .then((response) => {
        this.subclasses = response.data;
      })
      .catch((error) => {
        alert("Could not connect to database. Please try again later.");
      });
    console.log(typeof this.character.classesSubclasses);
    let currentClass = this.character.classesSubclasses.find(
      (charClass) => charClass.characterClass === this.class
    );
    this.editedCharacterClassSubclass.subclass = currentClass.subclass;
    console.log(currentClass.subclass);
    this.editedCharacterClassSubclass.classLevel = currentClass.classLevel;
    console.log(currentClass.classLevel);
  },
};
</script>

<style scoped>
.input {
  width: 100%;
}

@media screen and (min-width: 750px) {
  .input {
    width: 50%;
  }
}
</style>
