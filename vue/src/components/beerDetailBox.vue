<template>
  <div>
      <small>{{beer.description}}</small>
      <button v-if="this.$store.state.token" @click="removeBeer(beer.id)" >Delete</button>
  </div>
      
</template>

<script>
import breweryService from '@/services/BreweryService'
export default {
name:"beer-detail-box",
props: ['beer'],
methods:{
    removeBeer(beerId){

        breweryService.deleteBeer(beerId, this.$route.params.id);
        document.location.reload(true);

    

    }
}
}
</script>

<style>
    small {
        display: block;
        color: #F1F1F1;
        font-size: 0.8em;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
</style>


