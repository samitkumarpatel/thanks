<template>
    <div id="ui__header">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <router-link class="nav-link" to="/">thanks</router-link>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    </form>
                    <li class="nav-item">
                        <router-link class="nav-link" to="/teams">Team</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/members" class="nav-link">Member</router-link>
                    </li> 
                    <li class="nav-item" v-if="memberDetails!=null">
                        <span class="nav-link">{{memberDetails.firstname}} {{memberDetails.lastname}}</span>
                    </li>
                    <li class="nav-item">
                        <span class="nav-link" v-if="memberDetails==null" @click="login">login</span>
                        <span class="nav-link" v-else @click="logout">logout</span>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</template>

<script>
    import JWTLoginService from "@/service/jwtLogin.service.js";
    export default {
        name: "application_header",
        data() {
            return {
            }
        },
        methods: {
            login(){
                this.$router.push("/login");
            },
            logout(){
                this.$store.commit("setMemberId", null);
                this.$store.commit("setMemberDetails", null);
                JWTLoginService.removeJWTfromLocalStorage();
                this.$router.push("/login");
            }
        },
        computed: {
            memberDetails(){
                return this.$store.state.memberDetails;
            }
        },    
    };
</script>

<style scoped>
</style>