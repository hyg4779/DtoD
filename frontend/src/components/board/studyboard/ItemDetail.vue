<template>
  <div class="itemdetail">
    <header>
      <h3>{{title}}</h3>
      <div class="profileicon">
        <img v-if="itemuserImg" :src="itemuserImg"> 
        <img v-else src="../../../assets/default_user.png">
      </div>
      <p>{{itemuserName}}</p>
    </header>

    <body>
      <nav>
        <div id="stack">
          <h6 class="p-0 ms-3 mt-2">기술 스택</h6>
          <span
            v-for="(item, idx) in getSkills"
            :key="idx"
          >
            {{item}}
          </span>
        </div>
        <div class="btnGroup" v-if="userName === itemuserName">
          <button class="myBtn" id="up" @click="updateArticle">수정</button>
          <button class="myBtn" id="de" @click="deleteArticle">삭제</button>
        </div>
      </nav>
      <br>
      <section>
        <div class="img-box" :style="style">
          <img 
          v-for="(stack, idx) in imgs"
          :key="idx"
          id="stackImg"
          :src="require(`@/assets/stacks/${stack}.png`)"
          alt="img"
          >
        </div>
        <ul class="etc-box">
          <li>
            <p>모집인원</p>
            <p>{{peopleCount}}명</p>
          </li>
          <li>
            <p>모집기간</p>
            <p>{{joinDate}}</p>
          </li>
          <li>
            <p>수행기간</p>
            <p>{{ingDate}}</p>
          </li>
        </ul>
      </section>
        <ul>
          <li>
            <p>스터디 소개</p>
            <p v-html="getContent(this.content2)"></p>
          </li>
          <li>
            <p>스터디 규칙</p>
            <p v-html="getContent(this.content3)"></p>
          </li>
          <li>
            <p>오픈 카카오톡</p>
            <p v-html="getContent(this.content1)"></p>
          </li>
        </ul>
      
    </body>
    <footer>
      <StudyComment 
        v-for="(comment, idx) in this.comments"
        :key="idx"
        :comment="comment"
        :item_pk="item_pk"
        @onParentDeleteComment="onParentDeleteComment"
      />
      <hr v-if="this.comments.length !== 0" style="margin: 8px;">
      <form @submit="commentSubmit">
        <div class="form-group" style="margin-bottom:10px;">
          <textarea 
            class="form-control"
            placeholder="댓글을 남겨주세요" 
            id="comment" 
            rows="2" 
            v-model="mycomment" 
            @keypress.enter="commentSubmit"
            >
          </textarea>
          <button class="myBtn submit" id="sub">등록</button>
        </div>
      </form>
    </footer>
  </div>
  <!-- <div class="commentprofilebox">
    <div class="commentprofileicon">
      <img v-if="userImg" :src="userImg"> 
      <img v-else src="../../../assets/default_user.png">
    </div>
    <div class="commentprofilename">{{userName}}</div>
  </div> -->
</template>

<script>
import { api } from '../../../../api.js'
import axios from 'axios'
import StudyComment from "./StudyComment.vue"

export default {
  name: 'ItemDetail',
  components: {
    StudyComment,
  },
  props: {
    item_pk: Number,
  },
  data() {
    return {
      title: '',
      tech:'',
      content1: '',
      content2: '',
      content3: '',
      joinDate: '',
      ingDate: '',
      peopleCount: 0,
      imgPath: '',
      imgs: null,
      style: {
        backgroundColor: this.imgPath
      },

      itemuserName: '',
      itemuserImg: '',
      itemuserEmail: '',
      userImg: '',
      userName: '',

      mycomment: '',
      comments: [],
    }
  },
  computed: {
    getSkills: function() {
      const t = this.tech
      const temp = t.split(',')
      let res = []
      for(let i = 0; i < temp.length; i++){
        res.push(temp[i])
      }
      return res
    },
  },
  methods:{
    getContent(content) { 
      return content.split('\n').join('<br>'); 
    },
    commentSubmit(event) {
      event.preventDefault()
      if (this.mycomment.length !== 0) {
        const item_pk = this.item_pk
        const token = localStorage.getItem('jwt')
        axios({
          url: api.CREATE_STUDY_BOARD_COMMENT + `${item_pk}`,
          method: 'POST',
          data: {
            scommentContent: this.mycomment
          },
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then(()=>{
          // console.log(res.data)
          axios({
            url: api. GET_STUDY_BOARD_COMMENT + `${item_pk}`,
            method: 'GET',
            headers: {
              Authorization: 'Bearer ' + token
            },
          }).then((res)=>{
              const temp = []
              res.data.forEach((element)=>{
                temp.push(element)
              })
              this.comments = temp
          }).catch((err)=>{
            console.error(err)
          })
        }).catch((err)=>{
          console.error(err)
        })
        this.mycomment = ''
      } 
      else {
        alert("댓글을 입력하세요.")
      }
    },
    onParentDeleteComment() {
      const item_pk = this.item_pk
      const token = localStorage.getItem('jwt')
      axios({
        url: api.GET_STUDY_BOARD_COMMENT + `${item_pk}`,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
          const temp = []
          res.data.forEach((element)=>{
            temp.push(element)
          })
          this.comments = temp
      }).catch((err)=>{
        const temp = []
        this.comments = temp
        console.error(err)
      })
    },
    deleteArticle() {
      const token = localStorage.getItem('jwt')
      axios({
        url: api.DELETE_STUDY_BOARD + `${this.item_pk}`,
        method: 'DELETE',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then(()=>{
        // console.log(res)
        this.$router.go();
      }).catch((err)=>{
        console.error(err)
      })
    },
    updateArticle() {
      this.$emit('update-modal-open', this.item_pk)
    },
  },
  created() {
    const item_pk = this.item_pk
    const token = localStorage.getItem('jwt')

    axios({
      url:  api.GET_STUDY_BOARD_DETAIL + `${this.item_pk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.itemuserEmail = res.data.user.userEmail
      this.itemuserImg = res.data.user.userImg
      this.itemuserName = res.data.user.userName
      this.imgPath = res.data.sboardImg
      this.title = res.data.sboardTitle
      this.peopleCount = res.data.sboardPerson
      this.ingDate = res.data.sboardIngdate
      this.joinDate = res.data.sboardJoindate
      this.tech = res.data.sboardTechstack
      this.content1 = res.data.sboardContent1
      this.content2 = res.data.sboardContent2
      this.content3 = res.data.sboardContent3
      
      let stacks = res.data.sboardTechstack
      // 배열로 저장
      let result = stacks.split(',')
      // console.log(result.length)

      // 기술이 4개 이상이면 3개만 담고 그 이하는 다 담기
      if(result.length >= 4){
        // console.log(result.slice(0,3))
        this.imgs = result.slice(0,3)
      }else{
        this.imgs = result
      }
      this.style.backgroundColor = res.data.sboardImg
      // console.log(this.imgPath)
      // console.log(this.style.backgroundColor)
    }).catch((err)=>{
      console.error(err)
    })

    axios({
      url:  api.USER_INFO_GET,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.userName = res.data.userName
      this.userImg = res.data.userImg
    }).catch((err)=>{
      console.error(err)
    })

    axios({
      url: api.GET_STUDY_BOARD_COMMENT + `${item_pk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      const temp = []
      res.data.forEach((element)=>{
        temp.push(element)
      })
      this.comments = temp
      // console.log(this.comments)
    }).catch((err)=>{
      console.error(err)
    })

  }
}
</script>

<style scoped>
hr{
  margin: 8px;
}

.itemdetail{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: auto;
  height:auto;
  padding:10px !important;
}

header{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 0 2.5vh 0;
}

header h3{
  font-weight: bold;
}

header p {
  font-size: 1rem;
  font-weight: bold;
}

.profileicon {
  margin: 2vh 1vw 2vh 1vw;
  width : 6.5vh;
  height : 6.5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}
.profileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}

body{
  background-color: #FFFFFF !important;
}

nav{
  display: flex;
  justify-content: space-between;
}

#stack{
  display: flex;
  justify-content: flex-start;
  align-items:center;
  font-weight: 400;
  font-size: 1vw;
}

#stack span{
  border: 1px solid #F0F0F0;
  border-radius: 8rem;
  padding: 0 0.5vw 0.5vh 0.5vw;
  margin: 0 0 0 1vw;
  background-color: #F0F0F0;
}

section{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin:  0.6vh 0 2vh 0;
}

.img-box{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin: 1rem;
  padding: 1rem;
  width: 20rem;
  height: 15rem;
  border-radius: 2rem;
  box-shadow: 0.1rem 0.1rem 0.1rem rgba(0, 0, 0, 0.25);
}

.img-box img{
  margin: .5rem;
  width: 5rem;
  height: 5rem;
}

ul{
  list-style-type: none;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-right: 10rem;
  padding-left: 1rem;
  /* height: 15rem; */
}

ul p:nth-child(1){
  font-weight: bold;
  font-size: 1.1vw;
}

.form-group .form-control{
  /* background-color: black; 
  color:white; */
  border-radius: 1rem;
  height: 10vh;
  font-family: 'Epilogue', sans-serif;
}

.commentprofilebox{
  display: flex;
}
.commentprofileicon {
  margin: 0 1vw 2vh 0;
  width : 5vh;
  height : 5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}
.commentprofileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}
.commentprofilename {
  margin: 0.8vh 0 0 0;
  font-size: 1.2vw;
  font-weight: bold;
  font-family: 'Epilogue', sans-serif;
}

.img-etc-box {
  display: flex;
  margin:  0.6vh 0 2vh 0;
  /* width: auto; */
}
.img-etc-box .img-box {
  width: 31vh;
}
.img-etc-box .img-box #stackImg {
  margin: 9vh 0 0 0;
  width: 10vh;
}
.img-etc-box .etc-box {
  margin: 0 0 0 3vw;
}
.img-etc-box .etc-box .people {
  margin: 0 0 5vh 0;
}
.img-etc-box .etc-box .joindate {
  margin: 0 0 5vh 0;
} 
.img-etc-box .etc-box .ingdate {
  margin: 0 0 0 0;
}
.img-etc-box .etc-box .people .peopletitle {
  margin: 0 0 0.5vh 0;
  font-weight: bold;
  font-size: 1vw;
  font-family: 'Epilogue', sans-serif;
}
.img-etc-box .etc-box .joindate .joindatetitle {
  margin: 0 0 0.5vh 0;
  font-weight: bold;
  font-size: 1vw;
  font-family: 'Epilogue', sans-serif;
}
.img-etc-box .etc-box .ingdate .ingdatetitle {
  margin: 0 0 0.5vh 0;
  font-weight: bold;
  font-size: 1vw;
  font-family: 'Epilogue', sans-serif;
}
.img-etc-box .etc-box .people .peoplecontent {
  font-size: 1vw;
  font-family: 'Epilogue', sans-serif;
}
.img-etc-box .etc-box .joindate .joindatecontent{
  font-size: 1vw;
  font-family: 'Epilogue', sans-serif;
}
.img-etc-box .etc-box .ingdate .ingdatecontent {
  font-size: 1vw;
  font-family: 'Epilogue', sans-serif;
}


.btnGroup{
  display: flex;
}

.myBtn {
  border-radius: 1rem;
  color: #FFFFFF;
  cursor: pointer;
  font-size: 0.8vw;
  font-weight: bold;
  margin: 0 0.3vw 0 0.3vw;
  padding: 0 0.5vw 0.1vh 0.5vw;
  transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
  touch-action: manipulation;
  will-change: transform;
}

.myBtn:hover {
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.myBtn:active {
  box-shadow: none;
  transform: translateY(0);
}

#up{
  background-color: rgb(30, 200, 30);
}

#de{
  background-color: rgb(250, 100, 100);
}

form {
  position: relative;
}
#sub {
  background-color: rgb(50,100,250);
  position: absolute;
  right: 0.5vw;
  bottom: 1vh;
  padding: 0.4vh 0.6vw 0.5vh 0.6vw;
}
</style>
