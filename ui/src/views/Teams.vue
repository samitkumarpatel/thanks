<template>
    <div id="ui_teams">
        <ApplicationError :details="errorTxt"/>
        <h2>{{title}}</h2>
        <hr/>
        <section v-if="data">
            <Success :success="data" component_type="TEAM"/>
        </section>
        <section v-else>
            <div class="row">
                <div class="col-6">
                    <form v-on:submit.prevent="submit">
                        <div class="form-group">
                            <label for="teamName">Name of the team</label>
                            <input type="text" class="form-control" id="teamName" v-model="team.name" :class="team.name ? 'valid' : 'invalid'">
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea class="form-control" id="description" v-model="team.description" />
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="teamId">Team Id</label>
                                <input type="text" class="form-control" id="teamId" v-model="team.teamid" :class="team.teamid ? 'valid' : 'invalid'">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="inputState">admin</label>
                                <select id="inputState" class="form-control" v-model="team.adminUid">
                                    <option disabled value="">Choose a admin...</option>
                                </select>
                            </div>
                            <div class="form-group col-md-2">
                               
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form>
                </div>
                <div class="col"></div>
            </div>
        </section>
    </div>
</template>

<script>
import ApplicationError from '@/components/ApplicationError.vue';
import TeamApiService from "@/service/team.service.js";
import Success from "@/views/Success.vue"
export default {
    name : 'teams',
    components: {
        ApplicationError,Success
    },
    data() {
        return {
            title: 'Fill to create a team',
            errorTxt: {},
            data: null,
            team : {
                teamid : null,
                name : null,
                adminUid : null,
                description: null
            }
        }   
    },methods: {
        hasError(err){
            this.errorTxt = {
                error: true,
                status: 500,
                message: err.message
            };
        },
        submit() {
            var that=this;
            TeamApiService.save(this.team,function(res,err){
                if(err){
                    that.hasError(err);
                }else{
                   that.data=res.data;
                }
            })
        }
    },created : function(){
      
    }
}
</script>

<style scoped>
    input.invalid {
        border-left: 5px solid red;
    }
    input.valid {
        border-left: 5px solid green;
    }
</style>