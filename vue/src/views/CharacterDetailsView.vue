<template>
    <div class="container">
        <div class="d-flex flex-lg-row column-gap-lg-3 row-gap-2 flex-column align-items-center align-items-lg-start">
            <img :src="character.profilePic" alt="">
            <div class="w-75">
                <h1>{{ character.name }}</h1>
                <p class="class fst-italic" v-for="charClass in character.classesSubclasses" :key="charClass.characterClass">{{ charClass.subclass }} {{ charClass.characterClass }}, {{ charClass.classLevel }}</p>
                <div class="info mt-2">
                    <p class="fw-bold">character level</p>
                    <p class="mb-3">{{ character.currentLevel }}</p>
                    <p class="fw-bold">race</p>
                    <p class="mb-3">{{ character.race }}</p>
                    <p class="fw-bold">alignment</p>
                    <p class="mb-3">{{ character.alignment }}</p>
                </div>
                
            </div>
            <div class="d-flex flex-column align-items-center">
                    <button class="btn btn-primary mb-1 w-100" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="fa-solid fa-scroll"></i> edit character</button>
                    <button class="btn btn-primary w-100"><i class="fa-solid fa-hat-wizard"></i> manage classes</button>
                </div>
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <EditCharacter v-bind:char="character"/>
        </div>
    </div>
    
</template>

<script>
import CharacterService from '../services/CharacterService';
import EditCharacter from '../components/EditCharacter.vue';

    export default {
        components: {EditCharacter},
        data() {
            return {
                character: {}
            }
        },
        created() {
            CharacterService.getAllCharacters().then(response => {
                this.$store.commit("SET_CHARACTERS", response.data);
                this.character = this.$store.state.characters.find(c => c.id == this.$route.params.id);
            })
        }, 
    
    }
</script>

<style scoped>
img {
    width: 50%;
    margin-bottom: .5rem;
}
h1 {
    margin-bottom: 0;
}
.class {
    font-size: .8rem;
}
.info p {
    margin-bottom: .2rem;
    font-size: .9rem;
}

.class:first-of-type {
    margin-bottom: 0;
}

button {
    font-size: .8rem;
}

@media screen and ( min-width: 400px ) {
    .class {
        font-size: 1rem;
    }

    .info p {
        font-size: 1rem;
    }
}

</style>