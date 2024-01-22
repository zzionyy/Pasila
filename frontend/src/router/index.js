
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import JoinView from '../views/JoinView.vue';
import FindPwView from '../views/FindPwView.vue';
import FindPwSendView from '@/views/FindPwSendView.vue';
import LiveView from '../views/LiveView.vue'
import ScheduleView from '../views/ScheduleView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/join',
      name: 'join',
      component: JoinView,
    },
    {
      path: '/findpw',
      name: 'findpw',
      component: FindPwView,
    },
    {
      path: '/findpw/send',
      name: 'findpwsend',
      component: FindPwSendView,
    },{
      path: '/live/:id',
      name: 'live',
      component: LiveView
    },
    {
      path: '/schedule',
      name: 'schedule',
      component: ScheduleView
    }
  ]
})

export default router