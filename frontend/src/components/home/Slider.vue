<template>
  <div class="slider">
    <br>
    <swiper-slide>
      <div class="item" @click="getItemDetail()">
        <div class="item-img">
          <img :src="require(`@/assets/color/${imgPath}`)" alt="img">
        </div>
        <div class="item-title">
          <div v-for="(item,idx) in getTitle" :key="idx">{{ item }}</div>
        </div>
        
        <b-modal
        ref="detail" 
        size="lg" 
        class="bg-black"
        hide-footer 
        hide-header
        >
          <SwiperDetail
            :item_pk = this.item.sboardId
          />
        </b-modal>
      </div>
    </swiper-slide>
    <br>
  </div>
</template>

<script>
import SwiperDetail from './SwiperDetail.vue'
import { api } from '../../../api.js'
import axios from 'axios'

export default {
  name: 'Slider',
  components: {
    SwiperDetail
  },
  props: {
    item: Object
  },
  data() {
    return {
      SwiperDetail,
      imgPath: '',
      item_pk: 0,
    }
  },
  computed: {
    getTitle: function() {
      const t = this.item.sboardTitle
      const temp = t.split(' ')
      let res = []
      let tp = ''
      for(let i = 0; i < temp.length; i++){
        if(tp.length + temp[i].length < 28 ){ 
          tp += ' ' + temp[i]
        }else{
          res.push(tp)
          tp = temp[i]
        }
      }
      res.push(tp)
      return res
    },
  },
  methods: {
    getItemDetail() {
      this.$refs['detail'].show()
    },
  },
  created() {
    const itempk = this.item.sboardId
    const token = localStorage.getItem('jwt')
    axios({
      url:  api.GET_STUDY_BOARD_DETAIL + `${itempk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      this.imgPath = res.data.sboardImg
      // console.log(this.imgPath)
    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>
/* .slider{
  margin: 0 0 0 1vw !important;
} */
.item{
  width: 13vw;
  height: 27vh;
  cursor: pointer;
  /* border: 1px solid; */
  border-radius: 2rem;
  box-shadow: 5px 5px 5px rgb(122, 122, 122);
  background-color: white;
}

.item .item-img {
  height: 40%;
}

.item .item-img img {
  width: 100%;
  height: 100%;
  border-radius: 25px 25px 0 0;
  object-fit: fill;

}

.item .item-title {
  text-align: center;
  font-size: 0.78vw;
  margin: 2.5vh 0 0 0;
}

</style>
