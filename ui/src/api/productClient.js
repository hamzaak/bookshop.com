import axios from 'axios'
import { API_PRODUCTS_URL } from '@/api/config'

const productClient = {
    getAll() {
      return axios.get(API_PRODUCTS_URL + `/get/all`).then(res => res.data)
    },
    create(product) {
      return axios.put(API_PRODUCTS_URL + `/create`, product).then(res => res.data)
    }
}
  
export default productClient