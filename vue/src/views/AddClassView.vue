<template>
    <div class="container">
        <ul class="nav nav-pills mb-3">
            <li><router-link class="nav-link" v-bind:to="{ name: 'manage-classes', params: {name: character.name} }">back to {{ character.name }}'s classes</router-link></li>   
        </ul>
        <h1 class="mb-3">Add New Class</h1>
        <AddClassForm :character="character"/>
    </div>
</template>

<script>
import AddClassForm from '../components/AddClassForm.vue';
import CharacterService from '../services/CharacterService';

    export default {
    components: { AddClassForm },
    data() {
        return {
            character: {}
        }
    },
    created() {
            CharacterService.getAllCharacters().then(response => {
                this.$store.commit("SET_CHARACTERS", response.data);
                this.character = this.character = this.$store.state.characters.find(c => c.name == this.$route.params.name)
            });
        }
    }
</script>

<style scoped>

</style>