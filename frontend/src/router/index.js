import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import StudyBoard from '../views/Board/StudyBoard.vue'
import FreeBoard from '../views/Board/FreeBoard.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/studyboard',
    name: 'StudyBoard',
    component: StudyBoard
  },
  {
    path: '/freeboard',
    name: 'FreeBoard',
    component: FreeBoard
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
