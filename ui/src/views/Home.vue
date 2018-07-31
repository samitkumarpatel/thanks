<template>
  <div class="ui__home">
    <section v-if="errorTxt.error">
      <ApplicationError :details="errorTxt"/>
    </section>
    <section id="tab__view" v-else>
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item">
          <a class="nav-link" id="home-tab" data-toggle="tab" href="#history" role="tab" aria-controls="history" aria-selected="true">History</a>
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
            <History :history="[1,2,3,4]"/>
        </div>
        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
            <Profile :profile="member"/>
        </div>
        <div class="tab-pane fade show active" id="points" role="tabpanel" aria-labelledby="points-tab">
            <Points :points="member.points"/>
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
export default {
  name: "home",
  components: {
    ApplicationError,Profile,History,Points
  },
  data() {
    return {
      title: "dashboard",
      errorTxt: {},
      member: {}
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
      //service call
      MemberApiService.getById(this.memberId, function(res,err) {
        if (err) {
          that.hasError(err);
          that.$router.push("/login");
        } else {
          that.member = res.data;
        }
      });
    }
  },
  created: function() {
    this.init();
  },computed: {
    memberId() {
        return this.$store.state.memberId; 
    }
  }
};
</script>
<style scoped>
div.col {
  text-align: center;
}
</style>

