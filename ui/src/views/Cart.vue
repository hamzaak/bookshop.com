<template>
  <div>
      <Navbar />
      <div class="page-content">
        <h2>{{ $w('shopping_cart') }}</h2>
        <table class="table" v-if="cart && cart.length">
            <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">{{ $w('title') }}</th>
                    <th scope="col">{{ $w('description') }}</th>
                    <th scope="col">{{ $w('price') }}</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="i of cart" :key="i.id">
                    <td>
                        <img height="70" v-bind:src=formatPicUrl(i.productPicUrl) v-bind:alt=i.productTitle>
                    </td>
                    <td class="align-middle">
                        <p class="q-text">
                            <strong>{{ i.productTitle }}</strong>
                        </p>
                    </td>
                    <td class="align-middle">
                        <p class="q-text">
                            {{ i.productDescription }}
                        </p>
                    </td>
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
            <h2>{{ $w('total_price') }}: ${{formatPrice(this.totalPrice)}}</h2> 
        </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Navbar from '@/components/Navbar.vue'
import cartClient from '@/api/cartClient'
import { BASE_IP } from '@/api/config'

export default {
  name: 'Cart',
  components: {
    Navbar
  },
  computed: mapState(['cart', 'totalPrice']),
  data() {
    return {
        accountId: localStorage.getItem('accountId')
    };
  },
  created() {
    if(this.accountId == null) {
        this.$router.push({path: 'login'})
    }
  },
  methods: {
    formatPicUrl(relativeUrl) {
        return 'http://' + BASE_IP + ':9000/' + relativeUrl
    },
    formatPrice(value) {
        let val = (value/1).toFixed(2).replace('.', ',')
        return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
    },
    increase(item) {
        var shoppingCartItem = {
            accountId: this.accountId,
            productId: item.productId,
            quantity: 1 }

        cartClient.add(shoppingCartItem)
            .then(data => {
                console.log(JSON.stringify(data))
                this.$store.commit('updateCart', data)
            })
            .catch(e => {
                console.log(e)
            })
    },
    decrease(item) {
        var shoppingCartItem = {
            accountId: this.accountId,
            productId: item.productId,
            quantity: 1 }
        
        cartClient.remove(shoppingCartItem)
            .then(data => {
                console.log(JSON.stringify(data));
                this.$store.commit('updateCart', data)
            })
            .catch(e => {
                console.log(e)
            })
    }
  },
};
</script>

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
.q-text {
        display: -webkit-box;
        -webkit-line-clamp: 3;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
        margin-bottom: 0rem;
    }
</style>
