<template>
    <div id="member__points">
        <div class="row">
            <div class="col">
                <h4>Your total points : <span class="badge badge-success">{{points}}</span> !</h4>
                <br/>
                <p><a href="#" @click="givePoint">click (show/hide)</a> panel to give points to other </p>
                <section v-if="givePointFlag">
                    <div>
                        <input class="form-control form-control-lg" type="text" placeholder="type to search" disabled>
                    </div>
                    <hr/>
                    <div v-if="err">
                        {{err}}
                    </div>
                    <div class="alert alert-success" role="alert" v-for="member of members" :key="member.id">
                        {{member.firstname}} {{member.lastname}} , ({{member.role}})
                        <button type="button" class="btn btn-info" @click="addPoints(memberId,member.id)" v-if="memberId!=member.id">+</button>
                    </div>
                </section>
            </div>
            <div class="col">
                <strong>My achievement from other</strong>
                <hr/>
                <div class="alert alert-primary" role="alert" v-for="h of history" :key="h.id">
                    <a href="#">member</a> given {{h.point}} point to me on ({{h.createDate}})
                </div>
            </div>
        </div>
        
    </div>
</template>

<script>
    import MemberApiService from "@/service/member.service.js"
    import HistoryApiService from "@/service/history.service.js"

    export default {
        name : "points",
        props: {
            points: {
                type: Number,
                default: 0
            },
            history: {
                type : Array,
                default: []
            },
            memberId : {
                type: String,
                default: null
            }
        },
        methods: {
            givePoint() {
                if(this.givePointFlag){
                    this.givePointFlag=false;
                }else{
                    this.givePointFlag=true;
                }
                //call all member for point
                var that=this;
                MemberApiService.getAll(function(res,err){
                    if(err){
                        that.err=err;     
                    }else{
                        that.members=res.data;
                    }
                });
            },
            addPoints(memberId,toMemberId) {
                var that=this;
                MemberApiService.addRewardPoints(toMemberId,function(res,err) {
                    if(err){
                        that.err=err;
                    } else {
                        //do something 
                    }
                });
                that.addHistory(memberId,toMemberId);
            },
            addHistory(memberId,toMemberId) {
                var that=this;
                HistoryApiService.save(memberId,toMemberId,function(res,err) {
                    if(err){
                        that.err=err;
                    } else {
                        console.log('save history');
                    }
                });
            }
        },
        data() {
            return {
                givePointFlag: false,
                err: null,
                members: []
            }
        }
    }
</script>

<style scoped>
    #member__points{
        margin-top: 20px;
    }
</style>