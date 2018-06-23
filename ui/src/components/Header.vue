<template>
    <div id="ui__header">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <router-link class="nav-link" to="/">||||</router-link>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <router-link class="nav-link" to="/teams">create team</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/members" class="nav-link">create member</router-link>
                    </li>
                    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        settings
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">action</a>
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
            
        },
    };
</script>

<style scoped>
</style>