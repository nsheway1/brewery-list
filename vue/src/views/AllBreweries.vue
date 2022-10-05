<template>
<div>
  <featured-brew-box :key="featuredBreweryKey" v-if="pageLoaded" class="feature-brew-box"/>
<div class="search-area">
  <img class="magnify-glass" src="../img/search.jpg" />
  <div class="bar-label">
    <label class="brew-form-label" for="breweryNameFilter"></label>
    <input class="search-input" type="text" name="" 
    id="breweryNameFilter" v-model="filter.breweryName" 
    placeholder="Search for breweries" />
  </div>
</div>
<div class="list">
      <brewery-list v-bind:filter="filter" />
</div>
<add-brewery-form class="add-brew-form" v-if="isAddingBrewery" />
  <button class="add" v-if="isLoggedIn" @click="isAddingBrewery = true">Add Brewery</button>
</div>
</template>

<script>
import BreweryList from '@/components/BreweryList.vue'
import AddBreweryForm from '../components/addBreweryForm.vue';
import breweryService from '@/services/BreweryService'
import FeaturedBrewBox from '../components/FeaturedBrewBox.vue';

export default {
  name: 'all-breweries',
  components: { BreweryList, AddBreweryForm, FeaturedBrewBox},
  data(){
    return{
      isAddingBrewery: false,
      pageLoaded: false,
      filter: {
        breweryName:""
      }
    }
  },

  computed: {
    featured() {
      let featuredBrewery;
      let x = 0;
      this.$store.state.breweries.forEach(brewery => {
         if (brewery.voteCount > x) {
           x = brewery.voteCount;
           featuredBrewery = brewery;
         }

       });
        return featuredBrewery
    },
    featuredBreweryKey() {
      return this.$store.state.featuredKey;
    },
    isLoggedIn(){
      return this.$store.state.token;
    }
  },

  beforeCreate() {
         breweryService.getBreweries().then( response => {
            this.$store.commit("SET_BREWERIES", response.data);
         
        })
  },

  created(){

    this.$store.commit("SET_CURRENT_PAGE", 'Explore Columbus Beer');
          this.pageLoaded = true;
  },
}

</script>

<style>

.search-area {
  display: flex;
  justify-content: center;
}

.magnify-glass {
  margin-top: .7rem;
  margin-right: .2rem;
  height: 2.5rem;
  color: wheat;
}

.search-input {
  margin-top: 1rem;
  height: 1.5rem;
  border-radius: 5px;

}

.list{
  padding-top: 1rem;
}

.feature-brew-box {
  margin-left: 20rem;
}

.add{
  height: 4rem;
  width: 10rem;
  border-radius: 5px;
  color: white;
  background: linear-gradient(1deg, rgba(0, 0, 0, 1), rgba(251, 170, 27, .8));
}

.add-brew-form{
  margin-left: 20%;
}

</style>