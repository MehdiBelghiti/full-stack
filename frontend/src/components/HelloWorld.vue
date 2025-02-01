<script setup lang="ts">
import { ref, onMounted } from "vue";
import { fetchImages, fetchImage } from "../api/http-api";
import ImageGallery from "./ImageGallery.vue";

// State variables
const images = ref<{ id: number; name: string }[]>([]);
const selectedImageId = ref<number | null>(null);
const imageSrc = ref<string | null>(null);
const showGallery = ref(false);

// Fetch images list from backend
const loadImages = async () => {
  images.value = await fetchImages();
};

// Fetch selected image and convert to base64 for display
const loadImage = async (id: number) => {
  imageSrc.value = await fetchImage(id);
};

onMounted(loadImages);
</script>

<template>
  <div class="container">
    <h1>Image Selector</h1>
    
    <!-- Toggle Gallery Button -->
    <button class="toggle-button" @click="showGallery = !showGallery">
      {{ showGallery ? "Hide Gallery" : "Show Gallery" }}
    </button>
    
    <ImageGallery v-if="showGallery" />
    
    <!-- Dropdown to select an image -->
    <div class="dropdown-container">
      <label for="imageSelect">Choose an image:</label>
      <select id="imageSelect" v-model="selectedImageId" @change="loadImage(selectedImageId!)">
        <option v-for="image in images" :key="image.id" :value="image.id">
          {{ image.name }}
        </option>
      </select>
    </div>

    <!-- Display the selected image -->
    <div v-if="imageSrc" class="image-container">
      <h2>Selected Image</h2>
      <img :src="imageSrc" alt="Selected Image" />
    </div>
  </div>
</template>

<style scoped>
.container {
  max-width: 600px;
  margin: auto;
  text-align: center;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
  color: #333;
  margin-bottom: 20px;
}

.toggle-button {
  margin-bottom: 15px;
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.toggle-button:hover {
  background-color: #0056b3;
}

.dropdown-container {
  margin: 15px 0;
}

select {
  padding: 8px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.image-container {
  margin-top: 20px;
}

.image-container img {
  max-width: 100%;
  height: auto;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>
