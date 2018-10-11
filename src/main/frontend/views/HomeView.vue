<template>
  <div class="home">
    <h1>Home Page</h1>
    <h2>References</h2>
      <div v-if="referenceRows">
          <data-table :headings="referenceHeadings" :rows="referenceRows"></data-table>
      </div>
    <reference-list :references="references"></reference-list>
    <reference-form :reference="emptyReference" @save="onSaveReference"></reference-form>
  </div>
</template>

<script>
import ReferenceForm from '../components/ReferenceForm.vue';
import ReferenceList from '../components/ReferenceList.vue';
import DataTable from '../components/DataTable.vue';
import {getReferences, saveReference} from "../api/reference-api";

export default {
    name: 'home-view',
    components: {
        'reference-form': ReferenceForm,
        'reference-list': ReferenceList,
        'data-table': DataTable
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
            },
            referenceHeadings: ['Title', 'Pages From', 'Pages To']
        }
    },
    asyncComputed: {
        references () {
            return getReferences();
        },
        referenceRows () {
            let self = this;
            return new Promise ((resolve, reject) => {
                getReferences().then(references => {
                    let res = [];

                    references.forEach(reference => {
                        let arr = [];

                        arr.push(reference.title);
                        arr.push(reference.pages.from)
                        arr.push(reference.pages.to)

                        res.push(arr)
                    })

                    resolve(res)
                })
                .catch(err => {
                    reject(err);
                })
            });
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