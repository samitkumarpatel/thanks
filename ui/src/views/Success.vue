<template>
    <div id="success">
        <div class="alert alert-success" role="alert" v-if="success">
            <strong> <i class="material-icons">thumb_up_alt</i> Account successfully created with below details!</strong>
            <pre>{{success}}</pre>
        </div>
        <div v-if="component_type== 'MEMBER'">
            <p>Your default password is "changeme", don't forgot to change!</p>
        </div>
        <div>
            <p>
                <router-link to="/login">Click to login</router-link>|<a href="/home">home</a>
            </p> 
        </div>
    </div>
</template>

<script>
    export default {
        name : 'Success',
        props: {
            success: {
                type: Object,
                default: null
            },
            component_type : {
                type : String,
                default : "MEMBER"
            }
        },
    }
</script>

<style scoped>

</style>