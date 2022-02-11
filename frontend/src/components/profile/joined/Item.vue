<template>
  <div>
    <br>
    <swiper-slide>
      <div class="item" @click="getItemDetail()">
        <div
          class="imgBox"
        >
          <img
            v-for="(skill,idx) in myStacks"
            :key="idx"
            id="stackImg"
            :src="require(`../../../assets/stacks/${skill}.png`)"
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
            @close-modal="closeModal"
          />
        </b-modal>
      </div>
    </swiper-slide>
    <br>
  </div>
</template>

<script>
import ItemDetail from './ItemDetail.vue'

export default {
  name: 'MyItem',
  components: {
    ItemDetail
  },
  props: {
    item: Object,
  },
  computed: {
    myStacks(){
      // console.log(this.item.stacks)
      return this.item.stacks
    }
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

.imgBox{
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