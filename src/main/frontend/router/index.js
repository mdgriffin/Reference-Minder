import Vue from 'vue'
import Router from 'vue-router'
const HomeView = () => import('../views/HomeView.vue')

Vue.use(Router)

export function createRouter () {
  return new Router({
    mode: 'history',
    fallback: false,
    routes: [
      { path: '/', component: HomeView }
    ]
  })
}