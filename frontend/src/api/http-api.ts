import axios from "axios";

// Fetch the list of images from the backend
export const fetchImages = async () => {
  try {
    const response = await axios.get("/images");
    return response.data;
  } catch (error) {
    console.error("Error fetching images:", error);
    return [];
  }
};

// Fetch a single image by ID and return as base64
export const fetchImage = async (id: number) => {
  try {
    const response = await axios.get(`/images/${id}`, { responseType: "blob" });
    const reader = new FileReader();
    return new Promise<string>((resolve) => {
      reader.readAsDataURL(response.data);
      reader.onload = () => resolve(reader.result as string);
    });
  } catch (error) {
    console.error("Error fetching image:", error);
    return null;
  }
};

// Upload an image to the backend
export const uploadImage = async (file: File) => {
  const formData = new FormData();
  formData.append("file", file);

  try {
    await axios.post("/images", formData, {
      headers: { "Content-Type": "multipart/form-data" }
    });
  } catch (error) {
    console.error("Error uploading image:", error);
  }
};
