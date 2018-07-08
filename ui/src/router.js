import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Teams from './views/Teams.vue'
import Members from './views/Members.vue'
import Login from './views/Login.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
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
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }
  ]
})
