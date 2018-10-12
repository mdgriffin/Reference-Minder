<template>
<div>
    <ul>
        <li v-for="tag in tags">{{tag.name}} ({{tag.value}})</li>
    </ul>
</div>
</template>

<script>
export default {
    name: 'tag-list',
    props: ['tags']
}
</script>