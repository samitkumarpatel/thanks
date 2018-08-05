<template>
    <div id="alert_box">
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="alert alert-success" role="alert" v-if="success">
                        <h4>SUCCESS!</h4>
                        <pre>{{success}}</pre>
                    </div>
                    <div v-if="component_type== 'MEMBER'">
                        <p>Your default password is "changeme", don't forgot to change!</p>
                    </div>
                    <div>
                        <p><router-link to="/login">Click to login</router-link> | <a href="/home">home</a> </p> 
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name : "alert",
        props: {
            success: {
                type: Object,
                default: null
            },
            component_type : {
                type : String,
                default : "MEMBER"
            }
        }
    }
</script>

<style scoped>

</style>