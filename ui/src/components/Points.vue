<template>
    <div id="member__points">
        <div class="row">
            <div class="col">
                <h4>Your total points : <span class="badge badge-success">{{points}}</span> !</h4>
                <br/>
                <p><a href="#">click</a> to give points to other </p>
            </div>
            <div class="col">
                <strong>Recent History</strong>
                <hr/>
            </div>
        </div>
        
    </div>
</template>

<script>
    export default {
        name : "points",
        props: {
            points: {
                type: Number,
                default: 0
            },
        },
    }
</script>

<style scoped>
    #member__points{
        margin-top: 20px;
    }
</style>