<template>
    <div id="ui__error">
        <div class="alert alert-danger" role="alert" v-if="details.error">
            <h4 class="alert-heading">{{details.status}}</h4>
            <p>{{details.message}}</p>
        </div>
    </div>
</template>

<script>
    export default {
        name : 'application_error',
        props: {
            details: {
                type: Object,
                default:{
                    error : false,
                    status : 500,
                    message : 'Internal server error'
                } 
            }
        }
    }
</script>

<style scoped>
    #ui__error {
        margin-top:5px; 
    }
</style>