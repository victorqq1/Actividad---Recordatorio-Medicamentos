## Integrantes:

* Quispe Quicaña, Victor Raul
* Chambi Chayña Shirley
---
# 💊 Recordatorio de Medicinas

Aplicación móvil desarrollada en **Jetpack Compose (Kotlin)** que ayuda a los adultos mayores a recordar el día y la hora en que deben tomar sus medicamentos.
Permite ajustar el **tamaño del texto** para mejorar la accesibilidad y guarda esa preferencia incluso después de cerrar la aplicación.

---

## 🚀 Funcionalidades principales

* ✅ Agregar recordatorios con nombre del medicamento, día y hora.
* 📋 Mostrar todos los recordatorios en una lista.
* 🔠 Ajustar el tamaño del texto mediante un **slider**.
* 💾 Guardar la configuración del tamaño de texto con **DataStore**, para mantenerla después de cerrar la app.

---

## 🧠 ¿Cómo recuerda la aplicación la configuración?

La app usa **Jetpack DataStore**, un sistema moderno para guardar datos de manera persistente.
Cuando el usuario cambia el tamaño del texto, el valor se guarda en el almacenamiento interno.
Luego, al volver a abrir la app, ese valor se **recupera automáticamente** para mantener la misma configuración visual.

---

## 🧩 Tecnologías utilizadas

* **Kotlin**
* **Jetpack Compose**
* **Material 3**
* **AndroidX DataStore (Preferences)**

---

## 📂 Estructura del proyecto

```
app/
 ├── MainActivity.kt          # Interfaz principal con Jetpack Compose
 ├── UserPreferences.kt       # Manejo del almacenamiento de preferencias con DataStore
 ├── build.gradle             # Dependencias del proyecto
```

---

## ⚙️ Instalación

1. Clona el repositorio o copia los archivos en Android Studio.

2. Agrega la dependencia de DataStore en el `build.gradle (Module)`:

   ```gradle
   implementation("androidx.datastore:datastore-preferences:1.1.1")
   ```

3. Ejecuta la aplicación en un emulador o dispositivo físico Android.

---

## 🧾 Ejemplo de uso

1. Ingresa el nombre del medicamento, el día y la hora.
2. Presiona **"Guardar Recordatorio"** para agregarlo a la lista.
3. Ajusta el tamaño de letra con el **slider** (de 14sp a 30sp).
4. Cierra y vuelve a abrir la app: el tamaño de texto se conservará.
