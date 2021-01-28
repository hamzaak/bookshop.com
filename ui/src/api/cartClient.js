import axios from 'axios';
import { API_CART_URL } from "@/api/config";

const cartClient = {
    get(accountId) {
      return axios.get(API_CART_URL + `/get/` + accountId).then(res => res.data);
    },
    add(cartItem) {
      return axios.post(API_CART_URL + `/add`, cartItem).then(res => res.data);
    },
    remove(cartItem) {
      return axios.post(API_CART_URL + `/remove`, cartItem).then(res => res.data);
    }
};
  
export default cartClient;