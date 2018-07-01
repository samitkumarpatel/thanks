import Vue from 'vue'
import axios from 'axios'
import {HISTORY_API_URL} from '@/service/config.js'
const HistoryApiService = {
    getAll(callback){
        axios
            .get(HISTORY_API_URL)
            .then(function(response){
                callback(response,null)
            })
            .catch(function(error){
                callback(null,error)
            })
    }
}
export default HistoryApiService