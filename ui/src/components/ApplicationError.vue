<template>
    <div id="ui__error">
        <div class="alert alert-danger" role="alert" v-if="details.error">
            <i class="material-icons">error</i>
            <span class="alert-heading">{{details.status}}  - {{details.message}}</span>    
        </div>
    </div>
</template>

<script>
    export default {
        name : 'application_error',
        props: {
            details: {
                type: Object,
                default: null
            }
        }
    }
</script>

<style scoped>
    #ui__error {
        margin-top:5px; 
    }
</style>