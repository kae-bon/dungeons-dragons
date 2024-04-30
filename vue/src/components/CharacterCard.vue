<template>
    <div class="card">
        <img :src="character.profilePic" class="card-img-top" alt="character img">
        <div class="card-body">
            <h5 class="card-title">{{ character.name }}</h5>
            <p class="card-text">{{character.race}}</p>
            <router-link v-bind:to="{ name: 'home' }">view details</router-link>
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
    width: 45%;
}
</style>