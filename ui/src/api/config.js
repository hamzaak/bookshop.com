let BASE_IP = 'localhost'

console.log(process.env.VUE_APP_BACKEND)

if(process.env.VUE_APP_BACKEND == 'kube') {
    BASE_IP = '192.168.64.3'
}

const PRODUCT_MS_PORT = '9001'
const ACCOUNT_MS_PORT = '9002'
const CART_MS_PORT = '9003'
const i18_MS_PORT = '9004'

const API_PRODUCTS_URL = 'http://' + BASE_IP + ':' + PRODUCT_MS_PORT + '/api/products'
const API_ACCOUNTS_URL = 'http://' + BASE_IP + ':' + ACCOUNT_MS_PORT + '/api/accounts'
const API_CART_URL = 'http://' + BASE_IP + ':' + CART_MS_PORT + '/api/shoppingcart'
const API_i18_URL = 'http://' + BASE_IP + ':' + i18_MS_PORT + '/api/localization'

export { BASE_IP, API_PRODUCTS_URL, API_ACCOUNTS_URL, API_CART_URL, API_i18_URL}