<template>
  <div class="container">
    <div
      class="alert alert-warning alert-dismissible fade show"
      v-if="showAlert"
      role="alert"
    >
      <strong>Uh Oh!</strong> character level cannot exceed 20!
      <button
        @click="dismissAlert"
        type="button"
        class="btn-close"
        data-bs-dismiss="alert"
        aria-label="Close"
      ></button>
    </div>
    <ul class="nav nav-pills mb-3">
      <li>
        <router-link
          class="nav-link"
          v-bind:to="{
            name: 'manage-classes',
            params: { name: character.name },
          }"
          >back to {{ character.name }}'s classes</router-link
        >
      </li>
    </ul>
    <h1 class="mb-3">Add New Class</h1>
    <AddClassForm
      @warning="showAlert = true"
      :character="character"
      :characterClasses="characterClasses"
    />
  </div>
</template>

<script>
import AddClassForm from "../components/AddClassForm.vue";
import CharacterService from "../services/CharacterService";
import ClassService from "../services/ClassService";

export default {
  components: { AddClassForm },
  data() {
    return {
      character: {},
      characterClasses: [],
      showAlert: false,
    };
  },
  methods: {
    dismissAlert() {
      this.showAlert = false;
    },
  },
  created() {
    CharacterService.getAllCharacters().then((response) => {
      this.$store.commit("SET_CHARACTERS", response.data);
      this.character = this.character = this.$store.state.characters.find(
        (c) => c.name == this.$route.params.name
      );
      ClassService.getAllClassesAndSubclasses().then((response) => {
        this.$store.commit("SET_CLASSES", response.data);
        for (let i = 0; i < this.character.classesSubclasses.length; i++) {
          this.characterClasses.push(
            this.character.classesSubclasses[i].characterClass
          );
        }
      });
    });
  },
};
</script>

<style scoped></style>
