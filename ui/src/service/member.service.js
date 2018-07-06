import Vue from 'vue'
import axios from 'axios'
import {MEMBERS_API_URL} from '@/service/config.js'
const MemberApiService = {
    getAll(callback){
        axios
            .get(MEMBERS_API_URL)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    getById(memberId,callback){
        axios
            .get(MEMBERS_API_URL+"/"+memberId)
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
        
    }
}
export default MemberApiService