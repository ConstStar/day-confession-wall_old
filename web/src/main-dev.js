import axios from 'axios'

axios.defaults.baseURL = 'http://39.107.228.202:8081/biaobai-1.0/'

Vue.prototype.$http = axios
