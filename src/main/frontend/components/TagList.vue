<template>
<div class="tagList">
    <ul>
        <li v-for="tag in tags">
            <router-link :to="'/tags/' + tag.name">{{tag.name}} ({{tag.value}})</router-link>
        </li>
    </ul>
</div>
</template>

<script>
export default {
    name: 'tag-list',
    props: ['tags']
}
</script>

<style lang="scss">
@import '../styles/variables.scss';

.tagList ul {
    padding: 0;
    margin: 0;
}

.tagList li {
    list-style: none;
}

.tagList a {
    display: block;
    padding: 1em;
    color: #4a4a4a;
    text-decoration: none;
}

.tagList a:hover {
    background: $secondary_colour;
    color: #fff;
}
</style>