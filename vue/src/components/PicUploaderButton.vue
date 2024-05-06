<template>
  <div class="cloud upload">
    <button class="btn btn-secondary" v-on:click.prevent="upload">Upload Image</button>
  </div>
</template>

<script>

export default {
  data() {
    return {
      imgUrl: "",
    }
  },
  methods: {
    upload() {
      const uploadWidget = cloudinary.createUploadWidget({
        cloudName: import.meta.env.VITE_CLOUDINARY_CLOUD_NAME,
        uploadPreset: import.meta.env.VITE_CLOUDINARY_CLOUD_UPLOAD,
        sources: ['local', 'url', 'camera']
      }, (error, result) => {
        if (!error && result && result.event === "success") {
          this.imgUrl = result.info.url;
          
          this.uploadImage();
        }
      });

      let text = "Allow access to Photos and Camera";
      if (confirm(text) == true) {
        uploadWidget.open();
      } else {
        alert("Upload canceled!");
      }
    },
    uploadImage() {
      this.$emit('imageUploaded', this.imgUrl);
    }
  }
}
</script>

<style scoped>
button {
  color: #fcfcff;
  font-size: .8rem;
}
</style>