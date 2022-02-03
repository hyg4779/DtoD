import Vue from 'vue'
import Vuex from 'vuex'
// import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
  // plugins: [
  //   createPersistedState(),
  // ],  
  state: {
    credentials:{
      // email: null,
      // password: null,
      // nickname: null,
      // jobs: null,
      // skills: null,
      userEmail: null,
      userPwd: null,
      userName: null,
      userJobs: null,
      userTechstack: null,
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
    // infoIntialize(state){
    //   for(let property in state.credentials){
    //     state.credentials[property] = null
    //     console.log(state.credentials)
    //   }
    // }

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
    // infoIntialize({commit}){
    //   commit('infoIntialize')
    // }

  },
  modules: {
  }
})
