import Vue from 'vue'
import axios from 'axios'
import {TEAMS_API_URL} from '@/service/config.js'
const TeamApiService = {
    getAll(callback){
        axios
            .get(TEAMS_API_URL)
            .then(function(response){
                callback(response,null)
            })
            .catch(function(error){
                callback(null,error)
            })
    },
    getTeamNameById(teamId,callback){

    },
    getTeamById(teamId,callback){

    },
    save(team,callback){

    },
    update(id,team,callback){

    },
    filter(filterString,callback){

    },
    delete(id,callback){

    }
}
export default TeamApiService