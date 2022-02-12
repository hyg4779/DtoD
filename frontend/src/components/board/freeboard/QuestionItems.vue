<template>
  <div>
    <form @submit="searchSome" class="form">
      <select v-model="selected" name="kind" class='form-select'>
        <option disabled value>선택</option>
        <option value="title">제목</option>
        <option value="person">글쓴이</option>
      </select>
      <input class="form-control" v-model="search" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-success" type="submit">Search</button>
    </form>
    <div class="items">
      <QuestionItem 
        class="item"
        v-for="(item, idx) in paginatedData"
        :key="idx"
        :item="item"
      />
    </div>
    <p></p>
    <div v-if="this.items.length != 0" class="btn-cover" style="color:white;">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        다음
      </button>
    </div>
    <div v-else>
      <div class="nodata">
        작성한 게시글이 없습니다
      </div>
    </div>
    <p></p>
  </div>
</template>

<script>
import QuestionItem from './QuestionItem.vue'
import axios from 'axios'
import { api } from '../../../../api.js'
import _ from 'lodash'

export default {
  name: 'Items',
  components: {
    QuestionItem ,
  },
  props: {
    items: Array,
  },
  data() {
    return {
      pageNum: 0,
      pageSize: 6,

      selected: '',
      search: '',
      itembox: null,
    }
  },
  methods: {
    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    },
    searchSome(event) {
      event.preventDefault()
      const keyword = this.search
      const temp = []
      this.items = this.itembox
      if (this.selected === '') {
        alert("카테고리를 선택해주세요")
      }
      else if (this.selected === 'title') {
        this.items.forEach((element)=>{
          console.log(element)
          const title = element.cboardTitle
          if (title.indexOf(keyword) !== -1) {
            temp.push(element)   
          }
          this.items = temp  
        })
      } 
      else {
        const token = localStorage.getItem('jwt')
        axios({
          url: api.GET_FREE_BOARD,
          method: 'GET',
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          // console.log(res)
          for (let i=0; i < res.data.length; i++) {
            if (res.data[i].cboardType === '질문') {
              if (res.data[i].user.userName.indexOf(keyword) !== -1) {
                temp.push(res.data[i])
              }
            }
          }
          this.items = temp
        }).catch((err)=>{
          console.error(err)
        }) 
      }
      this.selected = ''
      this.search = ''
    }
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
      const sortedItems = _.sortBy(this.items, 'cboardId').reverse()
      return sortedItems.slice(start, end);
    },
  },
  created(){
    // console.log(this.items)
    this.itembox = this.items
  }
}
</script>

<style scoped>
.form {
  display: flex;
  margin: 0 0 0 60vw;
}

.form .form-select {
  height: 5vh;
  width: 13vh;
  font-size: 1.7vh;
}

.form .form-control {
  height: 5vh;
  width: 30vh;
  margin: 0 0.1vw 0 0;
}

.form button {
  height: 5vh;
  margin: 0 1vw 0 0;
}

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

.nodata {
  margin: 23vh 0 0 33vw;
  font-family: 'Dohyeon';
  font-size: 2vw;
}
</style>