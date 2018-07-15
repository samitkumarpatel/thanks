import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    memberId : null,
    memberDetails : null
  },
  mutations: {
    setMemberId(state,payload){
      state.memberId=payload;
    },
    setMemberDetails(state,payload){
      state.memberDetails=payload;
    }
  }
})
