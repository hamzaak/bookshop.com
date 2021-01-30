<template>
  <div class="page-content">
    <div class="center-screen" v-if="loading">
        <div class="progress">
            <div class="progress-bar bg-danger" role="progressbar" v-bind:style='"width:"+loadingValue+"%"' v-bind:aria-valuenow=loadingValue aria-valuemin="0" aria-valuemax="100"></div>
        </div>
    </div>
      
      <div v-if="!loading">
        <h2>Sign In</h2>
        <div class="row">
            <div class="col-sm">
                <input id="txtAccountName" type="input" class="form-control" placeholder="John Doe" v-model="accountName">
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

import accountClient from '@/api/accountClient'
import productClient from '@/api/productClient'

export default {
  name: 'Login',
  data() {
    return {
      accountName: 'John Doe',
      accountNotFound: 'Account not found',
      loading: true,
      loadingValue: 0,
      countOfDone: 0,
      accounts: [{ name: 'John Doe' }, { name: 'Jane Doe' }],
      products: [
        {
            description: 'Published in 1960, this timeless classic explores human behaviour and the collective conscience of The Deep South in the early 20th century. Humour entwines the delicate strands of prejudice, hatred, hypocrisy, love and innocence to create one of the best novels ever written.',
            price: 18.99,
            quantity: 50,
            title: 'To Kill a Mockingbird, by Harper Lee',
            picUrl: 'https://i.dr.com.tr/cache/600x600-0/originals/0000000339306-1.jpg'
        },
        {
            description: 'Although 1984 has passed us by, George Orwell’s dystopian, totalitarian world of control, fear and lies has never been more relevant. Delve into the life of Winston Smith as he struggles with his developing human nature in a world where individuality, freewill and love are forbidden.',
            price: 13.99,
            quantity: 100,
            title: '1984, by George Orwell',
            picUrl: 'https://i.dr.com.tr/cache/600x600-0/originals/0000000064038-1.jpg'
        },
        {
            description: 'Books are forbidden, and it is our main character Guy Montag’s job to burn any books he comes across. Often compared to George Orwell’s 1984, Ray Bradbury’s dystopian world is an unsettling commentary on Western societies’ addiction and dependence on the media and conformity.',
            price: 24.99,
            quantity: 100,
            title: 'Fahrenheit 451, by Ray Bradbury',
            picUrl: 'https://i.dr.com.tr/cache/600x600-0/originals/0000000039867-1.jpg'
        },
        {
            description: 'This classic novel follows the lives of boys marooned on an island as they regress into savages; and their beautiful, enjoyable island existence collapses into a primitive and cruel nightmare.',
            price: 15.99,
            quantity: 100,
            title: 'Lord of the Flies, by William Golding',
            picUrl: 'https://i.dr.com.tr/cache/600x600-0/originals/0000000231733-1.jpg'
        },
        {
            description: 'Of Mice And Men is a complex story of a friendship between two migrant workers: George Milton and Lennie Small, in California. Watch their friendship develop as the pair work towards their modest dreams of owning their own land and pets.',
            price: 12.49,
            quantity: 100,
            title: 'Of Mice and Men, by John Steinbeck',
            picUrl: 'https://i.dr.com.tr/cache/600x600-0/originals/0000000247102-1.jpg'
        },
        {
            description: 'Following eighteen years as a political prisoner, Dr Manette is released and returns to England with his daughter Lucie. There, two very different men fall in love with Lucie and become entwined in a tale of love and sacrifice.',
            price: 8.99,
            quantity: 100,
            title: 'A Tale of Two Cities, by Charles Dickens',
            picUrl: 'https://i.dr.com.tr/cache/600x600-0/originals/0001697472001-1.jpg'
        },
        {
            description: 'Perhaps the most famous love story ever written, Romeo and Juliet is an epic tragedy that explores the euphoria of desire and the tragedy of revenge.',
            price: 16.49,
            quantity: 100,
            title: 'Romeo and Juliet, by William Shakespeare',
            picUrl: 'https://i.dr.com.tr/cache/600x600-0/originals/0001697467001-1.jpg'
        },
        {
            description: 'Bizarre and curious, Alice In Wonderland explores the potential of imagination and the reality of fiction. If you’re a fan of escaping the real world, this is definitely the book for you.',
            price: 16.99,
            quantity: 100,
            title: 'Alice in Wonderland, by Lewis Carroll',
            picUrl: 'https://i.dr.com.tr/cache/600x600-0/originals/0001878736001-1.jpg'
        },
        {
            description: 'A combination of gothic thriller, cautionary tale and romance novel, Frankenstein is a story like no other. Written by Mary Shelley when she was just eighteen, Frankenstein prompts readers to ask themselves some truly shattering questions: what makes us human? What do we owe to one another as living creatures? How far can science push the boundaries of nature?',
            price: 28.99,
            quantity: 100,
            title: 'Frankenstein, by Mary Shelley',
            picUrl: 'https://i.dr.com.tr/cache/600x600-0/originals/0001739502001-1.jpg'
        }
      ]
    }
  },
  created() {
      accountClient.getAll()
        .then(data => {
            // populate all data
            if(data.length == 0) {
                this.populateAccounts(0)
            } else {
                this.loading=false
            }
        })
        .catch(e => {
            console.log(e)
        })
  },
  methods: {
    signIn() {
        if(this.accountName.trim() == '') {
            var el = document.getElementById('txtAccountName')
            if(!el.classList.contains('is-invalid')) {
                el.classList.add('is-invalid')
            }
            return;
        }

        accountClient.getByName(this.accountName)
            .then(data => {
                if(data.id) {
                    localStorage.setItem('accountId', data.id)
                    localStorage.setItem('accountName', data.name)
                
                    this.$router.push({path: 'products'})
                } else {
                    var el = document.getElementById('txtAccountName')
                    if(!el.classList.contains('is-invalid')) {
                        el.classList.add('is-invalid')
                    }
                }
            })
            .catch(e => {
                console.log(e)
            })
    },
    populateAccounts(index){
        if(this.accounts.length > index) {
            accountClient.create(this.accounts[index])
                .then(data => {
                    console.log(JSON.stringify(data))
                    index++
                    this.countOfDone++
                    this.makeProgress()
                    this.populateAccounts(index)
                })
                .catch(e => {
                    console.log(e)
                })
        } else {
            this.populateProducts(0)
        }
    },
    populateProducts(index){
        if(this.products.length > index) {
            productClient.create(this.products[index])
                .then(data => {
                    console.log(JSON.stringify(data))
                    index++
                    this.countOfDone++
                    this.makeProgress()
                    this.populateProducts(index)
                })
                .catch(e => {
                    console.log(e)
                })
        } else {
            this.loading=false
        }
    },
    makeProgress() {
        this.loadingValue = this.countOfDone / (this.accounts.length + this.products.length) * 100
    }
}
}
</script>

<style scoped>
    .page-content {
        padding: 40px;
    }
    .center-screen {
        position: absolute;
        top:0;
        bottom: 0;
        left: 0;
        right: 0;
        margin: auto;
        width: 100px;
        height: 100px;
        text-align: center;
    }
    .progress {
        margin-top: 1rem;
    }
</style>
