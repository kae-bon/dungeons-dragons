<template>
    <div class="container">
        <ul class="nav nav-pills mb-3">
            <li><router-link class="nav-link" v-bind:to="{ name: 'character-details', params: {name: character.name} }">back to {{ character.name }}'s profile</router-link></li>
        </ul>
        <h1>{{ character.name }}'s Classes</h1>
        <div class="container">
            <span v-for="charClass in character.classesSubclasses" :key="charClass.characterClass" class="badge text-bg-primary mb-0">{{ charClass.subclass }} {{ charClass.characterClass }}</span>
        </div>
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
span {
    font-size: 1rem;
}

@media screen and ( min-width: 650px ) {
    span {
    font-size: 1.2rem;
    }
}
</style>