<template>
<div class="feature-outline">
    <h1 class="featured-header">Featured Brewery</h1>
    <div class="feature-box">
        <router-link class="brewery-link" v-bind:to="{ name: 'brewery-details', params: {id: brewery.id}}">
            <div class="feature-item" v-if="infoRetrieved">
                <h1 class="brewery-list-title">{{brewery.name}}</h1>
                <img :src="imageUrl" />
            </div>
        </router-link>
        <p class="feature-info">"{{brewery.description}}"
        <br>
        <br>
            Upvote your favorite breweries to get them featured!
        </p>
    </div>
</div>
</template>

<script>
import breweryService from '@/services/BreweryService.js'
import axios from 'axios'
export default {
name: 'featured-brew-box',
data(){
    return{
        brewery: null,
        imageUrl: '',
        infoRetrieved: false
    }
},

created(){
    breweryService.getFeaturedBrewery().then( response => {
        this.brewery = response.data;
    })
    setTimeout(() => axios.get('https://us-central1-brewery-finder-f943e.cloudfunctions.net/getImageUrl', { params: { name: this.brewery.name + '-logo.jpg' }})
      .then(response => {
        this.imageUrl = response.data;
      }), 500);
    setTimeout(() => this.infoRetrieved = true, 501);
}

}
</script>

<style>

.feature-outline {
        border: 10px solid rgba(251, 170, 27, .5);
        border-style: solid;
        margin-top: 3rem;
}

.featured-header {
  padding-right: 15rem;
  font-family: 'Bungee', cursive;

}

.feature-box {
  display: flex;
  justify-content: space-around;
  background-color: rgb(0, 0, 0, .8);
}

.feature-item{
    background-color: rgba(20, 141, 57, 0.5);
}

.feature-item img{
    height: 12rem;
}

.feature-info {
  color: inherit;
  margin-top: 0rem;
  width: 42rem;
  height: 21rem;
  border: .1rem black solid;
  border-radius: 1rem;
  background-color:rgba(251, 170, 27, .5);
  box-shadow: rgba(251, 170, 27, .5) 5px 5px, 
  rgba(251, 170, 27, .3) 10px 10px, 
  rgba(251, 170, 27, .2) 15px 15px,
  rgba(251, 170, 27, .1) 20px 20px,
  rgba(251, 170, 27, .005) 25px 25px;
}

</style>