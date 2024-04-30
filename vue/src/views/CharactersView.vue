<template>
    <div class="container mt-3">
        <h1>your characters</h1>
        <CharacterCardList/>
    </div>
</template>

<script>
import CharacterCardList from '../components/CharacterCardList.vue';
import CharacterService from '../services/CharacterService';

    export default {
        components: {CharacterCardList},
        // created() {
        //     CharacterService.getAllCharacters().then(response => {
        //         this.$store.commit("SET_CHARACTERS", response.data)
        //     }).catch(error => {
        //         if (error.response.status == 404) {
        //             this.$router.push({name: 'not-found'})
        //         } else {
        //             alert("no")
        //         }
        //     })
        // }
    }
</script>

<style scoped>

</style>