<template>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Edit Character</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form @submit.prevent="editCharacter">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="nameInput" placeholder="name" v-model="editedCharacter.name"
                        required>
                        <label for="nameInput">name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="raceInput" placeholder="race"  v-model="editedCharacter.race"
                        required>
                        <label for="raceInput">race</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" id="currentLevel" placeholder="level" min="1" max="20" aria-describedby="levelHelpText"
                         v-model="editedCharacter.currentLevel" required>
                        <label for="currentLevel">current level</label>
                        <div id="levelHelpText" class="form-text">
                        character level cannot exceed 20
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <select class="form-select" id="alignmentInput" aria-label="Floating label select alignment" v-model="editedCharacter.alignment" required>
                            <option selected disabled>---</option>
                            <option value="Lawful Good">Lawful Good</option>
                            <option value="Lawful Neutral">Lawful Neutral</option>
                            <option value="Lawful Evil">Lawful Evil</option>
                            <option value="Neutral Good">Neutral Good</option>
                            <option value="True Neutral">True Neutral</option>
                            <option value="Neutral Evil">Neutral Evil</option>
                            <option value="1Chaotic Good">Chaotic Good</option>
                            <option value="Chaotic Neutral">Chaotic Neutral</option>
                            <option value="Chaotic Evil">Chaotic Evil</option>
                        </select>
                        <label for="alignmentInput">alignment</label>
                    </div>
                    <div class="mb-4">
                        <PicUploaderButton />
                    </div>
                    <div class="d-flex justify-content-between">
                        <button type="button" id="closeModal" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
            
        </div>
    </div>
    
</template>

<script>
import CharacterService from '../services/CharacterService';
import PicUploaderButton from '../components/PicUploaderButton.vue'

    export default {
    props: ['char'],
    components: {PicUploaderButton},
    data() {
        return {
            editedCharacter: this.char
            }
    },
    methods: {
        editCharacter() {
            if (this.editedCharacter.name == "" || this.editedCharacter.name == null) {
                this.editedCharacter.name = this.char.name
            }
            if (this.editedCharacter.race == "" || this.editedCharacter.race == null) {
                this.editedCharacter.race = this.char.race
            }
            if (this.editedCharacter.alignment == "" || this.editedCharacter.alignment == null) {
                this.editedCharacter.alignment = this.char.alignment
            }
            // if (this.editedCharacter.profilePic == "" || this.editedCharacter.name == null) {
            //     this.editedCharacter.profilePic = this.char.profilePic
            // }
            // if (this.editedCharacter.currentLevel == 0 || this.editedCharacter.name == null) {
            //     this.editedCharacter.currentLevel = this.char.curentLevel
            // }
            this.editedCharacter.classesSubclasses = this.char.classesSubclasses
            this.editedCharacter.id = this.char.id

            CharacterService.editCharacter(this.editedCharacter.id, this.editedCharacter).then(response =>
            {
                if (response.status === 200) {
                    CharacterService.getAllCharacters().then(response => {
                        this.$store.commit("SET_CHARACTERS", response.data)
                    })
                    this.closeModal();
                    }
                }
            )
        },
        closeModal() {
            const modal = document.getElementById('closeModal');
            modal.click();
            this.resetForm();
        },
        resetForm() {
            this.editedCharacter = this.character
        }
    },
    watch: {
        char: {
            deep: true,
            handler(n, o) {
                this.editedCharacter = this.char;
            }
        }
    }, created() {
        
    }
    }
</script>

<style scoped>

</style>