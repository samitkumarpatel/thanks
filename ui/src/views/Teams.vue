<template>
    <div id="ui_teams">
        <ApplicationError :error="error" :value="errorTxt"/>
        <h2>{{title}}</h2>
        <hr/>
        <section>
            <div class="row">
                <div class="col-6">
                    <form v-on:submit.prevent="submit">
                        <div class="form-group">
                            <label for="teamName">Name of the team</label>
                            <input type="text" class="form-control" id="teamName" placeholder="Name">
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea class="form-control" id="description" placeholder="about team" />
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="teamId">Team Id</label>
                                <input type="text" class="form-control" id="teamId">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="inputState">admin</label>
                                <select id="inputState" class="form-control">
                                    <option selected>Choose...</option>
                                    <option>...</option>
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
import ApplicationError from '@/components/ApplicationError.vue'
export default {
    name : 'teams',
    components: {
        ApplicationError
    },
    data() {
        return {
            title: 'create a teams',
            errorTxt: {
                status : 500,
                message : 'Internal server error'
            },
            error : false
        }
    },methods: {
        submit() {
            console.log('form submited');
        }
    },
}
</script>

<style scoped>

</style>