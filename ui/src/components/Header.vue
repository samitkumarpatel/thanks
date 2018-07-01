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
                    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        settings
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <router-link class="dropdown-item" to="/login">login</router-link>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">action</a>
                        </div>
                    </li>
                </ul>
                
            </div>
        </nav>
    </div>
</template>

<script>
    export default {
        name: "application_header",
        data() {
            return {
                
            }
        },methods: {
            
        }, created : function(){
            
        }
    };
</script>

<style scoped>
</style>