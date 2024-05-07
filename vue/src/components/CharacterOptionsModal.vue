<template>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Options</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body d-flex flex-column align-items-center row-gap-2">
                <router-link class="btn btn-primary w-50" @click="close()" v-bind:to="{ name: 'edit-character' }"><i class="fa-solid fa-scroll"></i> edit profile info</router-link>
                <button class="btn btn-primary w-50"><i class="fa-solid fa-hat-wizard"></i> manage classes</button>
                <button class="btn btn-primary w-50"><i class="fa-solid fa-skull-crossbones"></i> delete character</button>
            </div>
            
        </div>
    </div>
    
</template>

<script>


export default {
    methods: {
        close() {
            this.$emit('close')
        },
        
    }
}
</script>

<style scoped>
i {
   color: #fdfdfc;
}

button {
    font-size: .9rem;
}

@media screen and ( min-width: 750px ) {
    button {
        font-size: 1rem;
    }
}

</style>