import Vue from 'vue'
import axios from 'axios'
import {JWTLOGIN_API_URL} from '@/service/config.js'
const JWTLoginService = {
    login(username,password,callback){
        const params = new URLSearchParams();
        params.append('username', username);
        params.append('password', password);
        var that=this;
        axios
            .post(JWTLOGIN_API_URL+"/login",params)
            .then(function(response){callback(response,null)})
            .catch(function(error){
                that.removeJWTfromLocalStorage();
                callback(null,error);
            })
    },
    logout(callback){
        var that=this;
        axios
            .get(JWTLOGIN_API_URL+"/logout")
            .then(function(response){callback(response,null)})
            .catch(function(error){
                that.removeJWTfromLocalStorage();
                callback(null,error);
            })
    },
    getJWTfromLocalStorage(){
        return window.localStorage.getItem("jwtToken");
    },
    setJWTInLocalStorage(token){
        return window.localStorage.setItem("jwtToken",token);
    },
    removeJWTfromLocalStorage(){
        window.localStorage.removeItem("jwtToken");
    },
    parseJWT(callback){
        var url = JWTLOGIN_API_URL+"/parse?jwtToken="+this.getJWTfromLocalStorage();
        var that=this;
        axios
            .get(url)
            .then(function(response){callback(response,null)})
            .catch(function(error){
                that.removeJWTfromLocalStorage();
                callback(null,error);
            })
    }
}
export default JWTLoginService