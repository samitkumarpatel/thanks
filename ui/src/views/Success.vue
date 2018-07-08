<template>
    <div id="success">
        <div class="alert alert-success" role="alert" v-if="success">
            <h4>SUCCESS!</h4>
            <pre>{{success}}</pre>
        </div>
        <div>
            <p>Your default password is "changeme", don't forgot to change!</p>
        </div>
        <div>
            <p><router-link to="/login">Click to login</router-link> </p>
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
            }
        },
    }
</script>

<style scoped>

</style>