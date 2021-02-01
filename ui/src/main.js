import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import { store } from './store/store'
import Login from './views/Login.vue'
import ProductList from './views/ProductList.vue'
import Cart from './views/Cart.vue'
import i18n from '@/api/i18n'

Vue.config.productionTip = false
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', component: Login },
    { path: '/login', component: Login },
    { path: '/products', component: ProductList },
    { path: '/cart', component: Cart }
  ]
})

const DEFAULT_ENCODING = 'tr'
let words = []

i18n.getByLang(DEFAULT_ENCODING)
  .then(data => {
      console.log(JSON.stringify(data))
      words= data
  })
  .catch(e => {
      console.log(e)
  })

const i18Plugin = {
  install() {
    Vue.prototype.$w = function(key) {
      const items = words.filter(function (el) { return el.key == key; })
      if(items.length == 0)  {
       return key
      } 
      return items[0].value
    } 
  }
}

Vue.use(i18Plugin)

new Vue({
  render: h => h(App),
  store: store,
  router: router
}).$mount('#app')
