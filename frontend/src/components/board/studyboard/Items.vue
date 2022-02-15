<template>
  <div>
    <header>
      <form>
        <select v-model="selected" class='form-select'>
          <option disabled value>선택</option>
          <option value="title">제목</option>
          <option value="person">작성자</option>
        </select>
        <input
          class="form-control"
          v-model="search"
          type="text"
        >
        <button id="sub" @click="searchSome">검색🔍</button>
      </form>
    </header>

    <body>
      <Item 
        class="item"
        v-for="(item, idx) in paginatedData"
        :key="idx"
        :item="item"
      />
    </body>

    <footer>
      <div v-if="this.items.length != 0" class="btn-cover" style="color:white;">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          이전
        </button>
        <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
          다음
        </button>
      </div>
      <div v-else class="nodata">
        작성한 게시글이 없습니다
      </div>
    </footer>
  </div>
</template>

<script>
import Item from './Item.vue'
import axios from 'axios'
import { api } from '../../../../api.js'
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
        // this.items = this.itembox
        this.items.forEach((element)=>{
          // console.log(element)
          const title = element.sboardTitle
          if (title.indexOf(keyword) !== -1) {
            // console.log(title)
            temp.push(element)   
          }
          this.items = temp
        })
      } 
      else {
        const token = localStorage.getItem('jwt')
        axios({
          url: api.GET_STUDY_BOARD,
          method: 'GET',
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          // console.log(res)
          for (let i=0; i < res.data.length; i++) {
            if (res.data[i].user.userName.indexOf(keyword) !== -1) {
              temp.push(res.data[i])
            }
          }
          this.items = temp
          // console.log(this.items)
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
      const sortedItems = _.sortBy(this.items, 'id').reverse()
      return sortedItems.slice(start, end);
    },
  },
  created(){
    // console.log(this.items)
    this.itembox = this.items
    // console.log(this.itembox)
  }
}
</script>

<style scoped>
header{
  display: flex;
  justify-content: flex-end;
}

header form {
  display: flex;
  align-items: center;
  margin: 0 1vw 3vh 0;
}

header form select {
  height: 5vh;
  width: 13vh;
  font-size: 1.7vh;
}

.form-control {
  height: 5vh;
  width: 30vh;
  margin: 0 0.1vw 0 0;
}

#sub {
  font-size: 1vw;
  font-weight: bold;
  padding: 1vh 1vw 1vh 1vw;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
  background-color: rgb(250, 250, 250);
}

#sub:hover{
  transition: all 0.3s ease 0s;
  background-color: rgba(250, 250, 250, 0.5);
}

body {
  margin: auto;
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

.item:hover{
  transform: scale(1.05);
  transition: all 0.3s ease 0s;
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