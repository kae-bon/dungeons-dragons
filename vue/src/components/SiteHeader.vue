<template>
    <header>
        <nav class="navbar navbar-expand-md">
        <div class="container-fluid">
            <div>
            <i class="fa-solid fa-dice-d20 me-2"></i>
            <router-link class="navbar-brand" v-bind:to="{ name: 'home' }">Adventurer's Almanac</router-link>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <div class="navbar-nav column-gap-3">
                <router-link class="nav-item nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
                <router-link class="nav-item nav-link" v-bind:to="{ name: 'home' }">Profile</router-link>
                <router-link class="nav-item nav-link" v-bind:to="{ name: 'characters' }">Characters</router-link>
                <router-link class="nav-item nav-link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
            </div>
            </div>
        </div>
        </nav>
    </header>
</template>

<script>
    export default {
        
    }
</script>

<style scoped>
i {
    font-size: 1.5rem;
}

.navbar-brand {
    font-size: 1.5rem;
}

a {
    font-size: 1.2rem;
}

</style>