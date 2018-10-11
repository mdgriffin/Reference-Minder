<template>
    <div class="referenceList">
        <div v-for="reference in references">
            <h3>{{reference.title}}</h3>
        </div>
    </div>
</template>


<script>
    import {getReferences} from "../api/reference-api";

    export default {
        props: ['references']


    }

</script>