<template>
  <div class="item" @click="getItemDetail()">
    <div class="item-img">
      <img src="https://picsum.photos/600/300/?image=25" alt="img">
    </div>
    <div class="item-title">
      <div v-for="(item,idx) in getTitle" :key="idx">{{ item }}</div>
    </div>
    <b-modal
      ref="detail"
      centered
      size="lg"
      hide-footer 
      hide-header
    >
      <QuestionItemDetail
        :item_pk = this.item.id
      />
    </b-modal>
  </div>
</template>

<script>
import QuestionItemDetail from './QuestionItemDetail.vue'

export default {
  name: 'Item',
  components: {
    QuestionItemDetail
  },
  props: {
    item: Object,
  },
  data () {
    return {

    }
  },
  computed: {
    getTitle: function() {
      const t = this.item.title
      // console.log(t)
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
    // console.log(this.item)
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