<template>
    <div class="container">
        <form class="d-flex flex-column align-items-center">
            <div class="mb-3 input">
                <label for="classSelect" class="form-label">choose class</label>
                <select id="classSelect" class="form-select" aria-label="select class" v-model="newClass">
                    <option selected disabled>---</option>
                    <option v-for="cl in classes" :key="cl.className">{{ cl.className }}</option>
                </select>
            </div>
            <div v-if="newClass != ''">
                <div class="mb-3 input">
                    <label for="subclassSelect" class="form-label">choose subclass</label>
                    <select id="subclassSelect" class="form-select" aria-label="select class" v-model="newSubclass">
                        <option selected disabled>---</option>
                        <option v-for="subclass in subclasses" :key="subclass">{{ subclass }}</option>
                    </select>
                </div>
                <div class="mb-3 input">
                    <label for="level" class="form-label">class level</label>
                    <input type="number" class="form-control" min="1" max="20" id="level">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">add class</button>

        </form>
    </div>
</template>

<script>
import ClassService from '../services/ClassService';

    export default {
    props: ['character'],
    data() {
        return {
            classes: [],
            newClass: "",
            newSubclass: "",
            classLevel: 0
        }
    },
    computed: {
        subclasses() {
            return this.classes.find(c => c.className == this.newClass).subclasses;
        }
    },
    created() {
            ClassService.getAllClassesAndSubclasses().then(response => {
                this.classes = response.data;
            }).catch(error => {
                alert("Could not connect to the database, please try again later.")
            })
        }
    }
</script>

<style scoped>
.input {
    width: 100%;
}
</style>