# 📚 Sistema de Gestión Bibliotecaria - BiblioCity

## 📌 Descripción
BiblioCity es una solución web desarrollada para la Biblioteca Municipal, diseñada para migrar registros físicos a un sistema digital eficiente. El sistema gestiona libros, socios, préstamos y multas, garantizando la integridad de los datos mediante reglas de negocio automatizadas.

## 🛠️ Tecnologías Utilizadas
- **Lenguaje:** Java 21
- **Framework:** Spring Boot 4.0.5
- **Base de Datos:** PostgreSQL
- **Persistencia:** Spring Data JPA (Hibernate)
- **Seguridad:** Spring Security (Configuración básica permitAll para pruebas)
- **Gestión de Dependencias:** Maven

## 🏗️ Arquitectura
Se implementó una arquitectura **N-Capas (Multicapa)** para asegurar la separación de responsabilidades:
- **Controller:** Endpoints REST para la interacción con el cliente.
- **Service:** Capa lógica donde se ejecutan las reglas de negocio críticas.
- **Repository:** Abstracción de acceso a datos mediante JPA.
- **Entity:** Modelado físico de la base de datos.
- **DTO:** Objetos de transferencia de datos para desacoplar la API del modelo.

## ⚖️ Reglas de Negocio Implementadas
1. **Límite de Préstamos:** Un socio no puede tener más de 3 préstamos activos simultáneamente.
2. **Disponibilidad de Stock:** No se permite el préstamo si el libro no tiene ejemplares disponibles.
3. **Restricción por Multas:** Socios con multas pendientes tienen prohibido realizar nuevos préstamos.
4. **Multas Automáticas:** El sistema genera una multa automáticamente si la devolución supera la fecha estimada.
5. **Integridad de Eliminación:** No se permite eliminar libros o socios con préstamos activos.

## 🚀 Instrucciones de Ejecución
1. Configurar la base de datos en PostgreSQL con el nombre `bibliocitydb`.
2. Ajustar las credenciales en `src/main/resources/application.properties`.
3. Ejecutar el comando `./mvnw spring-boot:run`.
4. El sistema estará disponible en `http://localhost:8080`.
