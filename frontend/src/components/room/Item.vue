<template>
  <div class="item" @click="getItemDetail()">
    <div
      class="imgBox"
      :style="style"
    >
      <img
        v-for="(stack, idx) in imgs"
        :key="idx"
        id="stackImg"
        :src="require(`@/assets/stacks/${stack}.png`)"
        alt="img"
      >
    </div>
    <div class="item-title">
      <div v-for="(item,idx) in getTitle" :key="idx">{{ item }}</div>
    </div>
    <b-modal
      ref="detail"
      centered
      hide-footer 
      hide-header
      size="lg"
    >
      <ItemDetail 
        :item = this.item
        @close-modal="closeModal"
      />
    </b-modal>
  </div>
</template>

<script>
import ItemDetail from './ItemDetail.vue'
import { api } from '../../../api.js'
import axios from 'axios'

export default {
  name: 'Item',
  components: {
    ItemDetail
  },
  props: {
    item: Object,
  },
  data(){
    return{
      imgs: null,
      imgPath: '',
      item_pk: 0,

      style: {
        backgroundColor: this.imgPath
      }
    }
  },
  computed: {
    getTitle: function() {
      const t = this.item.roomTitle
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
    closeModal(){
      this.$refs['detail'].hide()
    }
  },
  created() {
    const itempk = this.item.roomId
    const token = localStorage.getItem('jwt')
    axios({
      url:  api.GET_STUDY_ROOM_DETAIL + `${itempk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res.data)
      // db에 저장된 item의 기술스텍 가져오기
      let stacks = res.data.roomTechstack
      // 배열로 저장
      let result = stacks.split(',')
      // console.log(result.length)

      // 기술이 4개 이상이면 3개만 담고 그 이하는 다 담기
      if(result.length >= 4){
        // console.log(result.slice(0,3))
        this.imgs = result.slice(0,3)
      }else{
        this.imgs = result
      }
      this.imgPath = res.data.roomImg
      this.style.backgroundColor = res.data.roomImg
      // console.log(this.imgPath)
      // console.log(this.style.backgroundColor)
    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>
.item{
  width: 13vw;
  height: 27vh;
  cursor: pointer;
  /* border: 1px solid; */
  border-radius: 2rem;
  box-shadow: 5px 5px 5px rgb(122, 122, 122);
  background-color: white;
}

.imgBox{
  display: flex;
  border-radius: 2rem 2rem 0 0;
  /* background-color: ; */
  flex-direction: row-reverse;
  justify-content: space-evenly;
  align-items: center;
  flex-wrap: nowrap;
  height: 40%;
}

#stackImg {
  width: 5vh;
}

.item .item-title {
  text-align: center;
  font-size: 0.78vw;
  margin: 2.5vh 0 0 0;
  font-family: 'Epilogue', sans-serif;
}

</style>