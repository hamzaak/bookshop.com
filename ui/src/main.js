import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import { store } from './store/store'
import Products from './components/Products.vue'
import Cart from './components/Cart.vue'
import SignIn from './components/SignIn.vue'

Vue.config.productionTip = false
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', component: SignIn },
    { path: '/signIn', component: SignIn },
    { path: '/products', component: Products },
    { path: '/cart', component: Cart }
  ]
})

new Vue({
  render: h => h(App),
  store: store,
  router: router
}).$mount('#app')
