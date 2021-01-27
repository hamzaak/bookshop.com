<template>
  <div class="page-content">
    <h2>Sign In</h2>
    <div class="row">
        <div class="col-sm">
            <input type="input" class="form-control" placeholder="John Doe" v-model="accountName">
        </div>
        <div class="col-sm">
            <button class="btn btn-danger" type="button" @click="signIn()">
                Sign In
            </button>
        </div>
        <div class="col-sm">
            
        </div>
    </div>
  </div>
</template>

<script>

import axios from 'axios';
import { API_ACCOUNTS_URL } from "@/constants/config";

export default {
  name: "SignIn",
  data() {
    return {
      accountName: 'John Doe'
    }
  },
  methods: {
    signIn() {
        if(this.accountName.trim() != '') {
            axios.get(API_ACCOUNTS_URL+`/getbyname/`+this.accountName)
                .then(response => {
                    localStorage.setItem('accountId', response.data.id);
                    localStorage.setItem('accountName', response.data.name);
                    
                    this.$router.push({path: 'products'});
                })
                .catch(e => {
                    console.log(e);
                })
        }
    }
}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .page-content {
        padding: 40px;
    }
</style>
