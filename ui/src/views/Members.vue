<template>
    <div id="ui_members">
        <ApplicationError :details="errorTxt"/>
        <h2>{{title}}</h2>
        <hr/>
        <section>
            <div class="row">
                <div class="col">
                        <form v-on:submit.prevent="submit">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="firstname">Firstname</label>
                                    <input type="text" class="form-control" id="firstname" placeholder="firstname" v-model="member.firstname">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="lastname">Lastname</label>
                                    <input type="text" class="form-control" id="lastname" placeholder="lastname" v-model="member.lastname">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="role">Country</label>
                                    <input type="text" class="form-control" id="country" placeholder="country" v-model="member.address.country">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="team">City</label>
                                    <input type="text" class="form-control" id="city" placeholder="city" v-model="member.address.city">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="empid">Pin Code</label>
                                    <input type="text" class="form-control" id="pincode" placeholder="pin code" v-model="member.address.pin">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="role">email</label>
                                    <input type="email" class="form-control" id="email" placeholder="email" v-model="member.contact.email">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="team">Mobile</label>
                                    <input type="text" class="form-control" id="mobile" placeholder="mobile" v-model="member.contact.mobile">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="empid">Landline</label>
                                    <input type="text" class="form-control" id="llt" placeholder="land line" v-model="member.contact.llt">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="role">Your Role</label>
                                    <input type="text" class="form-control" id="role" placeholder="role" v-model="member.role">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="team">Part of which Team ?</label>
                                    <select id="team" class="form-control" v-model="member.team">
                                        <option selected>Choose team...</option>
                                        <option>test</option>
                                    </select>
                                    <label for="createTeamLink">
                                        <small> not in the list? <router-link to="/teams">click</router-link> to create one</small>
                                    </label>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="empid">empid</label>
                                    <input type="text" class="form-control" id="empid" v-model="member.empid">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="role">Technology stack <small>seperate them with comma ,</small> </label>
                                    <textarea class="form-control" id="techstack" placeholder="tech stack" v-model="member.technology"/>
                                </div>
                                <div class="form-group col-md-6"></div>
                            </div>
                            <button type="submit" class="btn btn-primary">Signup</button>
                        </form>
                </div>
                <div class="col-4">
                    <ul>
                        <li v-for="(ve,index) of validationError" :key="index">{{ve}}</li>
                    </ul>
                </div>
            </div>
            
        </section>
    </div>
</template>

<script>
import ApplicationError from "@/components/ApplicationError.vue";
import MemberApiService from "@/service/member.service.js"
export default {
  name: "members",
  components: {
    ApplicationError
  },
  data() {
    return {
      title: "Fill to create a member",
      errorTxt: {},
      member : {
        password : null,
        firstname : null,
        lastname : null,
        role : null,
        team : null,
        empid : null,
        technology : null,
        address : {
            country : null,
            city : null,
            pin : null
        },
        contact : {
            email : null,
            mobile : null,
            llt : null
        }
      },
      validationError : []
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
        if(!this.member.team){
            this.validationError.push('team should not be empty');
        }
        if(!this.member.empid){
            this.validationError.push('empId should not be empty');
        }
        if(!this.member.technology){
            this.validationError.push('technology should not be empty');
        }else{
            var d = this.member.technology;
            this.member.technology = d.split(',');
        }
        //submit the form 
        if(this.validationError.length == 0){
            var that =this;
            MemberApiService.save(this.member,function(success,error){
                if(error){
                    that.hasError(error);
                }else{
                    //TODO success message has be to shown
                    console.log(success);
                }
            })
            return true;
        }
        e.preventDefault();
    }
  }
};
</script>

<style scoped>
</style>