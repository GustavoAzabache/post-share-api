# Post Share API

API RESTful desarrollada en Spring Boot que permite a los usuarios crear, listar, actualizar y eliminar publicaciones. Incluye autenticación básica, roles de usuario, validaciones, manejo de errores y relaciones entre entidades.

---

## 🧩 Características

- Autenticación con Basic Auth
- Roles: USER, MODERATOR, ADMIN
- Validación de datos con Jakarta Validation
- Manejo global de errores (`@ControllerAdvice`)
- Seguridad con Spring Security
- CRUD para Usuarios y Publicaciones
- Asociación de publicaciones a un usuario

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3.5.3
- Spring Security 6
- JPA / Hibernate
- Lombok
- MapStruct
- MySQL

---

## 📁 Estructura del proyecto

```bash
src
└── main
    └── java
        └── com.me.post_share_api
            ├── config           # Configuraciones generales
            ├── controller       # Controladores REST
            ├── dto              # DTOs de petición y respuesta
            ├── entity           # Entidades JPA
            ├── exception        # Excepciones personalizadas
            ├── repository       # Repositorios JPA
            ├── service          # Interfaces y clases de lógica de negocio            
            └── ProjectManagementApiApplication.java
```

---

## ⚙️ Instalación

```bash
git clone https://github.com/GustavoAzabache/post-share-api.git
cd post-share-api
./mvn spring-boot:run
```

---

## 🔗 Endpoints

### 📃 Publicaciones (Posts)
| Método | Ruta                  | Descripción                   | Autenticación  |
|--------|-----------------------|-------------------------------|----------------|
| GET    | `/api/posts`          | Listar todos los posts        | ❌ No          |
| GET    | `/api/posts/{id}`     | Obtener post por ID           | ❌ No          |
| POST   | `/api/posts`          | Crear nuevo post              | ✅ Sí          |
| PUT    | `/api/posts/{id}`     | Actualizar post               | ✅ Sí          |
| DELETE | `/api/posts/{id}`     | Eliminar post                 | ✅ Sí          |

### 📃 Usuarios (Users)
| Método | Ruta                           | Descripción                       | Autenticación |
|--------|--------------------------------|-----------------------------------|----------------|
| GET    | `/api/users`                   | Listar todos los users            | ❌ No          |
| GET    | `/api/users/{id}`              | Obtener user por ID               | ❌ No          |
| GET    | `/api/users/{username}/posts`  | Listar todos los posts de un user | ❌ No          |
| POST   | `/api/users`                   | Crear nuevo user                  | ❌ No          |
| PUT    | `/api/users/{id}`              | Actualizar post                   | ✅ Sí          |
| DELETE | `/api/users/{id}`              | Eliminar post                     | ✅ Sí          |

---

## 🔐 Credenciales de prueba

| Usuario     | Rol   |  Contraseña |
|-------------|-------|-------------|
| `UserAdmin` | ADMIN | `useradmin` |
| `UserUser`  | USER  | `useruser`  |

---

## ⚠️ Manejo de errores

- `404 NOT FOUND`: Usuario o post no existe
- `409 CONFLICT`: Username ya en uso
- `403 FORBIDDEN`: Usuario intenta modificar/eliminar recursos ajenos
- `400 BAD REQUEST`: Datos inválidos en la solicitud

---

🧪 Ejemplo de respuesta de error

Cuando buscas un trabajo que no existe:

```JSON
{
  "status": 409,
  "error": "Conflicto de username",
  "message": "El username ingresado ya está siendo usado.
El username ingresado ya está siendo usado.",
  "timestamp": "2025-07-06T14:45:34.326154513"
}
```

---

## 📌 Futuras mejoras
- Pruebas unitarias con JUnit y Mockito
- Documentación con Swagger
- JWT Authentication
- Comentarios y likes en los posts

