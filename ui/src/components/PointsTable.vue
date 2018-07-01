<template>
    <div id="point__table">
        <div class="row point__table__data">
            <div class="col"></div>
            <div class="col"></div>
            <div class="col"></div>
            <div class="col"></div>
            <div class="col"></div>
        </div>
    </div>
</template>

<script>
    export default {
        name : "PointsTable",
        props: {
            result: {
                type: Array,
                default: []
            },
        },
    }
</script>

<style scoped>
    .point__table__data{
        border: 1px solid blue;
    }
</style>