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
.items .item {
  grid-template-columns: auto auto auto;
  grid-gap: 10px 10px;
}

.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
  color: black;
}
</style>