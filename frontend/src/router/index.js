import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import AboutUs from '../views/about/AboutUs.vue'
import HowToUse from '../views/about/HowToUse.vue'
import Studying from '../views/room/Studying.vue'
import MakeStudy from '../views/room/MakeStudy.vue'
import StudyBoard from '../views/board/StudyBoard.vue'
import FreeBoard from '../views/board/FreeBoard.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/aboutus',
    name: 'AboutUs',
    component: AboutUs
  },
  {
    path: '/howtouse',
    name: 'HowToUse',
    component: HowToUse
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
