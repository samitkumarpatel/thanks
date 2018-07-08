<template>
    <div id="ui_teams">
        <ApplicationError :details="errorTxt"/>
        <h2>{{title}}</h2>
        <hr/>
        <section>
            <div class="row">
                <div class="col-6">
                    <form v-on:submit.prevent="submit">
                        <div class="form-group">
                            <label for="teamName">Name of the team</label>
                            <input type="text" class="form-control" id="teamName" v-model="team.name">
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea class="form-control" id="description" v-model="team.description" />
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="teamId">Team Id</label>
                                <input type="text" class="form-control" id="teamId" v-model="team.teamid">
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
export default {
    name : 'teams',
    components: {
        ApplicationError
    },
    data() {
        return {
            title: 'Fill to create a team',
            errorTxt: {},
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
            console.log('form submited'+JSON.stringify(this.team));
        }
    },created : function(){
      
    }
}
</script>

<style scoped>

</style>