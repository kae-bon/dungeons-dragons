<template>
  <div class="container">
    <form
      @submit.prevent="addClass()"
      class="d-flex flex-column align-items-center"
    >
      <div class="mb-3 input">
        <label for="classSelect" class="form-label">choose class</label>
        <select
          required
          id="classSelect"
          class="form-select"
          aria-label="select class"
          v-model="classInfo.characterClass"
        >
          <option selected disabled>---</option>
          <option
            v-for="cl in classes"
            :key="cl.className"
            :disabled="doesCharacterHaveClass(cl.className)"
          >
            {{ cl.className }}
          </option>
        </select>
        <p class="form-text">already registered classes are disabled</p>
      </div>
      <div v-if="classInfo.characterClass != ''" class="input">
        <div class="mb-3">
          <label for="subclassSelect" class="form-label">choose subclass</label>
          <select
            required
            id="subclassSelect"
            class="form-select"
            aria-label="select class"
            v-model="classInfo.subclass"
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
            v-model="classInfo.classLevel"
          />
          <p class="form-text">class level must be between 1 and 20</p>
        </div>
      </div>
      <button type="submit" class="btn btn-primary">add class</button>
    </form>
  </div>
</template>

<script>
import ClassService from "../services/ClassService";
import CharacterService from "../services/CharacterService";

export default {
  props: ["character", "characterClasses"],
  data() {
    return {
      classes: [],
      classInfo: {
        characterClass: "",
        subclass: "",
        classLevel: 1,
      },
    };
  },
  computed: {
    subclasses() {
      return this.classes.find(
        (c) => c.className == this.classInfo.characterClass
      ).subclasses;
    },
  },
  created() {
    ClassService.getAllClassesAndSubclasses()
      .then((response) => {
        this.classes = response.data;
      })
      .catch((error) => {
        alert("Could not connect to database. Please try again later.");
      });
  },
  methods: {
    doesCharacterHaveClass(cl) {
      return this.characterClasses.includes(cl);
    },
    addClass() {
      if (this.classInfo.classLevel + this.character.currentLevel > 20) {
        this.$emit("warning");
        return;
      }

      CharacterService.addClassSubclassToCharacter(
        this.character.id,
        this.classInfo
      )
        .then(() => {
          CharacterService.getAllCharacters()
            .then((response) => {
              this.$store.commit("SET_CHARACTERS", response.data);
              this.$router.push({
                name: "manage-classes",
                params: { name: this.character.name },
              });
            })
            .catch((error) => {
              alert("Could not connect to database. Please try again later.");
            });
        })
        .catch((error) => {
          alert(
            "There was an issue adding this class. Please try again later."
          );
        });
    },
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
