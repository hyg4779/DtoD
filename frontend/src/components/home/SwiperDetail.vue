<template>
  <div class="itemdetail">
    <div class="cardmodal">
      <div class="detailtitle">
        {{this.title}}
      </div>
      <div class="profilebox">
        <div class="profileicon">
          <img v-if="this.itemuserImg" :src="this.itemuserImg"> 
          <img v-else src="../../assets/default_user.png">
        </div>
        <div class="profilename">{{itemuserName}}</div>
      </div>
      <div class="tech-control">
        <div class="techstack">
          기술 스택
          <span v-for="(item, idx) in getSkills" :key="idx">
            {{item}}
          </span>
        </div>
      </div>
      <div class="img-etc-box">
        <div class="img-box">
          <img :src="require(`@/assets/color/${imgPath}`)" alt="img">
        </div>
        <div class="etc-box">
          <div class="people">
            <div class="peopletitle">모집인원</div>
            <div class="peoplecontent">
            {{peopleCount}}명
            </div>
          </div>         
          <div class="joindate">
            <div class="joindatetitle">모집기간</div>
            <div class="joindatecontent">
            {{joinDate}}
            </div>
          </div>
          <div class="ingdate">
            <div class="ingdatetitle">수행기간</div>
            <div class="ingdatecontent">
            {{ingDate}}
            </div>
          </div>
        </div>
      </div>
      <div class="content1">
        <div class="contenttitle1">
          스터디 소개
        </div>
        <div class="contentdetail1">
          <p v-html="getContent(this.content2)"></p>
        </div>
      </div>
      <div class="content2">
        <div class="contenttitle2">
          스터디 규칙
        </div>
        <div class="contentdetail2">
          <p v-html="getContent(this.content3)"></p>
        </div>
      </div>
      <div class="content3">
        <div class="contenttitle3">
          오픈 카카오톡
        </div>
        <div class="contentdetail3">
          <p v-html="getContent(this.content1)"></p>
        </div>
      </div>
      <div v-if="this.token">
      <hr>
        <StudyComment
          v-for="(comment, idx) in this.comments"
          :key="idx"
          :comment="comment"
          :item_pk="item_pk"
          @onParentDeleteComment="onParentDeleteComment"
        />
      <hr>
      </div>
      <div v-if="this.token">
        <div class="commentprofilebox">
          <div class="commentprofileicon">
            <img v-if="this.userImg" :src="this.userImg"> 
            <img v-else src="../../assets/default_user.png">
          </div>
          <div class="commentprofilename">{{userName}}</div>
        </div>
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
            <button class="submit">등록</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { api } from '../../../api.js'
import axios from 'axios'


export default {
  name: 'ItemDetail',
  components: {
  },
  props: {
    item_pk: Number,
  },
  data() {
    return {
      token: '',

      title: '',
      tech:'',
      content1: '',
      content2: '',
      content3: '',
      joinDate: '',
      ingDate: '',
      peopleCount: 0,

      itemuserName: '',
      itemuserImg: '',
      userImg: '',
      userName: '',

      myComments: '',
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
    commentSubmit() {
      
    },
    onParentDeleteComment() {

    },
  },
  created() {
    const token = localStorage.getItem('jwt')
    this.token = token
    axios({
      url:  api.GET_STUDY_BOARD_DETAIL + `${this.item_pk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.itemuserName = res.data.user.userName
      this.itemuserImg = res.data.user.userImg
      this.imgPath = res.data.sboardImg
      this.title = res.data.sboardTitle
      this.peopleCount = res.data.sboardPerson
      this.ingDate = res.data.sboardIngdate
      this.joinDate = res.data.sboardJoindate
      this.tech = res.data.sboardTechstack
      this.content1 = res.data.sboardContent1
      this.content2 = res.data.sboardContent2
      this.content3 = res.data.sboardContent3
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

  }
}
</script>

<style scoped>
.itemdetail{
  width: auto; 
  height:auto; 
  border-radius: 20px !important; 
  padding:10px !important;
  font-size: 20px;
}
.detailtitle {
  text-align: center;
  font-size: 1.5vw;
  font-weight: bold;
}
.profilebox{
  display: flex;
}
.profileicon {
  margin: 2vh 1vw 2vh 17vw;
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
.profilename {
  margin: 3.5vh 0 0 0;
  font-size: 1.2vw;
  font-weight: bold;
}

.tech-control {
  display: flex;
  justify-content: space-between;
}

.techstack {
  font-weight: 400;
  font-size: 1vw;
  margin:  0.6vh 0 2vh 0;
}
.techstack span {
  border: 1px solid #F0F0F0;
  border-radius: 8rem;
  padding: 0.1vh 0.5vw 0.5vh 0.8vw;
  margin: 0 0 0 1vw;
  background-color: #F0F0F0;
  font-weight: bold;
  
}
.item-control .update{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: #24274A;
  height: 3vh;
  width: 3vw;
  margin: 0 1vw 0 0;
  border: 1px solid;
  background-color: white;
  border-radius: 1.1rem;
}
.item-control .delete{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: white;
  height: 3vh;
  width: 3vw;
  /* margin: 0 1vw 0 0; */
  background-color: #24274A;
  border-radius: 1.1rem;
}

.content1 .contenttitle1{
  font-weight: bold;
  font-size: 1.1vw;
  margin: 1.5vh 0 1vh 0;
}
.content1 .contentdetail1 {
  font-weight:300;
  margin: 0 0 2vh 0;
}

.content2 .contenttitle2{
  font-weight: bold;
  font-size: 1.1vw;
  margin: 1.5vh 0 1vh 0;
}
.content2 .contentdetail2 {
  font-weight:300;
  margin: 0 0 2vh 0;
}

.content3 .contenttitle3{
  font-weight: bold;
  font-size: 1.1vw;
  margin: 1.5vh 0 1vh 0;
}
.content3 .contentdetail3 {
  font-weight:300;
  margin: 0 0 2vh 0;
}

.form-group .form-control{
  /* background-color: black; 
  color:white; */
  border-radius: 1rem;
  height: 10vh;
}

.submit {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.85vw;
  font-weight: bold;
  color: white;
  height: 4vh;
  width: 4vw;
  margin: 1vh 0 0 0;
  background-color: #24274A;
  border-radius: 1.1rem;
}

.commentprofilebox{
  display: flex;
}
.commentprofileicon {
  margin: 0 1vw 1vh 0;
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
  margin: 0.7vh 0 0 0;
  font-size: 1.2vw;
  font-weight: bold;
}

.img-etc-box {
  display: flex;
  margin:  0.6vh 0 2vh 0;
}
.img-etc-box .img-box img{
  height: 30vh;
  width: 30vh;
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
}
.img-etc-box .etc-box .joindate .joindatetitle {
  margin: 0 0 0.5vh 0;
  font-weight: bold;
  font-size: 1vw;
}
.img-etc-box .etc-box .ingdate .ingdatetitle {
  margin: 0 0 0.5vh 0;
  font-weight: bold;
  font-size: 1vw;
}
.img-etc-box .etc-box .people .peoplecontent {
  font-size: 1vw;
}
.img-etc-box .etc-box .joindate .joindatecontent{
  font-size: 1vw;
}
.img-etc-box .etc-box .ingdate .ingdatecontent {
  font-size: 1vw;
}
</style>