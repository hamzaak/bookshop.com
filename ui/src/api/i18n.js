import axios from 'axios'
import { API_i18_URL } from '@/api/config'

const i18n = {
    getByLang(lang) {
      return axios.get(API_i18_URL + `/get/`+ lang).then(res => res.data)
    },
    create(entry) {
      return axios.put(API_i18_URL + `/create`, entry).then(res => res.data)
    }
}
  
export default i18n