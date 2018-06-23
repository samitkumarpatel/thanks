import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import About from './views/About.vue'
import Teams from './views/Teams.vue'
import Members from './views/Members.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      component: About
    },
    {
      path: '/teams',
      name: 'teams',
      component: Teams
    },
    {
      path: '/members',
      name: 'members',
      component: Members
    }
  ]
})