<template>
    <div id="app" class="container">
        <div class="sidebar">
            <div class="logo">
                <h1><span class="logo-firstLetter">R</span>eference <span class="logo-firstLetter">M</span>inder</h1>
            </div>
            <h3>Tags</h3>
            <tag-list :tags="tags"></tag-list>
        </div>
        <div class="mainContent">
            <transition name="fade" mode="out-in">
                <router-view class="view" @referencesChanged="onReferencesChanged"></router-view>
            </transition>
        </div>
    </div>
</template>

<script>
import {getReferenceTags} from "./api/reference-api";
import TagList from "./components/TagList.vue";
import './styles/global.scss'

export default {
    name: 'main-app',
    components: {
        TagList
    },
    data() {
        return {
            referenceChanges: 0
        }
    },
    asyncComputed: {
        tags: {
            get() {
                return getReferenceTags();
            },
            watch() {
                this.referenceChanges;
            }
        }
    },
    methods: {
        onReferencesChanged () {
            this.referenceChanges++;
        }
    }
}
</script>


<style lang="scss">
.mainNav li {
  display: inline-block;
}

.mainNav li a {
  display: block;
  padding: 5px;
  font-size: 1.2em;
}
</style>
