<template>
    <div class="container">
        <h1 class="mb-4">your characters</h1>
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