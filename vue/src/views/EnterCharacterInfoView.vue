<template>
    <div>
        <EditCharacterForm :character="character"/>
    </div>
</template>

<script>
import EditCharacterForm from '../components/EditCharacterForm.vue';
import CharacterService from '../services/CharacterService';

export default {
    components: { EditCharacterForm },
    data() {
        return {
            character: {}
        }
    },
    created() {
            CharacterService.getAllCharacters().then(response => {
                this.$store.commit("SET_CHARACTERS", response.data);
                this.character = this.$store.state.characters.find(c => c.name == this.$route.params.name);
            })
        }

}
</script>

<style scoped>

</style>