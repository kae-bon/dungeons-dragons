<template>
  <div id="capstone-app" class="d-flex flex-column">
    <SiteHeader/>
    <router-view id="router-view" />
    <SiteFooter/>
  </div>
</template>

<script>
import SiteHeader from './components/SiteHeader.vue'
import SiteFooter from './components/SiteFooter.vue'
import CharacterService from './services/CharacterService';

export default {
  components: {SiteHeader, SiteFooter},
  created() {
        this.characters = this.$store.state.characters
        CharacterService.getAllCharacters().then(response => {
                this.$store.commit("SET_CHARACTERS", response.data)
                this.characters = this.$store.state.characters
            }).catch(error => {
                if (error.response.status == 404) {
                    this.$store.commit("SET_CHARACTERS", {})
                } else {
                    alert("no")
                }
            })
        }
}

</script>

<style scoped>
#capstone-app {
  height: 100vh;
}

#router-view {
  flex-grow: 1;
  margin-top: 1rem;
}

@media screen and ( min-width: 400px ) {
    .card {
        width: 45%;
    }

    #router-view {
      margin-top: 2rem;
    }
}

@media screen and ( min-width: 750px) {
    .card {
        width: 25%;
    }

    #router-view {
      margin-top: 4rem;
    }
}
</style>