<template>
    <div class=""referenceForm>
        <h2>Reference Form</h2>
        <div class="form-group">
            <label class="form-label">Title</label>
            <input type="text" class="form-input" v-model="reference.title">
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
    </div>
</template>

<script>
    export default {
        props: ['reference']
    }
</script>