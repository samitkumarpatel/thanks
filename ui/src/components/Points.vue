<template>
    <div id="member__points">
        <h4>you have earned <span class="badge badge-success">{{points}}</span> points as of now !</h4>
        <br/>
        <p><a href="#">click</a> to give points to other </p>
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