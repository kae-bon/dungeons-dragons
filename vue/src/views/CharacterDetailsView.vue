<template>
  <div class="container">
    <div
      class="alert alert-success alert-dismissible fade show"
      v-if="showSuccess"
      role="alert"
    >
      <strong>Yeehaw!</strong> character edit successful
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
        <router-link class="nav-link" v-bind:to="{ name: 'characters' }"
          >back to your characters</router-link
        >
      </li>
      <li class="nav-item dropdown">
        <a
          class="nav-link dropdown-toggle"
          data-bs-toggle="dropdown"
          href="#"
          role="button"
          aria-expanded="false"
          >character options</a
        >
        <ul class="dropdown-menu">
          <li>
            <router-link
              class="dropdown-item"
              v-bind:to="{ name: 'edit-character' }"
              >edit profile</router-link
            >
          </li>
          <li>
            <router-link
              class="dropdown-item"
              :character="character"
              v-bind:to="{ name: 'manage-classes' }"
              >manage classes</router-link
            >
          </li>
          <li><hr class="dropdown-divider" /></li>
          <li>
            <a class="dropdown-item text-danger" href="#">delete character</a>
          </li>
        </ul>
      </li>
    </ul>
    <div
      class="d-flex flex-lg-row column-gap-lg-3 row-gap-2 flex-column align-items-center align-items-lg-stretch"
    >
      <img :src="character.profilePic" class="details-pic" />
      <div class="w-100 profile d-flex flex-column justify-content-between">
        <div>
          <h1>{{ character.name }}</h1>
          <p
            class="class fst-italic"
            v-for="charClass in character.classesSubclasses"
            :key="charClass.characterClass"
          >
            {{ charClass.subclass }} {{ charClass.characterClass }},
            {{ charClass.classLevel }}
          </p>
          <div class="info mt-2">
            <p class="fw-bold">character level</p>
            <p class="mb-3">{{ character.currentLevel }}</p>
            <p class="fw-bold">race</p>
            <p class="mb-3">{{ character.race }}</p>
            <p class="fw-bold">alignment</p>
            <p class="mb-3">{{ character.alignment }}</p>
          </div>
        </div>
      </div>
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
      showSuccess: this.$store.state.showAlert,
    };
  },
  methods: {
    closeModal() {
      let modal = document.getElementById("optionsModal");
      modal.click();
    },
    dismissAlert() {
      this.$store.commit("DISMISS_ALERT");
    },
  },
  created() {
    CharacterService.getAllCharacters().then((response) => {
      this.$store.commit("SET_CHARACTERS", response.data);
      this.character = this.$store.state.characters.find(
        (c) => c.name == this.$route.params.name
      );
    });
  },
};
</script>

<style scoped>
img {
  width: 50%;
  margin-bottom: 0.5rem;
}

h1 {
  margin-bottom: 0;
  margin-top: 1rem;
}

.class {
  font-size: 0.8rem;
}

.info p {
  margin-bottom: 0.2rem;
  font-size: 0.9rem;
}

.class:not(:last-child) {
  margin-bottom: 0;
}

button {
  font-size: 0.8rem;
  margin-top: 1.2rem;
}

.btn-close {
  margin-top: 0;
}

@media screen and (min-width: 400px) {
  .class {
    font-size: 1rem;
  }

  .info p {
    font-size: 1rem;
  }

  img {
    margin-bottom: 0;
  }
}

@media screen and (min-width: 750px) {
  h1 {
    margin-top: 0;
  }
}
</style>
