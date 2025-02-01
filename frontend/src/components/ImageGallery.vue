<script setup lang="ts">
import { ref, onMounted } from "vue";
import { fetchImages, fetchImage, uploadImage } from "../api/http-api";

// State variables
const images = ref<{ id: number; name: string; src?: string }[]>([]);
const selectedFile = ref<File | null>(null);

// Fetch images and load them as base64
const loadImages = async () => {
  const imageList = await fetchImages();
  for (const image of imageList) {
    image.src = await fetchImage(image.id);
  }
  images.value = imageList;
};

const handleFileUpload = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files) {
    selectedFile.value = target.files[0];
  }
};

const submitImage = async () => {
  if (!selectedFile.value) return;
  await uploadImage(selectedFile.value);
  selectedFile.value = null;
  loadImages(); // Refresh the image list after upload
};

onMounted(loadImages);
</script>

<template>
  <div>
    <h1>Image Gallery</h1>
    <form @submit.prevent="submitImage">
      <input type="file" @change="handleFileUpload" accept="image/*" />
      <button type="submit">Upload</button>
    </form>
    <div class="gallery">
      <div v-for="image in images" :key="image.id" class="gallery-item">
        <img :src="image.src" :alt="image.name" />
        <p>{{ image.name }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.gallery {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.gallery-item {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: center;
}
.gallery-item img {
  max-width: 150px;
  height: auto;
  display: block;
  margin: auto;
}
form {
  margin-bottom: 20px;
}
</style>
