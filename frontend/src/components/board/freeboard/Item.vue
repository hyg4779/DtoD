<template>
  <div class="item">
    <q-card class="my-card" @click=getItemDetail()>
      <img src="https://cdn.quasar.dev/img/mountains.jpg">
      <q-card-section class="title">
        <div v-for="(item,idx) in getTitle" :key="idx">{{ item }}</div>
      </q-card-section>
    </q-card>
    <q-dialog
      ref="detail"
    >
      <ItemDetail 
        :item_pk = this.item.id
      />
    </q-dialog>
  </div>
</template>

<script>
import ItemDetail from './ItemDetail.vue'

export default {
  name: 'Item',
  components: {
    ItemDetail
  },
  props: {
    item: Object,
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
.items {
  margin: 25px;
}
.my-card{
  width: 300px;
  height: 250px;
  cursor: pointer;
  border-radius: 25px;
}
.my-card img {
  width: 100%;
  height: 100px;
  object-fit: fill;
}
.title {
  text-align: center;
  padding: 0;
  margin: 20px 0 20px 0;
}
</style>