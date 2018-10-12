<template>
  <div id="app" class="container">
    <div class="content">
      <transition name="fade" mode="out-in">
        <router-view class="view"></router-view>
      </transition>
    </div>
  </div>
</template>

<script>
import './styles/global.scss'

export default {
    name: 'main-app'
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
