# Rapport d'Avancement - Projet Vue.js & Spring Boot

## ✅ État d'Avancement

J'ai pu réaliser avec succès toutes les fonctionnalités demandées dans ce projet de développement d'une application web avec **Vue.js (Vite + TypeScript)** pour le frontend et **Spring Boot** pour le backend. Voici les différentes étapes accomplies :

### 🎯 Backend (Spring Boot 3.4.2 - Java 17)
1. **Création d'un API REST pour la gestion des images**
   - Endpoint `GET /images` : Récupération de la liste des images.
   - Endpoint `GET /images/{id}` : Récupération d’une image spécifique.
   - Endpoint `POST /images` : Upload d’une image vers le serveur.
   - Endpoint `DELETE /images/{id}` : Suppression d’une image.

2. **Tests unitaires avec JUnit 5 et MockMvc**
   - Test des endpoints pour s’assurer qu’ils fonctionnent correctement.

3. **Proxy de redirection pour gérer la communication entre le frontend et le backend**
   - Ajout de la configuration `proxy` dans `vite.config.ts` pour éviter les erreurs CORS.

---

### 🎨 Frontend (Vue.js - TypeScript - Vite)
1. **Installation et configuration du projet avec Vite**
   - Génération du projet avec `npm create vite@latest frontend --template vue-ts`.
   - Installation des dépendances nécessaires (`npm install axios`).

2. **Affichage de la liste des images avec Vue.js et Axios**
   - Création du composant `HelloWorld.vue` pour récupérer et afficher la liste des images sous forme de menu déroulant.

3. **Ajout d’une galerie d’images**
   - Création du composant `ImageGallery.vue` pour afficher toutes les images du serveur.
   - Factorisation du code d’accès à l’API dans `http-api.ts` pour centraliser les appels réseau.

4. **Ajout du téléversement d’images**
   - Formulaire d’upload d’image intégré à `ImageGallery.vue`.
   - Envoi du fichier via `POST /images` et rafraîchissement de la liste après un upload réussi.

5. **Stylisation des composants**
   - Ajout de styles CSS pour améliorer l’interface utilisateur (boutons, liste déroulante, galerie, etc.).

---

## ❗ Difficultés Rencontrées

### 🔹 Problème d’import des fichiers TypeScript dans Vue.js
Lors de l’importation de `http-api.ts` dans `HelloWorld.vue`, Vite ne trouvait pas le fichier. Solution :
- Vérification de l’emplacement du fichier dans `src/api/http-api.ts`.
- Correction de l’import, Vite ne trouvait pas l’import (`@/api/`) : `import { fetchImages, fetchImage } from "../api/http-api";`
- Amélioration potentielle: Ajout d’un alias dans `vite.config.ts` pour simplifier l’import (`@/api/`).

### 🔹 Erreur de dépassement de taille d’upload (`MaxUploadSizeExceededException`)
Spring Boot bloque les fichiers trop volumineux. Solution :
- Ajout des paramètres dans `application.properties` :
  ```properties
  spring.servlet.multipart.max-file-size=5MB
  spring.servlet.multipart.max-request-size=10MB
  ```

### 🔹 Difficulté à récupérer une image en Base64
Lors du téléchargement d’une image avec Axios, nous avons dû utiliser `responseType: "blob"` et `FileReader()` pour convertir les données en Base64.

```ts
axios.get(`/images/${id}`, { responseType: "blob" })
  .then(response => {
    const reader = new FileReader();
    reader.readAsDataURL(response.data);
    reader.onload = () => {
      imageSrc.value = reader.result as string;
    };
  });
```

---

## 🚀 Conclusion
Malgré quelques difficultés techniques, nous avons réussi à implémenter toutes les fonctionnalités du projet. Ce TP nous a permis de :
- **Utiliser Spring Boot pour créer une API RESTful**.
- **Manipuler Vue.js avec TypeScript et gérer les requêtes API avec Axios**.
- **Gérer le téléversement et l'affichage dynamique d’images**.
- **Structurer un projet web moderne et bien organisé**.

Le projet est maintenant fonctionnel, stylisé et évolutif ! 🎉

---

📌 **Prochaines améliorations possibles :**
- Ajouter un système de pagination pour la galerie d’images.
- Afficher un message de confirmation après chaque action (upload, suppression).
- Implémenter un système d’authentification pour sécuriser l’accès aux images.

Bravo pour le travail accompli ! 🚀🔥

