<template>
    <div class="card">
        <img :src="character.profilePic" class="card-img-top" alt="character img">
        <div class="card-body">
            <h5 class="card-title mb-1">{{ character.name }}</h5>
            <span v-for="charClass in character.classesSubclasses" :key="charClass.characterClass" class="badge text-bg-primary mb-0">{{ charClass.subclass }} {{ charClass.characterClass }}</span>
            <p class="card-text mt-3">{{character.race}}</p>       
        </div>
        <div class="text-center mt-2 mb-3">
            <router-link :character='character' class="btn btn-secondary" v-bind:to="{ name: 'character-details', params: {name: character.name} }">character details</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        props: ['character'],
        data() {
            return {
                charImg: this.character.profilePic
            }
        }
    }
</script>

<style scoped>


.card {
    width: 60%;
}

a {
    padding-top: .2rem;
    padding-bottom: .2rem;
    font-size: .7rem;
}

span {
    font-size: .6rem;
    width: 100%;
}

.badge {
    white-space: normal;
}

p {
    font-size: .9rem;
}

@media screen and ( min-width: 400px ) {
    .card {
        width: 45%;
    }

    img {
    opacity: 1;
    transition: opacity 1s;
    }

    img:hover {
    opacity: 90%;
    transition: opacity 300ms;
    }

    a {
        font-size: .7rem;
        width: 70%;
    }

    span {
    font-size: .65rem;
    }
}

@media screen and ( min-width: 750px) {
    .card {
        width: 25%;
    }


}

@media screen and ( min-width: 800px) {
    span {
        font-size: .7rem;
        display: inline-block;
        width: 75%;
    }


}
    
    

</style>