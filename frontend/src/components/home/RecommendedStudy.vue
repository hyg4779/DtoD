<template>
  <div class="recommendedstudy">
    <div class="recommendedtitle">
      <div>이런 스터디는 어떠세요?</div>
    </div>
    <div class="slidecontain" @mouseover = "btnOn" @mouseleave = "btnOff">
      <swiper class="slider" :options = "swiperOptions" ref = "slider" >
        <Slider
          v-for="(item, idx) in items"
          :key="idx"
          :item="item"
        />
        <div class="swiper-pagination" slot="pagination"></div>
        <div 
          v-if="buttonOn"
          class="swiper-button-prev swiper-button-white" 
          slot="button-prev"
          style="color: black;"
          @click = "prev">    
        </div>
        <div 
        v-if="buttonOn"
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

export default {
  name: 'RecommendedStudy',
  components: {
    Slider,
  },
  props: {
    items: Array,
  },
  data() {
    return {
      buttonOn : true,
      swiperOptions: {
        slidesPerView: 5,
        spaceBetween: 1000,
        // loop: true,
        navigation: {
          nextEl: '#button-next-relacionados',
          prevEl: '#button-prev-relacionados'
        },
      }
    }
  },
  methods: {
    btnOn(){
      this.buttonOn = true
    },
    btnOff(){
      this.buttonOn = false
    },
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
  }
}
</script>

<style scoped>
.recommendedstudy{
  height: calc(100vh - 7.498vh - 48vh);
  background-color: white;
  margin: 0 auto;
}

.recommendedtitle div {
  margin: 3vh 0 0 2vw;
  font-family: 'Dohyeon';
  font-size: 1.5vw;
}

.slidecontain{
  margin: 2vh 0 0 0;
}
.slider{
  margin: 0 auto 0;
}
</style>