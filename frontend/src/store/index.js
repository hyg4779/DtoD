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
      email: null,
      password: null,
      nickname: null,
      jobs: null,
      skills: null,
    }
  },
  mutations: {
    userCreate(state, payload){
      state.credentials.email = payload.email
      state.credentials.password = payload.password
    },
    nickName(state, payload){
      state.credentials.nickname = payload.nickname
    },
    jobs(state, payload){
      state.credentials.jobs = payload.jobs
    },
    skills(state, payload){
      state.credentials.skills = payload.skills
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
