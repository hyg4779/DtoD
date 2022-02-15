<template>
  <div class="items">
    <div @mouseover="btn=true" @mouseleave="btn=false">
    <swiper :options = "swiperOptions" ref = "slider" >
      <swiper-slide v-for="(item, idx) in items" :key="idx" :item="item">
          <!-- v-for="(item, idx) in items" -->
        <MyQuestion
          id="card"
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
import MyQuestion from './MyQuestion.vue'
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
// import _ from 'lodash'

export default {
  name: 'MyItems',
  components: {
    MyQuestion,
    Swiper,
    SwiperSlide,
  },
  props: {
    items: Array,
  },
  data() {
    return {
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
  created(){
    // console.log(this.items)
  }
}
</script>

<style scoped>
#card:hover{
  transform: scale(1.05);
  transition: all 0.3s ease 0s;
}
</style>