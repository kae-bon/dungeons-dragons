<template>
    <div>
        <CharacterCard v-for="character in characters" :key="character.id" :character="character" />
    </div>
</template>

<script>
import CharacterCard from './CharacterCard.vue'
import CharacterService from '../services/CharacterService';

    export default {
        data() {
            return {
                characters: ""
            }
        },
        components: {CharacterCard},
        created() {
            this.characters = this.$store.state.characters
            
            CharacterService.getAllCharacters().then(response => {
                this.$store.commit("SET_CHARACTERS", response.data)
                this.characters = this.$store.state.characters
            }).catch(error => {
                if (error.response.status == 404) {
                    this.$router.push({name: 'not-found'})
                } else {
                    alert("no")
                }
            })
        }
        
    }
</script>

<style scoped>

</style>