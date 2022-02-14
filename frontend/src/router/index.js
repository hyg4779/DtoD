import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AboutUs from '../views/about/AboutUs.vue'
import HowToUse from '../views/about/HowToUse.vue'
import Studying from '../views/room/Studying.vue'
import MakeStudy from '../views/room/MakeStudy.vue'
import StudyBoard from '../views/board/StudyBoard.vue'
import FreeBoard from '../views/board/FreeBoard.vue'
import MyProfile from '../views/profile/MyProfile.vue'
import MyStudy from '../views/profile/MyStudy.vue'
import EditMyStudy from '../views/profile/EditMyStudy.vue'
import Page404 from '../views/Page404.vue'
import Video from '../views/Video.vue'

// import Test from '../views/Test.vue'

Vue.use(VueRouter)

const routes = [

  // {
  //   path: '/test',
  //   name: 'Test',
  //   component: Test
  // },
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
    path: '/editmystudy/:sessionId',
    name: 'EditMyStudy',
    component: EditMyStudy
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
  {
    path: '/myprofile',
    name: 'MyProfile',
    component: MyProfile
  },
  {
    path: '/mystudy',
    name: 'MyStudy',
    component: MyStudy
  },
  {
    path: '/404',
    name: 'Page404',
    component: Page404,
  },
  {
    path: '*',
    redirect: '/404'
  },
  {
    path:'/video/:sessionId',
    name: 'Video',
    component: Video,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
