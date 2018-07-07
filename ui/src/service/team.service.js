import Vue from 'vue'
import axios from 'axios'
import {TEAMS_API_URL} from '@/service/config.js'
const TeamApiService = {
    getAll(callback){
        axios
            .get(TEAMS_API_URL)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    getTeamById(teamId,callback){
        axios
            .get(TEAMS_API_URL+"/"+teamId)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    save(team,callback){
        axios
            .post(TEAMS_API_URL,team)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    update(id,team,callback){
        axios
            .put(TEAMS_API_URL+"/"+id,team)
            .then(function(response){callback(response,null)})
            .catch(function(error){callback(null,error)})
    },
    filter(filterString,callback){

    },
    delete(id,callback){

    }
}
export default TeamApiService