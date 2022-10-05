<template>
  <div>
    <div class="header-box">
    <h1>Welcome to your Brewery Tour Router!</h1>
    <h2>Simply select a starting and ending point to display the optimal route for your trip!</h2>
    </div>
    <div class="i-map" v-if="pageLoaded">  
        <itinerary-map  />
    </div>
  </div>
</template>

<script>
import ItineraryMap from '../components/ItineraryMap.vue';
export default {
  components: { ItineraryMap },
name: 'itinerary-view',
data(){
    return{
        pageLoaded: false,
    }
},
mounted(){
    this.$store.commit("SET_CURRENT_PAGE", 'Brewery Tour Router');
    setTimeout(() => this.pageLoaded = true, 500);
}
}
</script>

<style>

.i-map{
   display: flex;
   justify-content: center;
}

.header-box{
    color: black;
    background-color:rgba(251, 170, 27, .7);
}

</style>