<template>
    <div class="referenceListView">
        <header class="mainHeader">
            <h2>References</h2>
            <button @click="showCreateForm" class="btn btn-primary">Add Reference <i class="far fa-plus-square"></i></button>
        </header>
        <div v-if="references" class="card">
            <data-table :headings="referenceHeadings" :rows="references | flattenReferences">
                <template slot="after-data" slot-scope="{rowIndex}">
                    <td>
                        <button @click="updateReference(rowIndex)" class="btn btn-secondary">Update <i class="fas fa-edit"></i></button>
                    </td>
                    <td class="align-left">
                        <button @click="deleteReference(rowIndex)" class="btn btn-secondary"><i class="fas fa-times"></i></button>
                    </td>
                </template>
            </data-table>
        </div>
        <reference-form @save="onSaveReference"></reference-form>
    </div>
</template>

<script>
import ReferenceForm from '../components/ReferenceForm.vue';
import ReferenceList from '../components/ReferenceList.vue';
import DataTable from '../components/DataTable.vue';
import {getReferences, getReferencesByTag, saveReference, deleteReference, updateReference} from "../api/reference-api";
import {flatten} from '../filters/references-filter.js'

export default {
    name: 'references-view',
    components: {
        ReferenceForm,
        'reference-list': ReferenceList,
        'data-table': DataTable
    },
    data() {
        return {
            referenceHeadings: ['Title', 'Authors', 'Pages From', 'Pages To', 'Date', 'Date Accessed', 'Type'],
            numReferenceSaves: 0
        }
    },
    filters: {
        flattenReferences (references) {
            return flatten(references);
        }
    },
    watch: {

    },
    asyncComputed: {
        references: {
            get () {
                var tag = this.$route.params.tag;

                if (tag !== undefined && tag.length > 0) {
                    return getReferencesByTag(tag);
                } else {
                    return getReferences();
                }
            },
            watch () {
                this.numReferenceSaves;
                this.$route.params.tag;
            }
        }
    },
    methods: {
        onSaveReference (reference) {
            if (reference.id) {
                updateReference(reference)
                    .then(res => {
                        this.numReferenceSaves++;
                        this.$emit('referencesChanged');
                    })
            } else {
                saveReference(reference)
                    .then(res => {
                        this.numReferenceSaves++;
                    })
            }


            this.$modal.hide('reference-form-modal')
        },
        deleteReference (rowIndex) {
            deleteReference(this.references[rowIndex].id)
                .then(res => {
                    this.numReferenceSaves++;
                    this.$emit('referencesChanged');
                })
                .catch(err => {
                    console.log(err)
                })
        },
        updateReference (rowIndex) {
            this.$modal.show('reference-form-modal', {reference: this.references[rowIndex]})
        },
        showCreateForm () {
            this.$modal.show('reference-form-modal')
        }
    }
}
</script>