<template>
  <div class="home">
    <h1>Home Page</h1>
    <h2>References</h2>
    <reference-list :references="references"></reference-list>
    <reference-form :reference="emptyReference" @save="onSaveReference"></reference-form>
  </div>
</template>

<script>
import ReferenceForm from '../components/ReferenceForm.vue';
import ReferenceList from '../components/ReferenceList.vue';
import {getReferences, saveReference} from "../api/reference-api";

export default {
    name: 'home-view',
    components: {
        'reference-form': ReferenceForm,
        'reference-list': ReferenceList
    },
    data() {
        return {
            emptyReference: {
                title: "",
                type: "",
                authors: [],
                tags: [],
                pages: {
                    from: 20,
                    to: 25
                },
                date: null,
                dateAccessed: null
            }
        }
    },
    asyncComputed: {
        references () {
            return getReferences();
        }
    },
    methods: {
        onSaveReference (reference) {
            console.log('On Reference CB2')
            console.log(reference)
            saveReference(reference)
                .then(res => {
                    console.log(JSON.stringify(res))
                    console.log('On Save Reference')
                })
        }
    }
}
</script>