<template>
  <div class="item" @click="getItemDetail()">
    <div class="item-img">
      <img :src="require(`@/assets/color/${imgPath}`)" alt="img">
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
      <ItemDetail
        :item_pk = this.item.sboardId
        @update-modal-open="updateModalOpen"
      />
    </b-modal>
    <b-modal
      ref="updateItem"
      centered
      size="lg"
      hide-footer 
      hide-header
    >
      <UpdateItem
        :itempk="this.item_pk"
        @update-fin="updateFin"
      />
    </b-modal>
  </div>
</template>

<script>
import ItemDetail from './ItemDetail.vue'
import UpdateItem from "./UpdateItem.vue"
import { api } from '../../../../api.js'
import axios from 'axios'

export default {
  name: 'Item',
  components: {
    ItemDetail,
    UpdateItem
  },
  props: {
    item: Object,
  },
  data(){
    return{
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
    updateModalOpen(e) {
      this.getItemPk(e)
      // console.log(this.item_pk)
      this.$refs['detail'].hide()
      this.$refs['updateItem'].show()
    },
    
    updateFin(){
      this.$refs['updateItem'].hide()
      this.$router.go();
      this.$refs['detail'].show()
    },

    getItemPk(e) {
      this.item_pk = e
      return this.item_pk
    },

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