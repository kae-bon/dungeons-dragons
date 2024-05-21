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
    <h1 class="mb-3">Edit Class</h1>
    <EditClassForm
      @warning="showAlert = true"
      :character="character"
      :class="currentClass"
    />
  </div>
</template>

<script>
import EditClassForm from "../components/EditClassForm.vue";
import CharacterService from "../services/CharacterService";
import ClassService from "../services/ClassService";

export default {
  components: { EditClassForm },
  data() {
    return {
      character: {},
      showAlert: false,
    };
  },
  methods: {
    dismissAlert() {
      this.showAlert = false;
    },
  },
  created() {
    this.showAlert = this.$store.state.showAlert;
    CharacterService.getAllCharacters().then((response) => {
      this.$store.commit("SET_CHARACTERS", response.data);
      this.character = this.character = this.$store.state.characters.find(
        (c) => c.name == this.$route.params.name
      );
      ClassService.getAllClassesAndSubclasses().then((response) => {
        this.$store.commit("SET_CLASSES", response.data);
      });
    });
  },
};
</script>

<style scoped></style>
