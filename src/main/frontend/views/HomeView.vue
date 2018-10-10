<template>
  <div class="home">
    <h1>Home Page</h1>
    <reference-form :reference="emptyReference"></reference-form>
  </div>
</template>

<script>
import ReferenceForm from '../components/reference-form.vue';

export default {
  name: 'home-view',
    components: {
    'reference-form': ReferenceForm
  },
  data() {
    return {
        emptyReference: {
            title: "",
            pages: {
                from: 20,
                to: 25
            },
            date: {
                day: 1,
                month: 2,
                year: 3
            },
            dateAccessed: {
                day: 1,
                month: 2,
                year: 3
            }
        }
    }
  }
}
</script>