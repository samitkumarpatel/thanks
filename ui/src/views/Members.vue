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
                                    <input type="text" class="form-control" id="firstname" placeholder="firstname">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="lastname">Lastname</label>
                                    <input type="text" class="form-control" id="lastname" placeholder="lastname">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="role">Country</label>
                                    <input type="text" class="form-control" id="country" placeholder="country">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="team">City</label>
                                    <input type="text" class="form-control" id="city" placeholder="city">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="empid">Pin Code</label>
                                    <input type="text" class="form-control" id="pincode" placeholder="pin code">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="role">email</label>
                                    <input type="email" class="form-control" id="email" placeholder="email">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="team">Mobile</label>
                                    <input type="text" class="form-control" id="mobile" placeholder="mobile">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="empid">Landline</label>
                                    <input type="text" class="form-control" id="llt" placeholder="land line">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="role">Your Role</label>
                                    <input type="text" class="form-control" id="role" placeholder="role">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="team">Part of which Team ?</label>
                                    <select id="team" class="form-control">
                                        <option selected>Choose team...</option>
                                        <option>test</option>
                                    </select>
                                    <label for="createTeamLink">
                                        <small> not in the list? <router-link to="/teams">click</router-link> to create one</small>
                                    </label>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="empid">empid</label>
                                    <input type="text" class="form-control" id="empid">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="role">Technology stack <small>seperate them with comma ,</small> </label>
                                    <textarea class="form-control" id="techstack" placeholder="tech stack"/>
                                </div>
                                <div class="form-group col-md-6"></div>
                            </div>
                            <button type="submit" class="btn btn-primary">Signup</button>
                        </form>
                </div>
                <div class="col-4"></div>
            </div>
            
        </section>
    </div>
</template>

<script>
import ApplicationError from "@/components/ApplicationError.vue";
export default {
  name: "members",
  components: {
    ApplicationError
  },
  data() {
    return {
      title: "create a member",
      errorTxt: {
        error : false,
        status: 500,
        message: "Internal server error"
      }
    };
  },methods: {
      submit() {
          console.log('form submited');
      }
  },
};
</script>

<style scoped>
</style>