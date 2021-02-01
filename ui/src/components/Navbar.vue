<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
            <strong>Bookshop</strong>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <router-link to="/products" class="nav-link active">{{ $w('products') }}</router-link>
            </li>
        </ul>
        <form class="d-flex">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a to="/" class="nav-link" style="font-weight:bold;">{{accountName}}</a>
                </li>
            </ul>
            <button class="btn btn-outline-danger" type="button" @click="goCart()">
                <i class="bi bi-cart4"></i>
                <sup><span class="badge bg-danger">{{cart.length}}</span></sup>
            </button>
            <button class="btn btn-danger" style="margin-left: 0.5rem;" type="button" @click="signOut()">
                {{ $w('sign_out') }}
            </button>
        </form>
        </div>
    </div>
    </nav>
</template>

<script>

import { mapState } from 'vuex'
import cartClient from '@/api/cartClient'

export default {
  name: 'Navbar',
  computed: mapState([
    'cart'
  ]),
  data() {
    return {
        accountId: localStorage.getItem('accountId'),
        accountName: localStorage.getItem('accountName')
    }
  },
  created() {
    cartClient.get(this.accountId)
        .then(data => {
            this.$store.commit('updateCart', data)
        })
        .catch(e => {
            console.log(e)
        })
  },
  methods: {
      goCart() {
          this.$router.push({ path: 'cart' })
      },
      signOut() {
          localStorage.removeItem('accountId')
          localStorage.removeItem('accountName')

          this.$router.push({ path: 'login' })
      }
  }
}
</script>

<style scoped>
sup {
    top: -.5em;
    position: relative;
    font-size: 75%;
    line-height: 0;
    vertical-align: baseline;
}
.q-icon {
    font-size: 2rem;
    vertical-align: middle;
}
.q-icon::before {
    vertical-align: middle;
}
</style>
