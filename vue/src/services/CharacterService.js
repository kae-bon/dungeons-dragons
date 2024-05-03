import axios from 'axios';

export default {

  getAllCharacters() {
    return axios.get('/users/characters');
  },

  editCharacter(id, character) {
    return axios.put(`/characters/${id}`, character)
  }

}