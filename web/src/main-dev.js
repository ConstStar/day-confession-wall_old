import axios from 'axios'

axios.defaults.baseURL = 'http://biaobai.xiaoxiaoge.cn/'

Vue.prototype.$http = axios
