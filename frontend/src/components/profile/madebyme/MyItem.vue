<template>
  <div>
    <br>
    <swiper-slide>
      <div class="item" @click="getItemDetail()">
        <div class="item-img" :style="style">
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
          <MyItemDetail 
            :item = this.item
            @close-modal="closeModal"
          />
        </b-modal>
      </div>
    </swiper-slide>
    <br>
  </div>
</template>

<script>
import MyItemDetail from './MyItemDetail.vue'

export default {
  name: 'MyItem',
  components: {
    MyItemDetail
  },
  props: {
    item: Object,
  },
  data() {
    return {
      imgs: null,
      imgPath: '',

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
    // console.log(this.item)

    let stacks = this.item.roomTechstack
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
    this.imgPath = this.item.roomImg
    this.style.backgroundColor = this.item.roomImg
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
  margin: 0 auto;
}

.item .item-title {
  text-align: center;
  font-size: 0.78vw;
  margin: 2.5vh 0 0 0;
  font-family: 'Epilogue', sans-serif;
}

.item .item-img {
  display: flex;
  border-radius: 2rem 2rem 0 0;
  flex-direction: row-reverse;
  justify-content: space-evenly;
  align-items: center;
  flex-wrap: nowrap;
  height: 40%;
}

#stackImg {
  width: 5vh;
}

</style>