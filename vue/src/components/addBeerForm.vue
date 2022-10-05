<template>
  <form class="flex-form" v-on:submit.prevent="submitForm">
      <div class="label-8">
      <label for="name">Name</label>
      <input id="name" placeholder="" type="text" v-model="beer.name">
      </div>
      <div class="label-9">
      <label for="type">Type</label>
      <input id="type" type="text" v-model="beer.type">
      </div>
      <div class="label-10">
      <label for="description">Description</label>
      <input id="description" type="text" v-model="beer.description">
      </div>
      <div class="label-11">
      <label for="ABV">ABV</label>
      <input id="ABV" type= "text" v-model="beer.abv" >
      </div>
      <button class="submit-button">Submit</button>
  </form>
</template>

<script>
import breweryService from '@/services/BreweryService'
export default {
name:'add-beer-form',
data(){
    return{
        beer:{
            id:0,
            name:"",
            type:"",
            description:"",
            abv:0.0,
            breweryId:0
        }
    }
},
methods:{
    submitForm(){
        this.beer.breweryId= this.$route.params.id;
        breweryService.addNewBeer(this.beer, this.$route.params.id);
         document.location.reload(true);
        this.$forceUpdate();
    }
}
}
</script>

<style>
 label{
     color: black;
 }

 form input {
     margin-left: 1rem;
     margin-right: 1rem;
 }
 form {
     margin-top: 3rem;
 }

 .submit-button {
        height: 4rem;
        text-align: left;
        background: linear-gradient(1deg, rgba(0, 0, 0, 1), rgba(251, 170, 27, .8));
        color: white;
        /* border: solid rgba(255,255,255,0) 1pt; */
        border-radius: 5px;
        font-size: 1em;
        margin-right: 1rem;
        text-align: center;
        
 }

 .submit-button:hover {
            background: rgba(251, 170, 27, .8);
        color:black;
        border: solid black 1pt;
 }

</style>