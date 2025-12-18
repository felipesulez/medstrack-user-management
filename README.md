# Medstrack User Management

## Executive Summary

Medstrack User Management es un microservicio backend desarrollado en **Java** con **Spring Boot**,
responsable de la gestión segura de usuarios dentro del ecosistema Medstrack.

El servicio está diseñado bajo principios de arquitectura limpia y separación de responsabilidades,
priorizando seguridad, mantenibilidad y escalabilidad desde etapas tempranas del desarrollo.

Este repositorio forma parte de una plataforma medtech orientada al seguimiento de medicamentos
y recordatorios de salud, y sirve tanto como base funcional del sistema como proyecto de portafolio
profesional enfocado en backend engineering de nivel productivo.

---
## Design Decisions

Las siguientes decisiones de diseño fueron tomadas para asegurar un backend robusto,
seguro y fácil de evolucionar en un entorno medtech:

### Arquitectura en capas
Se adoptó una arquitectura en capas para garantizar una separación clara de responsabilidades
entre controladores, lógica de negocio, dominio y persistencia, facilitando el mantenimiento
y la evolución independiente de cada capa.

### Seguridad por diseño
La autenticación y el manejo de credenciales se implementan utilizando Spring Security y
BCrypt, evitando el almacenamiento de contraseñas en texto plano y siguiendo buenas prácticas
recomendadas por OWASP (Open Web Application Security Project).

### Validación en el borde del sistema
Se utiliza Jakarta Bean Validation para asegurar que los datos de entrada sean validados
antes de ingresar a la lógica de negocio, reduciendo errores y estados inconsistentes.

### Persistencia desacoplada
El acceso a datos se abstrae mediante repositorios, permitiendo cambiar la tecnología de
persistencia sin afectar la lógica de negocio.

### Documentación de arquitectura automatizada
La arquitectura del sistema se documenta utilizando PlantUML y se genera automáticamente
mediante GitHub Actions, garantizando que los diagramas estén siempre alineados con el código.

---
## Arquitectura

El siguiente diagrama representa la arquitectura general del microservicio
Medstrack User Management, mostrando la separación por capas, los principales
componentes del sistema y los flujos de responsabilidad entre ellos.


[![Medstrack Architecture](docs/architecture/medstrack-architecture.svg)](docs/architecture/medstrack-architecture.svg)

---



##  Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Instalación y Configuración](#instalación-y-configuración)
- [Uso](#uso)
  - [Endpoints](#endpoints)
  - [Pruebas con Curl](#pruebas-con-curl)
- [Pendientes](#pendientes)
- [Contribución](#contribución)
- [Licencia](#licencia)
