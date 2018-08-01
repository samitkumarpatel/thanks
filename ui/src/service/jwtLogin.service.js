import Vue from 'vue'
import axios from 'axios'
import {JWTLOGIN_API_URL} from '@/service/config.js'
const JWTLoginService = {
    login(username,password,callback){
        const params = new URLSearchParams();
        params.append('username', username);
        params.append('password', password);
        axios
            .post(JWTLOGIN_API_URL+"/login",params)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    logout(callback){
        axios
            .get(JWTLOGIN_API_URL+"/logout")
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    getJWTfromLocalStorage(){
        return window.localStorage.getItem("jwtToken");
    },
    setJWTInLocalStorage(token){
        return window.localStorage.setItem("jwtToken",token);
    },
    removeJWTfromLocalStorage(){
        window.localStorage.removeItem("jwtToken");
    }
}
export default JWTLoginService