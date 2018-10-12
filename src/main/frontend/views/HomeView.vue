<template>
    <div class="home">
        <div class="sidebar">
            <h2>Tags</h2>
            <tag-list :tags="tags"></tag-list>
        </div>
        <div class="content">
            <h2>References</h2>
            <div v-if="referenceRows">
                <data-table :headings="referenceHeadings" :rows="referenceRows"></data-table>
            </div>
            <reference-form-modal :reference="emptyReference" @save="onSaveReference"></reference-form-modal>
            <button @click="showCreateForm" class="btn btn-large btn-secondary">Add Reference</button>
        </div>
  </div>
</template>

<script>
import ReferenceForm from '../components/ReferenceForm.vue';
import ReferenceList from '../components/ReferenceList.vue';
import DataTable from '../components/DataTable.vue';
import ReferenceFormModal from '../components/ReferenceFormModal.vue';
import TagList from "../components/TagList.vue";
import {getReferences, getReferenceTags, saveReference} from "../api/reference-api";

export default {
    name: 'home-view',
    components: {
        'reference-form': ReferenceForm,
        'reference-list': ReferenceList,
        'data-table': DataTable,
        ReferenceFormModal,
        TagList
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
            referenceHeadings: ['Title', 'Authors', 'Pages From', 'Pages To', 'Date', 'Date Accessed', 'Type'],
            numReferenceSaves: 0
        }
    },
    asyncComputed: {
        tags: {
            get () {
                return getReferenceTags();
            },
            watch () {
                this.numReferenceSaves;
            }
        },
        referenceRows: {
            get () {
                let self = this;
                return new Promise((resolve, reject) => {
                    getReferences().then(references => {
                        let res = references.reduce((acc, reference) => {
                            let arr = [];

                            arr.push(reference.title);
                            arr.push(reference.authors.reduce((acc, author) => acc += author.name + ', ', ''));
                            arr.push(reference.pages.from)
                            arr.push(reference.pages.to)
                            arr.push(new Date(reference.date.year, reference.date.month, reference.date.day).toLocaleDateString("en-IE"))
                            arr.push(new Date(reference.dateAccessed.year, reference.dateAccessed.month, reference.dateAccessed.day).toLocaleDateString("en-IE"))
                            arr.push(reference.type? reference.type :  '')

                            acc.push(arr)

                            return acc
                        }, [])

                        resolve(res)
                    })
                        .catch(err => {
                            reject(err);
                        })
                });
            },
            watch () {
                this.numReferenceSaves;
            }
        }
    },
    methods: {
        onSaveReference (reference) {
            let self = this;
            reference.createdAt = new Date();
            reference.updatedAt = new Date();
            saveReference(reference)
                .then(res => {
                    self.numReferenceSaves++;
                })
        },
        showCreateForm () {
            this.$modal.show('reference-form-modal')
        }
    }
}
</script>