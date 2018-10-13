<template>
    <div class="home">
        <div class="sidebar">
            <h2>Tags</h2>
            <tag-list :tags="tags"></tag-list>
        </div>
        <div class="content">
            <h2>References</h2>
            <div v-if="references">
                <data-table :headings="referenceHeadings" :rows="references | flattenReferences">
                    <template slot="after-data" slot-scope="{rowIndex}">
                        <button @click="updateReference(rowIndex)" class="btn btn-secondary">Update</button>
                        <button @click="deleteReference(rowIndex)" class="btn btn-secondary"><i class="fas fa-times"></i></button>
                    </template>
                </data-table>
            </div>
            <modal name="new-reference-form" transition="pop-out" :width="modalWidth" :height="modalHeight" :scrollable="modalScroll">
                <reference-form :reference="emptyReference" @save="onSaveReference"></reference-form>
            </modal>
            <button @click="showCreateForm" class="btn btn-large btn-secondary">Add Reference</button>
        </div>
  </div>
</template>

<script>
import ReferenceForm from '../components/ReferenceForm.vue';
import ReferenceList from '../components/ReferenceList.vue';
import DataTable from '../components/DataTable.vue';
import TagList from "../components/TagList.vue";
import {getReferences, getReferenceTags, saveReference, deleteReference} from "../api/reference-api";
import {flatten} from '../filters/references-filter.js'

export default {
    name: 'home-view',
    components: {
        ReferenceForm,
        'reference-list': ReferenceList,
        'data-table': DataTable,
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
            numReferenceSaves: 0,
            modalWidth: '60%',
            modalHeight: 'auto',
            modalScroll: true
        }
    },
    filters: {
        flattenReferences (references) {
            return flatten(references);
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
        references: {
            get () {
                return getReferences();
            },
            watch () {
                this.numReferenceSaves;
            }
        }
    },
    methods: {
        onSaveReference (reference) {
            reference.createdAt = new Date();
            reference.updatedAt = new Date();
            saveReference(reference)
                .then(res => {
                    this.numReferenceSaves++;
                })
        },
        deleteReference (rowIndex) {
            deleteReference(this.references[rowIndex].id)
                .then(res => {
                    this.numReferenceSaves++
                })
                .catch(err => {
                    console.log(err)
                })
        },
        updateReference (rowIndex) {
            console.log('show update model for ' + rowIndex)
        },
        showCreateForm () {
            this.$modal.show('new-reference-form')
        }
    }
}
</script>