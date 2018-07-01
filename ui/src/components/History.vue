<template>
    <div id="member__history">
        <div class="card" v-for="(x,index) in history" :key="index">
            <div class="card-header" id="headingOne">
            <h5 class="mb-0">
                <button class="btn btn-link" data-toggle="collapse" :data-target="'#collapseOne'+index" aria-expanded="true" :aria-controls="'collapseOne'+index">
                Name : {{index}} has given #5 points
                </button>
            </h5>
            </div>
            <div :id="'collapseOne'+index" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
            <div class="card-body">
                Details to be publish for - {{index}}
            </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name : "history",
        props: {
            history: {
                type: Array,
                default: []
            },
        },
    }
</script>

<style scoped>
    #member__history{
        margin-top: 20px;
    }

</style>