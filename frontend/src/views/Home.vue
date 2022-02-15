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
import _ from 'lodash'

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
        url: api.GET_RECOMMEND_STUDY_BOARD,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        // console.log(res)
        const temp = []
        let lodashtemp = []
        res.data.forEach((element)=>{
          // console.log(element)
          if(!temp.includes(element)) {
            temp.push(element)
          }
        })
        // console.log(temp)
        for (let i=0; i < temp.length -1; i ++) {
          if (temp[i].sboardId === temp[i+1].sboardId) {
            temp.splice(i, 1)
            i--;
          }
        }
        // console.log(temp)
        lodashtemp = _.sampleSize(temp, 10)
        this.items = lodashtemp
      }).catch((err)=>{
        console.error(err)
      })
    } else {
      axios({
        url: api.GET_STUDY_BOARD,
        method: 'GET',
        // headers: {
        //   Authorization: 'Bearer ' + token
        // },
      }).then((res)=>{
        // console.log(res)
        const temp = []
        let lodashtemp = []
        res.data.forEach((element)=>{
          temp.push(element)
        })
        lodashtemp = _.sampleSize(temp, 10)
        this.items = lodashtemp
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
