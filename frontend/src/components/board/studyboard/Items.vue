<template>
  <div>
    <div class="items">
      <Item 
        class="item"
        v-for="(item, idx) in paginatedData"
        :key="idx"
        :item="item"
      />
    </div>
    <p></p>
    <div class="btn-cover" style="color:white;">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        다음
      </button>
    </div>
    <p></p>
  </div>
</template>

<script>
import Item from './Item.vue'
import _ from 'lodash'

export default {
  name: 'Items',
  components: {
    Item,
  },
  props: {
    items: Array,
  },
  data() {
    return {
      pageNum: 0,
      pageSize: 6,
    }
  },
  methods: {
    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    },
  },
  computed: {
    pageCount () {
      let listLeng = this.items.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      const sortedItems = _.sortBy(this.items, 'id').reverse()
      return sortedItems.slice(start, end);
    },
  },
  created(){
    // console.log(this.items)
  }
}
</script>

<style scoped>
.items {
  margin: 7vh auto;
  display: grid;
  grid-template-rows: auto auto;
  grid-template-columns: auto auto auto;
  grid-gap: 5vh 0;
  justify-content: space-around;
  /* align-content: stretch; */
}

.btn-cover {
  margin: 3rem auto;
  text-align: center;
  color:white;
}

.btn-cover .page-btn {
  width: 4.5vw;
  height: 4.5vh;
  font-size: 1vw;
  line-height: 3vh;
  letter-spacing: 0.05vw;
  border: 1px solid;
  border-radius: 1rem;
  background-color: #24292F;
  color: white;
}
.btn-cover .page-btn:hover {
  color: #24292F;
  background-color: white;
  transition: .2s;
}

.btn-cover .page-count {
  padding: 0 1rem;
  color: black;
}
</style>