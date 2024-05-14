<template>
  <div class="card">
    <img :src="character.profilePic" class="card-img-top" alt="character img" />
    <div class="card-body d-flex flex-column">
      <h5 class="card-title mb-2">{{ character.name }}</h5>
      <div class="mb-3 d-flex flex-column row-gap-1 flex-grow-1">
        <span
          v-for="charClass in character.classesSubclasses"
          :key="charClass.characterClass"
          class="badge text-bg-primary"
          >{{ charClass.subclass }} {{ charClass.characterClass }}</span
        >
      </div>
      <p class="card-text">{{ character.race }}</p>
    </div>
    <div class="mt-2 mb-3 text-center">
      <router-link
        :character="character"
        class="btn btn-success"
        v-bind:to="{
          name: 'character-details',
          params: { name: character.name },
        }"
        >character details</router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  props: ["character"],
  data() {
    return {
      charImg: this.character.profilePic,
    };
  },
};
</script>

<style scoped>
.card {
  width: 70%;
}

a {
  padding-top: 0.2rem;
  padding-bottom: 0.2rem;
  font-size: 0.7rem;
}

span {
  font-size: 0.6rem;
  width: 100%;
}

.badge {
  white-space: normal;
}

p {
  font-size: 0.9rem;
}

@media screen and (min-width: 400px) {
  .card {
    width: 60%;
  }

  img {
    opacity: 1;
    transition: opacity 1s;
  }

  img:hover {
    opacity: 90%;
    transition: opacity 300ms;
  }

  a {
    font-size: 0.7rem;
    width: 70%;
  }

  span {
    font-size: 0.65rem;
  }
}

@media screen and (min-width: 700px) {
  .card {
    width: 40%;
  }
}

@media screen and (min-width: 800px) {
  span {
    font-size: 0.7rem;
  }

  .btn {
    font-size: 0.9rem;
  }

  .card {
    width: 35%;
  }
}

@media screen and (min-width: 992px) {
  .card {
    width: 30%;
  }
}
</style>
