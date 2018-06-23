<template>
    <div id="ui__error">
        <div class="alert alert-danger" role="alert" v-if="error">
            {{value}}
        </div>
    </div>
</template>

<script>
    export default {
        name : 'application_error',
        props: {
            value: {
                type: Object,
                default:{
                    status : 500,
                    message : 'Internal server error'
                } 
            },
            error: {
                type: Boolean,
                default:false 
            }
        }
    }
</script>

<style scoped>
    #ui__error {
        margin-top:5px; 
    }
</style>