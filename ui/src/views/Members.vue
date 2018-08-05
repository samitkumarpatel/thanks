<template>
    <div id="ui_members">
        <ApplicationError :details="errorTxt"/>
        <h2>{{title}}</h2>
        <hr/>
        <section v-if="response">
            <Success :success="response"/>
        </section>
        <section v-else>
            <div class="row">
                <div class="col">
                    <form v-on:submit.prevent="submit">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="firstname">Firstname</label>
                                <input type="text" class="form-control" id="firstname" v-model="member.firstname" :class="member.firstname ? 'valid' : 'invalid'">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="lastname">Lastname</label>
                                <input type="text" class="form-control" id="lastname" v-model="member.lastname" :class="member.lastname ? 'valid' : 'invalid'">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="role">Country</label>
                                <input type="text" class="form-control" id="country" v-model="member.address.country">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="team">City</label>
                                <input type="text" class="form-control" id="city" v-model="member.address.city">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="empid">Pin Code</label>
                                <input type="text" class="form-control" id="pincode" v-model="member.address.pin">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="role">email</label>
                                <input type="email" class="form-control" id="email" v-model="member.contact.email" :class="member.contact.email ? 'valid' : 'invalid'">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="team">Mobile</label>
                                <input type="tel" class="form-control" id="mobile" v-model="member.contact.mobile">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="empid">Landline</label>
                                <input type="tel" class="form-control" id="llt" v-model="member.contact.llt">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="role">Your Role</label>
                                <input type="text" class="form-control" id="role" v-model="member.role" :class="member.role ? 'valid' : 'invalid'">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="empid">empid</label>
                                <input type="text" class="form-control" id="empid" v-model="member.empid" :class="member.empid ? 'valid' : 'invalid'">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="team">Part of which Team ?</label>
                                <select id="team" class="form-control" v-model="member.teamId">
                                    <option disabled value="">Choose team...</option>
                                    <option v-for="team of teams" :key="team.id" :value="team.id">{{team.name}}</option>
                                </select>
                                <label v-if="teamError" style="color:red">
                                    <small> we are facing issue to finding and creating team ! please try later.. </small>
                                </label>
                                <label for="createTeamLink" v-else>
                                    <small> not in the list? <router-link to="/teams">click</router-link> to create one</small>
                                </label>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="role">Technology stack <br/> <small>seperate them with comma ','</small> </label>
                                <textarea class="form-control" id="techstack" v-model="member.technology"/>
                            </div>
                            <div class="form-group col-md-6"></div>
                        </div>
                        <ul>
                            <li v-for="(ve,index) of validationError" :key="index">{{ve}}</li>
                        </ul>
                        <button type="submit" class="btn btn-primary">Signup</button>
                    </form>
                </div>
                <div class="col-4">
                </div>
            </div>
        </section>
    </div>
</template>

<script>
import ApplicationError from "@/components/ApplicationError.vue";
import MemberApiService from "@/service/member.service.js";
import TeamApiService from "@/service/team.service.js";
import Member from "@/service/member.js";
import Success from "@/views/Success.vue";
import Alert from "@/views/Alert.vue";
import JWTLoginService from "@/service/jwtLogin.service.js";
export default {
  name: "members",
  components: {
    ApplicationError,Success,Alert
  },
  data() {
    return {
      title: "Create Member",
      errorTxt: {},
      teamError : false,
      teams : [],
      member : Member,
      validationError : [],
      response : null
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
    submit(e) {
        //validation check
        this.validationError = [];
        if(!this.member.firstname){
            this.validationError.push('firstname should not be empty');
        }
        if(!this.member.lastname){
            this.validationError.push('lastname should not be empty');
        }
        if(!this.member.contact.email){
            this.validationError.push('email should not be empty');
        }
        if(!this.member.teamId){
            this.validationError.push('team should not be empty');
        }
        if(!this.member.empid){
            this.validationError.push('empId should not be empty');
        }
        if(this.member.technology) {
            var d = this.member.technology;
            this.member.technology=d.split(',');
        }

        //submit the form 
        if(this.validationError.length == 0){
            var that =this;
            MemberApiService.save(this.member,function(success,error){
                if(error){
                    that.hasError(error);
                }else{
                    that.response=success.data;
                }
            });
            return true;
        }
        e.preventDefault();
    }
  },created:function() {
      this.teams = [];
      var that=this;
      TeamApiService.getAll(function(success,error){
          if(error){
              that.teamError=true;
          }else{
              that.teams=success.data;
          }
      });
  },computed: {
      memberId() {
          return this.$store.state.memberId; 
      }
  },
};
</script>

<style scoped>
    input.invalid {
        border-left: 5px solid red;
    }
    input.valid {
        border-left: 5px solid green;
    }
    
</style>