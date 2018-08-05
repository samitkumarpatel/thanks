import Vue from 'vue'
import axios from 'axios'
import {MEMBERS_API_URL} from '@/service/config.js'
import JWTLoginService from "@/service/jwtLogin.service.js";

const MemberApiService = {
    getAll(callback){
        axios
            .get(MEMBERS_API_URL)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    getById(memberId,callback){
        let axiosConfig = {
            headers: {
                'JWT_TOKEN': JWTLoginService.getJWTfromLocalStorage()
            }
        };
        axios
            .get(MEMBERS_API_URL+"/"+memberId,axiosConfig)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    save(member,callback){
        axios
            .post(MEMBERS_API_URL,member)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})

    },
    update(id,member,callback){
        axios
            .put(MEMBERS_API_URL+"/"+id,member)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    filter(filterString,callback){

    },
    delete(id,callback){
        
    },
    logon(empId,password,callback){
        axios
            .get(MEMBERS_API_URL+"/validate/"+empId+"/"+password)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    prepareHeaders() {
        let axiosConfig = {
            headers: {
                'JWT_TOKEN': JWTLoginService.getJWTfromLocalStorage()
            }
        };
        return axiosConfig;
    },
    addRewardPoints(id,callback){
        axios
            .post(MEMBERS_API_URL+"/"+id+"/points")
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)}) 
    }
}
export default MemberApiService