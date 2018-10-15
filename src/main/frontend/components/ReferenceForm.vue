<template>
    <modal name="reference-form-modal" transition="pop-out" :width="modalWidth" :height="modalHeight" :scrollable="modalScroll" @before-open="beforeOpen">
        <div class="referenceFormModal">
            <div v-if="formReference">
                <h2>Reference Form</h2>
                <div class="form-group">
                    <label class="form-label">Title</label>
                    <input type="text" class="form-input" v-model="formReference.title">
                </div>
                <div class="form-group">
                    <label class="form-label">Reference Types</label>
                    <select v-model="formReference.type">
                        <option disabled value="">Please select one</option>
                        <option v-for="(typeKey, typeVal) in referenceTypes" :value="typeVal" :key="typeKey">{{typeKey}}</option>
                    </select>
                </div>
                <h4>Authors</h4>
                <div class="referenceForm-authors">
                    <div v-for="(author, authorIndex) in formReference.authors" :key="authorIndex">
                        <div class="form-group">
                            <label class="form-label">Author {{authorIndex + 1}} Name:</label>
                            <input type="text" class="form-input" v-model="formReference.authors[authorIndex].name">
                            <button @click="removeAuthor(authorIndex)" class="btn btn-small"><i class="fas fa-times"></i></button>
                        </div>
                    </div>
                    <button @click="addAuthor" class="btn btn-secondary">Add Author <i class="fas fa-plus"></i></button>
                </div>
                <div class="form-group">
                    <label class="form-label">Date</label>
                    <datepicker v-model="formReference.date" :input-class="'form-input'"></datepicker>
                </div>
                <div class="form-group">
                    <label class="form-label">Date Accessed</label>
                    <datepicker v-model="formReference.dateAccessed" :input-class="'form-input'"></datepicker>
                </div>
                <div class="form-group">
                    <label class="form-label">Page From</label>
                    <input type="text" class="form-input" v-model="formReference.pages.from">
                </div>
                <div class="form-group">
                    <label class="form-label">Page To</label>
                    <input type="text" class="form-input" v-model="formReference.pages.to">
                </div>
                <div class="form-group">
                    <label class="form-label">Tags</label>
                    <vue-tags-input
                            v-model="tag"
                            :tags="formReference.tags"
                            @tags-changed="onTagsChanged"
                    />
                </div>
                <button @click="saveReference" class="btn btn-large btn-primary">Save <i class="fas fa-save"></i></button>
            </div>
        </div>
    </modal>
</template>

<script>
    import VueTagsInput from '@johmun/vue-tags-input';
    import Datepicker from 'vuejs-datepicker';
    import {getReferenceTypes} from "../api/reference-api";

    const blankReference = {
        title: "",
        type: "",
        authors: [],
        tags: [],
        pages: {
            from: 20,
            to: 25
        },
        date: new Date(),
        dateAccessed: new Date()
    };


    export default {
        name: 'reference-form',
        components: {
            VueTagsInput,
            Datepicker
        },
        data () {
            return {
                tag: '',
                formReference: JSON.parse(JSON.stringify(blankReference)),
                modalWidth: '60%',
                modalHeight: 'auto',
                modalScroll: true
            }
        },
        asyncComputed: {
            referenceTypes () {
                return getReferenceTypes();
            }
        },
        methods: {
            saveReference () {
                if (!this.formReference.date) {
                    this.formReference.date = new Date();
                }

                if (!this.formReference.dateAccessed) {
                    this.formReference.dateAccessed = new Date();
                }

                this.$emit('save', this.formReference)
            },
            addAuthor () {
                this.formReference.authors.push({'name': ''})
            },
            removeAuthor (authorIndex) {
                this.formReference.authors.splice(authorIndex, 1)
            },
            onTagsChanged (newTags) {
                this.formReference.tags = newTags
            },
            beforeOpen (event) {
                if (event.params && event.params.reference) {
                    this.formReference = JSON.parse(JSON.stringify(event.params.reference))
                } else {
                    this.formReference = JSON.parse(JSON.stringify(blankReference))
                }
            }
        }
    }
</script>

<style>
    .referenceFormModal {
        background: #fff;
        overflow: scroll;
        padding: 2em;
    }
</style>