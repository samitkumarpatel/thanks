<template>
  <div class="ui__home">
    <section v-if="errorTxt.error">
      <ApplicationError :details="errorTxt"/>
    </section>
    <section id="tab__view" v-else>
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
          <a class="nav-link" id="home-tab" data-toggle="tab" href="#history" role="tab" aria-controls="history" aria-selected="true">Activity Stream</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" id="contact-tab" data-toggle="tab" href="#points" role="tab" aria-controls="points" aria-selected="false">Points</a>
        </li>
      </ul>
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade" id="history" role="tabpanel" aria-labelledby="history-tab">
            <History :history="activitySteam"/>
        </div>
        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
            <Profile :profile="member"/>
        </div>
        <div class="tab-pane fade show active" id="points" role="tabpanel" aria-labelledby="points-tab">
            <Points :points="member.points" :history="histories" :memberId="member.id"/>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import ApplicationError from "@/components/ApplicationError.vue";
import MemberApiService from "@/service/member.service.js";
import Profile from "@/components/Profile.vue";
import History from "@/components/History.vue";
import Points from "@/components/Points.vue";
import JWTLoginService from "@/service/jwtLogin.service.js";
import HistoryApiService from "@/service/history.service.js";

export default {
  name: "home",
  components: {
    ApplicationError,Profile,History,Points
  },
  data() {
    return {
      title: "dashboard",
      errorTxt: {},
      member: {},
      histories:[],
      activitySteam: []
    };
  },
  methods: {
    hasError(err){
      this.errorTxt = {
          error: true,
          status: 500,
          message: err.message
      };
    },
    init() {
      var that = this;
      JWTLoginService.parseJWT(function(res,err){
        if(err) {
          that.$router.push("/login");
        } else {
          var memberId = res.data.member.id;
          that.$store.commit("setMemberId", memberId);
          that.getMemberById(memberId);
          that.getRecentHistory(memberId);
          that.getActivitySteam(memberId);
        }
      });
    },
    getMemberById(memberId) {
      var that = this;
      MemberApiService.getById(memberId, function(res,err) {
        if (err) {
          that.hasError(err);
        } else {
          that.member = res.data;
          that.$store.commit("setMemberDetails",res.data);
        }
      });
    },
    getRecentHistory(memberId) {
      var that=this;
      HistoryApiService.toMe(memberId,function(res,err) {
          if(err){
              console.log(err);
          }else{
              that.histories = res.data;
          }
      });
    },
    getActivitySteam(memberId) {
      var that=this;
      HistoryApiService.toOther(memberId,function(res,err) {
          if(err){
              console.log(err);
          }else{
              that.activitySteam = res.data;
          }
      });
    }
  },
  created: function() {
    this.init();
  }
};
</script>

<style scoped>
div.col {
  text-align: center;
}
</style>

