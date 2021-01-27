<template>
  <div>
      <Navbar />
      <div class="page-content">
        <h2>Shopping Cart</h2>
        <table class="table" v-if="cart && cart.length">
            <thead>
                <tr>
                <th scope="col">Title</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
                <th scope="col"></th>
                <th scope="col"></th>
                <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="i of cart" :key="i.id">
                    <td class="align-middle">{{ i.productTitle }}</td>
                    <td class="align-middle">{{ i.productDescription }}</td>
                    <td class="align-middle">${{formatPrice(i.productPrice * i.quantity)}}</td>
                    <td class="align-middle" style="width:15px">
                        <i class="bi bi-dash-circle-fill text-danger q-icon" @click="decrease(i)"></i>
                    </td>
                    <td class="align-middle" style="width:60px">
                        <input type="text" class="form-control form-control-sm text-center" placeholder="John Doe" v-model="i.quantity" disabled>
                    </td>
                    <td class="align-middle" style="width:15px">
                        <i class="bi bi-plus-circle-fill text-danger q-icon" @click="increase(i)"></i>
                    </td>
                </tr>
            </tbody>
        </table>
        <br>
        <div>
            <h2>Total Price: ${{formatPrice(this.totalPrice)}}</h2> 
        </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { API_CART_URL } from "@/constants/config";
import { mapState } from 'vuex'
import Navbar from './Navbar.vue'

export default {
  name: "Cart",
  components: {
    Navbar
  },
  computed: mapState(["cart", "totalPrice"]),
  data() {
    return {
        accountId: localStorage.getItem('accountId')
    };
  },
  methods: {
      formatPrice(value) {
        let val = (value/1).toFixed(2).replace('.', ',')
        return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")
        },
      increase(item) {
          var shoppingCartItem = {
            accountId: this.accountId,
            productId: item.productId,
            quantity: 1 };

        axios.post(API_CART_URL + `/add`, shoppingCartItem)
            .then(response => {
                console.log(response);
                this.$store.commit('updateCart', response.data);
            })
            .catch(e => {
                console.log(e);
            })
      },
      decrease(item) {
          var shoppingCartItem = {
            accountId: this.accountId,
            productId: item.productId,
            quantity: 1 };
          axios.post(API_CART_URL + `/remove`, shoppingCartItem)
                .then(response => {
                    console.log(response);
                    this.$store.commit('updateCart', response.data);
                })
                .catch(e => {
                    console.log(e);
                })
      }
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.page-content {
  padding: 40px;
}
.q-icon {
    font-size: 1.5rem;
    cursor: pointer;
    vertical-align: middle;
}
.q-icon::before {
    vertical-align: middle;
}
.q-input {
    width: 9rem;
}
</style>
