<template>
  <div class="home">
    <Carousel />
    <RecommendedStudy 
      :items="this.items"
    />
  </div>
</template>

<script>
import Carousel from '@/components/home/Carousel.vue'
import RecommendedStudy from '@/components/home/RecommendedStudy.vue'
import axios from 'axios'
import { api } from '../../api.js'

export default {
  name: 'Home',
  components: {
    Carousel,
    RecommendedStudy,
  },
  data() {
    return {
      items: [],
      stacks: [],
    }
  },
  created() {
    const token = localStorage.getItem('jwt')
    if (token) {
      axios({
        url: api.GET_STUDY_BOARD,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        const temp = []
        res.data.forEach((element)=>{
          temp.push(element)
        })
        this.items = temp
        // console.log(this.items)
      }).catch((err)=>{
        console.error(err)
      })
    } else {
      axios({
        url: api.GET_STUDY_BOARD,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        const temp = []
        res.data.forEach((element)=>{
          temp.push(element)
        })
        this.items = temp
        // console.log(this.items)
      }).catch((err)=>{
        console.error(err)
      })
    }
  }
}
</script>

<style>
.home {
  background-color: white;
}
</style>
