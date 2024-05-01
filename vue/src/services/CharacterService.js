import axios from 'axios';

export default {

  getAllCharacters() {
    console.log('hello');
    return axios.get('/users/characters');
  },

}