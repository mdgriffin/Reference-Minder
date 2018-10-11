<template>
    <div class=""referenceForm>
        <h2>Reference Form</h2>
        <div class="form-group">
            <label class="form-label">Title</label>
            <input type="text" class="form-input" v-model="formReference.title">
        </div>
        <div class="form-group">
            <label class="form-label">Reference Types</label>
            <select v-model="formReference.type">
                <option disabled value="">Please select one</option>
                <option v-for="(typeKey, typeVal) in referenceTypes" :key="typeKey">{{typeKey}}</option>
            </select>
        </div>
        <h3>Authors</h3>
        <div class="referenceForm-authors">
            <div v-for="(author, authorIndex) in formReference.authors" :key="authorIndex">
                <div class="form-group">
                    <label class="form-label">Author {{authorIndex + 1}} Name:</label>
                    <input type="text" class="form-input" v-model="formReference.authors[authorIndex].name">
                    <button @click="removeAuthor(authorIndex)"><i class="fas fa-times"></i></button>
                </div>
            </div>
            <button @click="addAuthor">Add Author <i class="fas fa-plus"></i></button>
        </div>
        <div class="form-group">
            <label class="form-label">Date</label>
            <datepicker v-model="date"></datepicker>
        </div>
        <div class="form-group">
            <label class="form-label">Date Accessed</label>
            <datepicker v-model="dateAccessed"></datepicker>
        </div>
        <h3>Page Range</h3>
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
        <button @click="saveReference">Save</button>
    </div>
</template>

<script>
import VueTagsInput from '@johmun/vue-tags-input';
import Datepicker from 'vuejs-datepicker';
import {getReferenceTypes} from "../api/reference-types-api";

const dateToReferenceDate = function (date) {
    let obj = {};

    obj.year = date.getFullYear();
    obj.month = date.getMonth();
    obj.day = date.getDate();

    return obj;
}

export default {
    props: ['reference'],
    components: {
        VueTagsInput,
        Datepicker
    },
    data () {
        let date = this.reference.date? new Date(this.reference.date.year, this.reference.date.month, this.reference.date.day) : new Date();
        let dateAccessed = this.reference.dateAccessed? new Date(this.reference.dateAccessed.year, this.reference.dateAccessed.month, this.reference.dateAccessed.day) : new Date();

        return {
            tag: '',
            formReference: JSON.parse(JSON.stringify(this.reference)),
            date: date,
            dateAccessed: dateAccessed
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
                this.formReference.date = dateToReferenceDate(new Date());
            }

            if (!this.formReference.dateAccessed) {
                this.formReference.dateAccessed = dateToReferenceDate(new Date());
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
        }
    },
    watch: {
        dateAccessed (newVal) {
            this.formReference.dateAccessed = dateToReferenceDate(new Date(newVal))
        },
        date (newVal, oldVal) {
            this.formReference.date = dateToReferenceDate(new Date(newVal))
        }
    }
}
</script>