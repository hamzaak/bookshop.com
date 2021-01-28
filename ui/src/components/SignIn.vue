<template>
  <div class="page-content">
      <div class="d-flex justify-content-center" v-if="loading">
        <div class="spinner-grow text-danger" role="status">
            
        </div>
      </div>
      <div v-if="!loading">
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
  </div>
</template>

<script>

import axios from 'axios';
import { API_ACCOUNTS_URL, API_PRODUCTS_URL } from "@/constants/config";

export default {
  name: "SignIn",
  data() {
    return {
      accountName: 'John Doe',
      loading: true,
      accounts: [{ name: "John Doe" }, { name: "Jane Doe" }],
      products: [
        {
            description: "Published in 1960, this timeless classic explores human behaviour and the collective conscience of The Deep South in the early 20th century. Humour entwines the delicate strands of prejudice, hatred, hypocrisy, love and innocence to create one of the best novels ever written.",
            price: 18.99,
            quantity: 50,
            title: "To Kill a Mockingbird, by Harper Lee"
        },
        {
            description: "Although 1984 has passed us by, George Orwell’s dystopian, totalitarian world of control, fear and lies has never been more relevant. Delve into the life of Winston Smith as he struggles with his developing human nature in a world where individuality, freewill and love are forbidden.",
            price: 13.99,
            quantity: 100,
            title: "1984, by George Orwell"
        },
        {
            description: "Books are forbidden, and it is our main character Guy Montag’s job to burn any books he comes across. Often compared to George Orwell’s 1984, Ray Bradbury’s dystopian world is an unsettling commentary on Western societies’ addiction and dependence on the media and conformity.",
            price: 24.99,
            quantity: 100,
            title: "Fahrenheit 451, by Ray Bradbury"
        },
        {
            description: "Set in The South during The Civil War, chances are if you love the movie you’ll love the book. Although the main character and the world she lives in is loathsome, readers’ opinions are twisted as this novel dishes out a fated justice when both Scarlett and The South lose their wars.",
            price: 9.99,
            quantity: 100,
            title: "Gone with the Wind, by Margaret Mitchell"
        },
        {
            description: "This classic novel follows the lives of boys marooned on an island as they regress into savages; and their beautiful, enjoyable island existence collapses into a primitive and cruel nightmare.",
            price: 15.99,
            quantity: 100,
            title: "Lord of the Flies, by William Golding"
        },
        {
            description: "Of Mice And Men is a complex story of a friendship between two migrant workers: George Milton and Lennie Small, in California. Watch their friendship develop as the pair work towards their modest dreams of owning their own land and pets.",
            price: 12.49,
            quantity: 100,
            title: "Of Mice and Men, by John Steinbeck"
        },
        {
            description: "Following eighteen years as a political prisoner, Dr Manette is released and returns to England with his daughter Lucie. There, two very different men fall in love with Lucie and become entwined in a tale of love and sacrifice.",
            price: 8.99,
            quantity: 100,
            title: "A Tale of Two Cities, by Charles Dickens"
        },
        {
            description: "Perhaps the most famous love story ever written, Romeo and Juliet is an epic tragedy that explores the euphoria of desire and the tragedy of revenge.",
            price: 16.49,
            quantity: 100,
            title: "Romeo and Juliet, by William Shakespeare"
        },
        {
            description: "Bizarre and curious, Alice In Wonderland explores the potential of imagination and the reality of fiction. If you’re a fan of escaping the real world, this is definitely the book for you.",
            price: 16.99,
            quantity: 100,
            title: "Alice in Wonderland, by Lewis Carroll"
        },
        {
            description: "A combination of gothic thriller, cautionary tale and romance novel, Frankenstein is a story like no other. Written by Mary Shelley when she was just eighteen, Frankenstein prompts readers to ask themselves some truly shattering questions: what makes us human? What do we owe to one another as living creatures? How far can science push the boundaries of nature?",
            price: 28.99,
            quantity: 100,
            title: "Frankenstein, by Mary Shelley"
        }
      ]
    }
  },
  created() {
    axios.get(API_ACCOUNTS_URL + `/get/all`)
        .then(response => {
            // populate all data
            if(response.data.length == 0) {
                this.populateAccounts(0);
            } else {
                this.loading=false
            }
        })
        .catch(e => {
        this.errors.push(e)
        })
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
    },
    populateAccounts(index){
        if(this.accounts.length > index) {
            axios.put(API_ACCOUNTS_URL + `/create`, this.accounts[index])
                .then(response => {
                    console.log(response)
                    index++
                    this.populateAccounts(index)
                })
                .catch(e => {
                    console.log(e);
                })
        } else {
            this.populateProducts(0)
        }
    },
    populateProducts(index){
        if(this.products.length > index) {
            axios.put(API_PRODUCTS_URL + `/create`, this.products[index])
                .then(response => {
                    console.log(response)
                    index++
                    this.populateProducts(index)
                })
                .catch(e => {
                    console.log(e);
                })
        } else {
            this.loading=false
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
