<template>
    <div id="login">
        <div class="login-clean">
            <form @submit.prevent="doLogin">
                <h2 class="sr-only">Login Form</h2>
                <div class="illustration"><i class="icon ion-ios-navigate"></i></div>
                <div class="form-group">
                    <input class="form-control" type="text" name="empid" placeholder="empid" v-model="empid">
                </div>
                <div class="form-group">
                    <input class="form-control" type="password" name="password" placeholder="Password" v-model="password">
                </div>
                <div class="form-group">
                    <button class="btn btn-primary btn-block" type="submit">Log In</button>
                </div>
                New ? <router-link to="/members">Click</router-link> to signUp <hr/>
                <span v-if="authenticationError">empId or password not Match! (please try again)</span>
                <span v-if="status==500">We are experiencing anissue , please try later!</span>
            </form>
        </div>
    </div>
</template>

<script>
import ApplicationError from "@/components/ApplicationError.vue";
import MemberApiService from "@/service/member.service.js";
import JWTLoginService from "@/service/jwtLogin.service.js";

export default {
  name: "login",
  components: {
    ApplicationError
  },
  data() {
    return {
      empid: null,
      password: null,
      authenticationError: false,
      status: null
    };
  },
  methods: {
    doLogin() {
      var that = this;
      /*
      MemberApiService.logon(this.empid, this.password, function(res, err) {
        if (err) {
          that.authenticationError = true;
          that.status = err.status;
          that.empid=null;
          that.password=null;
        } else {
          that.$store.commit("setMemberId", res.data.id);
          that.$store.commit("setMemberDetails",res.data);
          that.$router.push("/");
        }
      });*/
      JWTLoginService.login(this.empid,this.password,function(res,err){
        if(err){
          that.authenticationError = true;
          that.status = err.status;
          that.empid=null;
          that.password=null;
        }else{
          JWTLoginService.setJWTInLocalStorage(res.data.jsonToken);
          that.$store.commit("setJwtToken", res.data.jsonToken);
          that.$router.push("/");
        }
      });
    },
    signUp() {
      this.$router.push("/team");
    }
  }
};
</script>

<style scoped>
.login-clean {
  padding: 80px 0;
}

.login-clean form {
  max-width: 320px;
  width: 90%;
  margin: 0 auto;
  background-color: #ffffff;
  padding: 40px;
  border-radius: 4px;
  color: #505e6c;
  box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.1);
}

.login-clean .illustration {
  text-align: center;
  padding: 0 0 20px;
  font-size: 100px;
  color: rgb(244, 71, 107);
}

.login-clean form .form-control {
  background: #f7f9fc;
  border: none;
  border-bottom: 1px solid #dfe7f1;
  border-radius: 0;
  box-shadow: none;
  outline: none;
  color: inherit;
  text-indent: 8px;
  height: 42px;
}

.login-clean form .btn-primary {
  background: #f4476b;
  border: none;
  border-radius: 4px;
  padding: 11px;
  box-shadow: none;
  margin-top: 26px;
  text-shadow: none;
  outline: none !important;
}

.login-clean form .btn-primary:hover,
.login-clean form .btn-primary:active {
  background: #eb3b60;
}

.login-clean form .btn-primary:active {
  transform: translateY(1px);
}

.login-clean form .forgot {
  display: block;
  text-align: center;
  font-size: 12px;
  color: #6f7a85;
  opacity: 0.9;
  text-decoration: none;
}

.login-clean form .forgot:hover,
.login-clean form .forgot:active {
  opacity: 1;
  text-decoration: none;
}
</style>