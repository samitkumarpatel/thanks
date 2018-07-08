<template>
    <div id="ui__error">
        <div class="alert alert-danger" role="alert" v-if="details.error">
            <h4 class="alert-heading">Http status: {{details.status}}</h4>
            <p>Reason : {{details.message}}</p>
            <p>Something went wrong! Please try after sometime</p>
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