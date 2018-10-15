import Vue from 'vue'
import Router from 'vue-router'
const ReferencesView = () => import('../views/ReferencesView.vue')

Vue.use(Router)

export function createRouter () {
  return new Router({
    mode: 'history',
    fallback: false,
    routes: [
      { path: '/', component: ReferencesView},
      { path: '/tags/:tag', component: ReferencesView },
    ]
  })
}