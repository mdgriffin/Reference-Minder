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
                        <button @click="updateReference(rowIndex)" class="btn btn-secondary" title="Update Reference">Update <i class="fas fa-edit"></i></button>
                    </td>
                    <td class="align-left">
                        <button @click="deleteReference(rowIndex)" class="btn btn-warning" title="Delete Reference"><i class="fas fa-times"></i></button>
                    </td>
                </template>
            </data-table>
        </div>
        <reference-form @save="onSaveReference"></reference-form>

        <modal name="delete-successful-modal" transition="pop-out" :height="'auto'" @before-open="beforeOpenDeleteSuccessful">
            <div class="messageModal messageModal-success">
                <h3><i class="fas fa-clipboard-check"></i> Reference Deleted Successfully</h3>
            </div>

        </modal>
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
            if (confirm("Are you sure you want to delete this reference?")) {
                deleteReference(this.references[rowIndex].id)
                    .then(res => {
                        this.numReferenceSaves++;
                        this.$modal.show('delete-successful-modal')
                        this.$emit('referencesChanged');
                    })
                    .catch(err => {
                        console.log(err)
                    })
            } else {
                this.$modal.show('delete-successful-modal')
            }
        },
        updateReference (rowIndex) {
            this.$modal.show('reference-form-modal', {reference: this.references[rowIndex]})
        },
        showCreateForm () {
            this.$modal.show('reference-form-modal')
        },
        beforeOpenDeleteSuccessful () {
            setTimeout(() => {
                this.$modal.hide('delete-successful-modal')
            }, 2000)
        }
    }
}
</script>