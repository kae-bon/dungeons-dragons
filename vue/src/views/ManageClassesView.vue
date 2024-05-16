<template>
  <div class="container">
    <div
      class="alert alert-success alert-dismissible fade show"
      v-if="$store.state.showAlert"
      role="alert"
    >
      <strong>woohoo!</strong> class registered successfully
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
            name: 'character-details',
            params: { name: character.name },
          }"
          >back to {{ character.name }}'s profile</router-link
        >
      </li>
    </ul>
    <h1>Manage {{ character.name }}'s Classes</h1>

    <div class="mt-3 mb-5">
      <button @click="goToNewClassPage" class="btn btn-primary">
        add class
      </button>
    </div>

    <div class="">
      <p class="mb-0 fst-italic">click a class to edit it</p>
      <button
        v-for="charClass in character.classesSubclasses"
        :key="charClass.characterClass"
        class="btn btn-primary mb-0 d-block"
      >
        {{ charClass.subclass }} {{ charClass.characterClass }}, level
        {{ charClass.classLevel }}
      </button>
    </div>
  </div>
</template>

<script>
import CharacterService from "../services/CharacterService";

export default {
  components: {},
  data() {
    return {
      character: {},
    };
  },
  created() {
    CharacterService.getAllCharacters().then((response) => {
      console.log("hi");
      this.$store.commit("SET_CHARACTERS", response.data);
      this.character = this.$store.state.characters.find(
        (c) => c.name == this.$route.params.name
      );
    });
  },
  methods: {
    goToNewClassPage() {
      this.$router.push({ name: "add-class" });
    },
    dismissAlert() {
      this.$store.commit("DISMISS_ALERT");
    },
  },
};
</script>

<style scoped>
span {
  font-size: 1rem;
}

@media screen and (min-width: 650px) {
  span {
    font-size: 1.2rem;
  }
}
</style>
