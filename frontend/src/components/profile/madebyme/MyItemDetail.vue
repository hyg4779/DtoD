<template>
  <div class="itemdetail">
    <div id="left">
      <header>
        {{this.item.roomTitle}}
      </header>
      <ul>
        <li>
          스터디장: 
          <span>{{this.item.user.userName}}</span>
        </li>
        <li>
          개설날짜: 
          <span>{{this.item.roomTime}}</span>
        </li>
        <li>
          활동요일: 
          <span>{{this.days}}</span>
        </li>
        <li>
          활동기간: 
          <br>
          <span>{{this.item.roomIngdate}}</span>
        </li>
        <li>
          모집인원: 
          <span>{{this.item.roomPerson}}명</span>
        </li>
      </ul>
    </div>

    <div id="right">
      <p>📌 사용스텍</p>
      <div
        class="imgBox"
      >
        <div
          v-for="(stack, idx) in imgs"
          :key="idx"
          id="imagedatail"
        >
          <img            
            class="mt-2"
            :src="require(`@/assets/stacks/${stack}.png`)"
            alt="stack_logo"
          >
          <p>{{ stack }}</p>
        </div>
      </div>
      <ul>
        <li>
          설명
          <br>
          <div>
            <span v-html="getContent(this.item.roomContent2)"></span>
          </div>
        </li>
        <li>
          오픈카카오톡
          <br>
          <div>
            <span v-html="getContent(this.item.roomContent1)"></span>
          </div>
        </li>
      </ul>
      <footer>
        <button id="manageBtn" @click="goManage">관리</button>
        <button id="cancelBtn" @click="$emit('close-modal')">닫기</button>
      </footer>
    </div>
    
  </div>
</template>

<script>
export default {
  name: 'MyItemDetail',
  props: {
    item: Object,
  },
  data() {
    return {
      imgs: null,
      days: null,
      
      itemid: null,
      itempassword: null,

      password: null,
    }
  },
  methods:{
    getContent(content) { 
      return content.split('\n').join('<br>'); 
    },
    goManage(){
      this.$router.push({name:'EditMyStudy', params:{sessionId: this.item.roomId}})
    },
  },
  created() {
    // console.log(this.item)

    this.itemid = this.item.roomId
    this.itempassword = this.item.roomPwd

    let stacks = this.item.roomTechstack
    // 배열로 저장
    let result = stacks.split(',')
    // 기술이 4개 이상이면 3개만 담고 그 이하는 다 담기
    if(result.length >= 4){
      // console.log(result.slice(0,3))
      this.imgs = result.slice(0,3)
    }else{
      this.imgs = result
    }

    let days = this.item.roomIngday
    let temp = days.split(',')
    for (let i=0; i < temp.length; i++) {
      if (temp[i] === 'mon') {
        temp[i] = '월'
      }
      if (temp[i] === 'tue') {
        temp[i] = '화'
      }
      if (temp[i] === 'wed') {
        temp[i] = '수'
      }
      if (temp[i] === 'thu') {
        temp[i] = '목'
      }
      if (temp[i] === 'fri') {
        temp[i] = '금'
      }
      if (temp[i] === 'sat') {
        temp[i] = '토'
      }
      if (temp[i] === 'sun') {
        temp[i] = '일'
      }
      if (temp[i] === 'yet') {
        temp[i] = '추후 미정'
      }
    }
    this.days = temp.join(',')
  }
}
</script>

<style scoped>
.itemdetail{
  display: flex;
  flex-direction: row;
  justify-content: center;
}

#left{
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 100%; 
  margin: 1vh;
}

#left header {
  font-size: 1.8vw;
  margin: 0 auto;
  text-align: center;
  font-weight: bold;
  /* border: 1px solid gray; */
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
  /* padding: 0 0 0 1vw; */
  /* width: 90%; */
}

#left ul{
  list-style-type: square;
  margin: 2vh 0 0 0;
}

#left li {
  font-size: 1.3vw;
  font-family: 'Epilogue', sans-serif;
  font-weight: bold;
  padding: 0.3vw;
  margin: 0 0 1vh 0;
}
#left li  span{
  font-size: 1vw;
  font-family: 'Epilogue', sans-serif;
  font-weight: bold;
}

#pwd{
  border: 1px solid;
  border-radius: .5rem;
  box-shadow: 0.1rem 0.1rem 0.1rem rgb(150,150,150);
  /* margin: 1rem 0 1rem 0; */
  font-family: 'Epilogue', sans-serif;
}

#right{
  width: 100%;
  margin: 1.2vh;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

#right p {
  font-size: 1.4vw;
  font-weight: bold;
}

.imgBox{
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
#imagedatail{
  display: flex;
  flex-direction: column;
  align-items: center;
}
#right img {
  width: 9.5vh;
  height: 9.5vh;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
  padding: 0.7vh 0.5vw 0.7vh 0.5vw;
}

#right ul{
  list-style-type: square;
}

#right li {
  font-size: 1.3vw;
  font-weight: bold;
  padding: 0.3vw;
  margin: 1vh 0 0 0;
}

#right li div{
  font-size: 1rem;
  font-weight: bold;
  padding: 0.3rem;
  margin: 0.5vh 0 0.5vh 0;
}

footer{
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}

footer button{
  font-size: 1vw;
  font-weight: bold;
  padding: 1vh 1vw 1vh 1vw;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
}

footer button:nth-child(1){
  background-color: #24274A;
  color: white;
}

</style>