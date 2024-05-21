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

    <div>
      <h2 class="mb-0">current classes</h2>
      <p class="fst-italic">click a class to edit it</p>

      <router-link
        v-bind:to="{
          name: 'edit-class',
          params: {
            name: character.name,
            className: charClass.characterClass,
          },
        }"
        v-for="charClass in character.classesSubclasses"
        :key="charClass.characterClass"
        class="currentClass"
      >
        {{ charClass.subclass }} {{ charClass.characterClass }}, lvl
        {{ charClass.classLevel }}
      </router-link>
    </div>

    <div class="mt-5 mb-5">
      <h2>add new class</h2>
      <button @click="goToNewClassPage" class="btn btn-primary">
        add class
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

h2 {
  font-size: 1.2rem;
}

p {
  font-size: 0.9rem;
}

.currentClass {
  font-size: 1.2rem;
}

@media screen and (min-width: 650px) {
  span {
    font-size: 1.2rem;
  }
}
</style>
