import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
    state: {
        cart: [],
        totalPrice: 0
    },
    mutations: {
        updateCart(state, items) {
            state.cart = items.filter(function (el) { return el.quantity > 0; })
            
            state.totalPrice = 0
            for(var item of state.cart) {
                state.totalPrice = state.totalPrice + item.productPrice * item.quantity
            }
        },
        increase(state, item) {
            var original = state.cart.filter(function (el) { return el.productId == item.productId; })
            if (original.length > 0) {
                original[0].quantity++
                state.cart = state.cart.filter(function (el) { return el.productId != item.productId; })
                state.cart.push(original[0])
            } else {
                item.quantity = 1
                state.cart.push(item)
            }

            state.cart = state.cart.filter(function (el) { return el.quantity > 0; })

            state.totalPrice = 0
            for(var i in state.cart) {
                state.totalPrice = state.totalPrice + i.productPrice * i.quantity
            }
        },
        decrease(state, item) {
            var original = state.cart.filter(function (el) { return el.productId == item.productId; })
            original[0].quantity--

            state.cart = state.cart.filter(function (el) { return el.productId != item.productId; })

            if (original[0].quantity > 0) {
                state.cart.push(original[0])
            }
            
            state.cart = state.cart.filter(function (el) { return el.quantity > 0; })

            state.totalPrice = 0
            for(var i in state.cart) {
                state.totalPrice = state.totalPrice + i.productPrice * i.quantity
            }
        }
    },
    getters: {
        cart: state => state.cart,
        totalPrice: state => state.totalPrice
    }
})