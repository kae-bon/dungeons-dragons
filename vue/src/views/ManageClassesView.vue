<template>
    <div class="container">
        <ul class="nav nav-pills mb-3">
            <li><router-link class="nav-link" v-bind:to="{ name: 'character-details', params: {name: character.name} }">back to {{ character.name }}'s profile</router-link></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">character options</a>
                <ul class="dropdown-menu">
                    <li><router-link class="dropdown-item" v-bind:to="{ name: 'edit-character' }">edit profile</router-link></li>
                    <li><router-link class="dropdown-item" :character="character" v-bind:to="{ name: 'manage-classes' }">manage classes</router-link></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item text-danger" href="#">delete character</a></li>
                </ul>
            </li>
        </ul>
        <h1>{{ character.name }}</h1>
    </div>
</template>

<script>
    import CharacterService from '../services/CharacterService';

export default {
    components: {},
    data() {
        return {
            character: {},
        }
    },
        created() {
            CharacterService.getAllCharacters().then(response => {
                console.log("hi");
                this.$store.commit("SET_CHARACTERS", response.data);
                this.character = this.$store.state.characters.find(c => c.name == this.$route.params.name);
            })
        },
    
}
</script>

<style scoped>

</style>