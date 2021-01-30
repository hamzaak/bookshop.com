import axios from 'axios'
import { API_ACCOUNTS_URL } from '@/api/config'

const accountClient = {
    getAll() {
      return axios.get(API_ACCOUNTS_URL + `/get/all`).then(res => res.data)
    },
    getByName(name) {
      return axios.get(API_ACCOUNTS_URL + `/getbyname/` + name).then(res => res.data)
    },
    create(account) {
      return axios.put(API_ACCOUNTS_URL + `/create`, account).then(res => res.data)
    }
}
  
export default accountClient