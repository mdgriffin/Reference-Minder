<template>
  <div class="home">
    <h1>Home Page</h1>
    <reference-form :reference="emptyReference" @save="onSaveReference"></reference-form>
  </div>
</template>

<script>
import ReferenceForm from '../components/reference-form.vue';
import {saveReference} from "../api/reference-api";

export default {
    name: 'home-view',
    components: {
        'reference-form': ReferenceForm
    },
    data() {
        return {
            emptyReference: {
                title: "",
                pages: {
                    from: 20,
                    to: 25
                },
                date: {
                    day: 1,
                    month: 2,
                    year: 3
                },
                dateAccessed: {
                    day: 1,
                    month: 2,
                    year: 3
                }
            }
        }
    },
    methods: {
        onSaveReference (reference) {
            console.log('On Reference CB2')
            console.log(reference)
            saveReference(reference)
                .then(res => {
                    console.log(res)
                    console.log('On Save Reference')
                })
        }
    }
}
</script>