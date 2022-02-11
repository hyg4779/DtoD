import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import { api } from '../../api.js'

// import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

const token = localStorage.getItem('jwt')

export default new Vuex.Store({
  // plugins: [
  //   createPersistedState(),
  // ],  
  state: {
    credentials:{
      userEmail: null,
      userPwd: null,
      userImg: null,
      userJobs: null,
      userName: null,
      userTechstack: null,
    },
    date: {
      joindate: null,
      ingdate: null,
    }
  },
  mutations: {
    userCreate(state, payload){
      state.credentials.userEmail = payload.email
      state.credentials.userPwd = payload.pwd
    },
    nickName(state, payload){
      state.credentials.userName = payload.nickname
    },
    jobs(state, payload){
      state.credentials.userJobs = payload.jobs
    },
    skills(state, payload){
      state.credentials.userTechstack = payload.skills
    },

    joinDate(state, payload){
      state.date.joindate = payload.joindate
    },
    ingDate(state, payload){
      state.date.ingdate = payload.ingdate
    },
    getUserInfo({credentials}, payload){
      credentials.userEmail = payload.userEmail
      credentials.userImg = payload.userImg
      credentials.userJobs = payload.userJobs
      credentials.userName = payload.userName
      credentials.userTechstack = payload.userTechstack
      console.log(credentials)
    }

  },
  actions: {
    userCreate({commit}, payload){
      commit('userCreate', payload)
    },

    nickName({commit}, payload){
      commit('nickName', payload)
    },

    jobs({commit}, payload){
      commit('jobs', payload)
    },

    skills({commit},payload){
      commit('skills', payload)
    },

    joinDate({commit}, payload){
      commit('joinDate', payload)
    },

    ingDate({commit}, payload){
      commit('ingDate', payload)
    },

    getUserInfo({commit}){
      axios ({
      method: 'get',
      url: api.USER_INFO_GET,
      headers: { 
        Authorization: 'Bearer ' + token
      }
      }).then(res=>{

        // 로그인된 사용자 정보
        // console.log(res.data)
        commit('getUserInfo', res.data)
      }).catch(err => {
        console.log(err)
      })
      
    }

  },
  modules: {
  }
})
