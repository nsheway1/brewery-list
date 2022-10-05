<template>
<div class="mainlist">
    <sidebar id="itinerary"></sidebar> 
  <div class="brewery-list">
      <brewery-list-item v-for="brewery in filteredList" 
      v-bind:key="brewery.id" class="brewery" 
      v-bind:brewery="brewery" :id="brewery.name" draggable="true" />
  </div>
</div>
</template>

<script>
import breweryListItem from '@/components/BreweryListItem'
import Sidebar from './Sidebar.vue';


export default {
    name: 'brewery-list',
    components: {
        breweryListItem, Sidebar},

   props: ['filter'],
    

    computed: {
        breweries() {
            const breweryList = this.$store.state.breweries;
            return breweryList;

        },

        

        filteredList() {
            return this.$store.state.breweries.filter((brewery) => {
                return brewery.name.toLowerCase().includes(this.filter.breweryName.toLowerCase())
            })
        }
        // setFeatured(brewery) {
        //     this.$store.commit("SET_FEATURED", brewery);
        //     const featured = this.$store.state.featured;
        //     return featured
        // }
    },



}
</script>

<style>
.brewery-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    flex-direction: row;
    grid-area: list;
}

.brewery {
    font-size: 20px;
     flex-basis: 29%;
}

.mainlist{
    display: grid;
    grid-template-columns: 20% 80%;
    grid-template-areas: 
    ". list";
}






</style>
