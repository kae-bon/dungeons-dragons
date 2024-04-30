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
       
        
    }
</script>

<style scoped>

</style>