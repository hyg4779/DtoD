<template>
  <div class="item" @click="getItemDetail()">
    <div
      class="imgBox"
    >
      <img
        v-for="(skill,idx) in myStacks"
        :key="idx"
        id="stackImg"
        :src="require(`../../assets/stacks/${skill}.png`)"
        alt="stack_logo"
      >
    </div>
    <div class="item-title">
      <p>{{ item.title }}</p>
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
      />
    </b-modal>
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
    myStacks(){
      console.log(this.item.stacks)
      return this.item.stacks
    }
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
.imgBox{
  display: flex;
  flex-direction: row-reverse;
  justify-content: center;
  flex-wrap: nowrap;
  padding: 15px 15px 0 15px;
  height: 40%;
}

#stackImg {
  width: 50%;
  height: 50%;
  margin: 5%;
}

.item{
  width: 13vw;
  height: 26.5vh;
  cursor: pointer;
  /* border: 1px solid; */
  border-radius: 2rem;
  box-shadow: 5px 5px 5px rgb(122, 122, 122);
  background-color: white;
}


.item .item-title {
  text-align: center;
  font-size: 0.78vw;
  margin: 2.5vh 0 0 0;
}
</style>