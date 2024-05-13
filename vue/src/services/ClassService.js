import axios from 'axios';

export default {

  getAllClassesAndSubclasses() {
    return axios.get('/classes');
  },

  
}
