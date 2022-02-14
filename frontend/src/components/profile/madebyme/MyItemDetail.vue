<template>
  <div class="itemdetail">
    <div id="left">
      <header>
        {{this.item.title}}
      </header>
      <ul>
        <li>
          스터디장: 
          <span>{{this.item.user_id}}</span>
        </li>
        <li>
          개설날짜: 
          <span>{{this.item.madeDate}}</span>
        </li>
        <li>
          활동요일:
          <span>{{this.item.day}}</span>
        </li>
        <li>
          활동기간: 
          <br>
          <span>{{this.item.ingDate}}</span>
        </li>
        <li>
          현재인원 / 모집인원: 
          <span>{{this.item.people}} / {{this.item.count}}명</span>
        </li>
      </ul>
    </div>

    <div id="right">
      <p>📌 사용스텍</p>
      <div
        class="imgBox"
      >
        <div
          v-for="(skill, idx) in this.item.stacks"
          :key="idx"
          id="imagedatail"
        >
          <img            
            class="mt-2"
            :src="require(`../../../assets/stacks/${skill}.png`)"
            alt="stack_logo"
          >
          <p>{{ skill }}</p>
        </div>
      </div>
      <ul>
        <li>
          설명
          <br>
          <div>
            {{ this.item.content }}
          </div>
        </li>
        <li>
          오픈카카오톡
          <br>
          <div>
            {{ this.item.kakaoCode }}
          </div>
        </li>
      </ul>
      <footer>
        <button id="manageBtn" @click="goManage">관리</button>
        <button id="applyBtn" @click="goVideo">입장</button>
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
  methods:{
    goManage(){
      this.$router.push({name:'EditMyStudy'})
    },
    goVideo(){
      this.$router.push({name:'Video', params:{sessionId: this.item.id}})
    }
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
  font-size: 2.7vw;
  margin: 0 auto;
  text-align: center;
  font-weight: bold;
  border-radius: 1rem;
  box-shadow: 0.2rem 0.2rem 0.2rem rgb(150,150,150);
  /* padding: 0 0 0 1vw; */
  width: 80%;
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
footer button:nth-child(2){
  background-color: #24274A;
  color: white;
}

</style>