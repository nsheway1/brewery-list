<template>
<div>
  <form class="flex-form" v-if="showForm" v-on:submit.prevent="submitForm">
      <div class="label-1">
      <label class="brew-form-label" for="name">Name</label>
      <input id="name" type="text" v-model="brewery.name">
      </div>
      <div class="label-2">
      <label class="brew-form-label" for="description">Description</label>
      <input id="description" type="text" v-model="brewery.description">
      </div>
      <div class="label-3">
      <label class="brew-form-label" for="street address">Street Address</label>
      <input id="street address" type= "text" v-model="brewery.streetAddress" >
      </div>
      <div class="label-4">
      <label class="brew-form-label" for="city">City</label>
      <input id="city" type="text" v-model="brewery.city">
      </div>
      <div class="label-5">
      <label class="brew-form-label" for="state">State</label>
      <input id="state" type="text" v-model="brewery.state">
      </div>
      <div class="label-6">
      <label class="brew-form-label" for="zipcode">Zipcode</label>
      <input id="zipcode" type="text" v-model="brewery.zipcode">
      </div>
      <button class="label-7">Submit</button>
  </form>
      
      <div v-if="showPhotoUploader">
      <p class="brew-upload-status-text">Add Brewery Photo</p>
      <input style="display: none" type="file" @change="onFileSelected" ref="imgInput">
      <button class="select-photo" @click="$refs.imgInput.click()">Select Photo</button>
        <p class="brew-upload-status-text" v-if="photoSelected">Photo Selected</p>
        <button class="upload-photo" @click="onUploadPhoto">Upload</button>
        <p class="brew-upload-status-text" v-if="uploadSuccess">Upload Successful!</p>
      </div>

      <div v-if="showLogoUploader">
      <p class="brew-upload-status-text">Add Brewery Logo</p>
      <input style="display: none" type="file" @change="onLogoSelected" ref="imgInput">
      <button class="select-photo" @click="$refs.imgInput.click()">Select Logo Image</button>
        <p class="brew-upload-status-text" v-if="photoSelected">Image Selected</p>
        <button class="upload-photo" @click="onUploadLogo">Upload</button>
        <p class="brew-upload-status-text" v-if="uploadSuccess">Upload Successful!</p>
      </div>
  </div>
</template>


<script>
import breweryService from '@/services/BreweryService'
import axios from 'axios'
export default {
  name: 'add-brewery-form',
  data(){
    return{
      brewery:{
        id: 0,
        name:"",
        description:"",
        streetAddress:"",
        city:"",
        state:"",
        zipcode:""
      },
      showForm: true,
      showPhotoUploader: false,
      fileToUpload: null,
      photoSelected: false,
      uploadSuccess: false,
      showLogoUploader: false
    }
  },
  methods:{
    submitForm(){
      breweryService.addNewBrewery(this.brewery);
      this.showPhotoUploader = true;
      this.showForm = false;
    },
    onFileSelected(event) {
        this.fileToUpload = event.target.files[0];
        this.photoSelected = true;
        this.uploadSuccess = false;
    },
    onLogoSelected(event){
      this.fileToUpload = event.target.files[0];
      this.photoSelected = true;
      this.uploadSuccess = false;
    },
    onUploadPhoto(){
        const fd = new FormData();
        fd.append('image', this.fileToUpload, this.brewery.name + '.jpg');
        axios.post('https://us-central1-brewery-finder-f943e.cloudfunctions.net/uploadImage', fd)
        .then(response => {
            console.log(response);
        });
        this.photoSelected = false;
        this.uploadSuccess = true;
        this.showPhotoUploader = false;
        this.showLogoUploader = true;
    },
    onUploadLogo(){
        const fd = new FormData();
        fd.append('image', this.fileToUpload, this.brewery.name + '-logo.jpg');
        axios.post('https://us-central1-brewery-finder-f943e.cloudfunctions.net/uploadImage', fd)
        .then(response => {
            console.log(response);
        });
        this.photoSelected = false;
        this.uploadSuccess = true;
        document.location.reload(true);
        this.$forceUpdate();
    }
  }


}
</script>

<style>

.brew-form-label{
  color: black;
}

.brew-upload-status-text{
  color: #d6d8da;
}

.flex-form{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
  "one two three"
  "four five six"
  "seven seven seven";
  border: .1rem solid black;
  background-color: rgba(251, 170, 27, .8);
  text-align: center;
}

.label-1{
  grid-area: one;
}

.label-2{
  grid-area: two;
}

.label-3{
  grid-area: three;
}

.label-4{
  grid-area: four;
}

.label-5{
  grid-area: five;
}

.label-6{
  grid-area: six;
}

.label-7{
  font-size: 1rem;
  font-weight: bold;
  margin-right: auto;
  margin-left: auto;
  width: 25%;
  grid-area: seven;
}


</style>