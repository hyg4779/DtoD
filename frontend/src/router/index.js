import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Studying from '../views/Room/Studying.vue'
import MakeStudy from '../views/Room/MakeStudy.vue'
import StudyBoard from '../views/Board/StudyBoard.vue'
import FreeBoard from '../views/Board/FreeBoard.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/studying',
    name: 'Studying',
    component: Studying
  },
  {
    path: '/makestudy',
    name: 'MakeStudy',
    component: MakeStudy
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
