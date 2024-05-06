<template>
    <div class="container w-50">
        <h1 class="mb-3">edit {{ character.name }}</h1>
            <form @submit.prevent="saveChanges">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="nameInput" placeholder="neme" v-model="editedCharacter.name">
                        <label for="nameInput">name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="raceInput" placeholder="satyr" v-model="editedCharacter.race">
                        <label for="raceInput">race</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" min="1" max="20" class="form-control" id="currentLevelInput" placeholder="5" v-model.number="editedCharacter.currentLevel">
                        <label for="currentLevelInput">level</label>
                    </div>
                    <div class="form-floating mb-3"> 
                        <select class="form-select" id="selectAlignment" aria-label="alignment" v-model="editedCharacter.alignment">
                            <option selected disabled>---</option>
                            <option value="lawful good">lawful good</option>
                            <option value="neutral good">neutral good</option>
                            <option value="chaotic good">chaotic good</option>
                            <option value="lawful neutral">lawful neutral</option>
                            <option value="true neutral">true neutral</option>
                            <option value="chaotic neutral">chaotic neutral</option>
                            <option value="lawful evil">lawful evil</option>
                            <option value="neutral evil">neutral evil</option>
                            <option value="chaotic evil">chaotic evil</option>
                            <option value="unaligned">unaligned</option>
                        </select>
                        <label for="selectAlignment">alignment</label>
                    </div>
                    <div class="d-flex flex-column align-items-center mb-3">
                        <PicUploaderButton id="picButton" class="mb-2" />
                        <img :src="character.profilePic" class="details-pic">
                    </div>
                    <button type="submit" class="btn btn-primary w-100">save changes</button>
            </form>
        </div>
</template>

<script>
import PicUploaderButton from '../components/PicUploaderButton.vue'
import CharacterService from '../services/CharacterService';

    export default {
    components: { PicUploaderButton }, 
    props: ['character'],
    data() {
        return {
            editedCharacter: {
                name: this.character.name,
                race: this.character.race,
                currentLevel: this.character.currentLevel,
                alignment: this.character.alignment,
                classesSubclasses: this.character.classesSubclasses,
                userId: this.character.userId
            }
        }
    },
    created() {
            
        }, 
    methods: {
        saveChanges() {
            if (this.editedCharacter.name == null || this.editedCharacter.name == "") this.editedCharacter.name = this.character.name;
            if (this.editedCharacter.race == null || this.editedCharacter.race == "") this.editedCharacter.race = this.character.race;
            if (this.editedCharacter.profilePic == null || this.editedCharacter.profilePic == "") this.editedCharacter.profilePic = this.character.profilePic;
            if (this.editedCharacter.alignment == null || this.editedCharacter.alignment == "") this.editedCharacter.alignment = this.character.alignment;
            if (this.editedCharacter.currentLevel == null || this.editedCharacter.currentLevel > 20 || this.editedCharacter.currentLevel < 1) this.editedCharacter.currentLevel = this.character.currentLevel;
            this.editedCharacter.classesSubclasses = this.character.classesSubclasses;

            CharacterService.editCharacter(this.character.id, this.editedCharacter).then(response => {
                if (response.status === 200) {
                    CharacterService.getAllCharacters().then(response => {
                        this.$store.commit("SET_CHARACTERS", response.data);
                    })
                    console.log("whoo")
                    this.$router.push({ name: 'character-details', params: {name: this.character.name} })
                }
            }).catch(error => {
                if (error.response) {
                    if (error.response.status === 401) {
                        alert('error editing character. log in and try again');
                    } else {
                        alert('experiencing system issues. try again later');
                    }
                } else alert('whoops!')
            })
        }
    }
    }
</script>

<style scoped>
img {
    width: 80%;
}
</style>