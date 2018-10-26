<template>
<div class="tagList">
    <ul>
        <li><router-link to="/">All References  <i class="fas fa-tags"></i></router-link></li>
        <li v-for="tag in tags">
            <router-link :to="'/tags/' + tag.name">{{tag.name}} ({{tag.value | toInt }})</router-link>
        </li>
    </ul>
</div>
</template>

<script>
export default {
    name: 'tag-list',
    props: ['tags'],
    filters: {
        toInt (value) {
            return parseInt(value)
        }
    }
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