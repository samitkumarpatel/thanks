import Vue from 'vue'
import axios from 'axios'
import {MEMBERS_API_URL} from '@/service/config.js'
const MemberApiService = {
    getAll(callback){
        axios
            .get(MEMBERS_API_URL)
                .then(function(response){
                    callback(response,null)
                })
                .catch(function(error){
                    callback(null,error)
                })
    },
    getById(memberId,callback){
        axios
            .get(MEMBERS_API_URL+"/"+memberId)
            .then(function(response){
                callback(response,null)
            })
            .catch(function(error){
                callback(null,error)
            })
    },
    save(member,callback){

    },
    update(id,member,callback){

    },
    filter(filterString,callback){

    },
    delete(id,callback){
        
    }
}
export default MemberApiService