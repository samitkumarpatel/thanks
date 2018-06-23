<template>
    <div id="ui_members">
        <ApplicationError :error="error" :value="errorTxt"/>
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
                                <div class="form-group col-md-6">
                                    <label for="role">Role in the team</label>
                                    <input type="text" class="form-control" id="role" placeholder="role">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="team">Select Team 
                                        <small> (Not in the list? <router-link to="/teams">create)</router-link></small>
                                    </label>
                                    <select id="team" class="form-control">
                                        <option selected>Choose team...</option>
                                        <option>test</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="empid">empid</label>
                                    <input type="text" class="form-control" id="empid">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="role">Technology stack <small>(seperate them with ,)</small> </label>
                                    <textarea class="form-control" id="techstack" placeholder="tech stack"/>
                                </div>
                                <div class="form-group col-md-4">
                                   
                                </div>
                                <div class="form-group col-md-2">
                                </div>
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
        status: 500,
        message: "Internal server error"
      },
      error: false
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