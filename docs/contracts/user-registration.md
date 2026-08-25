# User Registration Contract

## Endpoint

`POST /api/usuarios/registro`

## Description

Este contrato define el comportamiento del registro de usuarios en Medstrack.

La API debe validar los datos recibidos, evitar el registro de correos duplicados y almacenar la contraseña de forma segura.

---

## Request

### Headers

Content-Type: application/json

### Body

{
  "correo": "felipe@example.com",
  "nombre": "Felipe",
  "password": "Medstrack1"
}

---

## Validation Rules

### correo

- Obligatorio.
- Debe tener formato de correo electrónico válido.
- Debe ser único.

### nombre

- Obligatorio.
- No puede estar vacío.

### password

- Obligatoria.
- Mínimo 8 caracteres.
- Máximo 12 caracteres.
- Nunca debe almacenarse en texto plano.

---

## Responses

### 201 Created

Registro exitoso.

{
  "success": true,
  "message": "Usuario registrado correctamente",
  "data": null
}

### 400 Bad Request

Los datos enviados no cumplen las reglas de validación.

{
  "success": false,
  "message": "Datos de entrada inválidos",
  "data": null
}

### 409 Conflict

El correo electrónico ya está registrado.

{
  "success": false,
  "message": "El correo ya está registrado",
  "data": null
}

### 500 Internal Server Error

Error inesperado del servidor.

{
  "success": false,
  "message": "Ocurrió un error inesperado en el servidor",
  "data": null
}

---

## Security

La contraseña debe almacenarse utilizando un algoritmo de hashing seguro.

La API nunca debe devolver la contraseña en una respuesta.

---

## Business Rules

1. Un correo solo puede pertenecer a un usuario.
2. Un usuario válido debe tener correo, nombre y contraseña.
3. Una contraseña válida debe cumplir las restricciones definidas anteriormente.
4. Los errores de negocio deben diferenciarse de los errores internos del servidor.
