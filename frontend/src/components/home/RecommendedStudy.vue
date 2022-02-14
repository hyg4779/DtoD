<template>
  <div class="recommendedstudy">
    <div class="recommendedtitle">
      <div v-if="this.tokenNum">{{ userName}}님 이런 스터디는 어떠세요?</div>
      <div v-else>방문자님 이런 스터디는 어떠세요?</div>
    </div>
    <div @mouseover="btn=true" @mouseleave="btn=false">
      <swiper :options = "swiperOptions" ref = "slider" >
        <swiper-slide
          id="card"
          v-for="(item, idx) in items"
          :key="idx"
          :item="item">
            <!-- v-for="(item, idx) in items" -->
          <Slider
            :key="idx"
            :item="item"
          />
        </swiper-slide>
        <div 
          v-if="btn"
          class="swiper-button-prev swiper-button-white" 
          slot="button-prev"
          style="color: black;"
          @click = "prev">    
        </div>
        <div 
          v-if="btn"
          class="swiper-button-next swiper-button-white" 
          slot="button-next"
          style="color: black;"
          @click = "next"
          >
      </div>
      </swiper>
    </div>
  </div>
</template>

<script>
import Slider from './Slider.vue'
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import { api } from '../../../api.js'
import axios from 'axios'

export default {
  name: 'RecommendedStudy',
  components: {
    Slider,
    Swiper,
    SwiperSlide,
  },
  props: {
    items: Array,
  },
  data() {
    return {
      tokenNum: '',
      userName: '',

      btn : false,   // slider 양 옆 버튼
      swiperOptions: {
        slidesPerView: 5,
        spaceBetween: 5,
        // loop: true,
        // loopedSlides: 5,
        navigation: {
          nextEl: '#button-next-relacionados',
          prevEl: '#button-prev-relacionados'
        },
      }
    }
  },
  methods: {
    prev(){
      for (let i = 0; i < 5; i++){
        this.$refs.slider.$swiper.slidePrev()
      }
      this.$refs.slider.$swiper.slidePrev()
    },
    next(){
      for (let i = 0; i < 5; i++){
        this.$refs.slider.$swiper.slideNext()
      }
      this.$refs.slider.$swiper.slideNext()
    },
  },
  created() {
    const token = localStorage.getItem('jwt')
    this.tokenNum = token
    if (this.tokenNum) {
      axios({
        url:  api.USER_INFO_GET,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
        // console.log(res)
        this.userName = res.data.userName
      }).catch((err)=>{
        console.error(err)
      })
    }
    else {
      //pass
    }
  }
}
</script>

<style scoped>
.recommendedstudy{
  height: calc(100vh - 7.498vh - 49vh);
  background-color: white;
  margin: 0 auto;
}

.recommendedtitle div {
  margin: 4vh 0 0 2vw;
  font-family: 'Dohyeon';
  font-size: 1.5vw;
}

#card:hover{
  transform: scale(1.05);
  transition: all 0.3s ease 0s;
}
</style>