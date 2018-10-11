<template>
    <div class=""referenceForm>
        <h2>Reference Form</h2>
        <div class="form-group">
            <label class="form-label">Title</label>
            <input type="text" class="form-input" v-model="reference.title">
        </div>
        <h3>Authors</h3>
        <div class="referenceForm-authors">
            <div v-for="(author, authorIndex) in reference.authors" :key="authorIndex">
                <div class="form-group">
                    <label class="form-label">Author {{authorIndex}} Name:</label>
                    <input type="text" class="form-input" v-model="reference.authors[authorIndex].name">
                    <button @click="removeAuthor(authorIndex + 1)"><i class="fas fa-times"></i></button>
                </div>
            </div>
            <button @click="addAuthor">Add Author <i class="fas fa-plus"></i></button>
        </div>
        <h3>Date</h3>
        <div class="form-group">
            <label class="form-label">Day</label>
            <input type="text" class="form-input" v-model="reference.date.day">
        </div>
        <div class="form-group">
            <label class="form-label">Month</label>
            <input type="text" class="form-input" v-model="reference.date.month">
        </div>
        <div class="form-group">
            <label class="form-label">Year</label>
            <input type="text" class="form-input" v-model="reference.date.year">
        </div>
        <h3>Date Accessed</h3>
        <div class="form-group">
            <label class="form-label">Day</label>
            <input type="text" class="form-input" v-model="reference.dateAccessed.day">
        </div>
        <div class="form-group">
            <label class="form-label">Month</label>
            <input type="text" class="form-input" v-model="reference.dateAccessed.month">
        </div>
        <div class="form-group">
            <label class="form-label">Year</label>
            <input type="text" class="form-input" v-model="reference.dateAccessed.year">
        </div>
        <h3>Page Range</h3>
        <div class="form-group">
            <label class="form-label">Page From</label>
            <input type="text" class="form-input" v-model="reference.pages.from">
        </div>
        <div class="form-group">
            <label class="form-label">Page To</label>
            <input type="text" class="form-input" v-model="reference.pages.to">
        </div>
        <div class="form-group">
            <label class="form-label">Tags</label>
            <vue-tags-input
                v-model="tag"
                :tags="reference.tags"
                @tags-changed="onTagsChanged"
            />
        </div>
        <button @click="saveReference">Save</button>
    </div>
</template>

<script>
import VueTagsInput from '@johmun/vue-tags-input';

export default {
    props: ['reference'],
    components: {
        VueTagsInput,
    },
    data () {
        return {
            tag: ''
        }
    },
    methods: {
        saveReference () {
            this.$emit('save', this.reference)
        },
        addAuthor () {
            this.reference.authors.push({'name': ''})
        },
        removeAuthor (authorIndex) {
            this.reference.authors.splice(authorIndex, 1)
        },
        onTagsChanged (newTags) {
            this.reference.tags = newTags
        }
    }
}
</script>