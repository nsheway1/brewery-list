import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    breweries: [],
    currentPage: '',
    filter: {breweryName: ''},
    breweriesLoaded: false,
    featuredKey: 0,
    featuredBrewURL: '',
    featured: null,
    itinerary: [],
    carouselKey: 0
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_BREWERIES(state, breweries) {
      state.breweries = breweries;
    },
    SET_FEATURED(state, brewery) {
      state.featured = brewery
    },
    SET_CURRENT_PAGE(state, currentPage){
      state.currentPage = currentPage;
    },
    INCREMENT_VOTE_COUNT(state){
      state.featuredKey++;
    },
    INCREMENT_CAROUSEL_COUNT(state){
      state.carouselKey++;
    },
    SET_FEATURED_URL(state, url){
      state.featuredBrewURL = url;
    },
    GENERATE_ITINERARY(state, itinerary){
      state.itinerary = itinerary;
    }
  }
})
