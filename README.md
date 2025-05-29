# 📚 Sistema Académico - Spring Boot

Este proyecto es un sistema académico desarrollado con Java y Spring Boot como parte de la evaluación del **tercer corte** de la asignatura **Programación Web**. Su propósito es simular la gestión académica de una institución educativa, permitiendo la administración de estudiantes, docentes, cursos, evaluaciones, comunicaciones, entre otros aspectos clave.

Está diseñado aplicando buenas prácticas de **arquitectura por capas** y principios de **diseño limpio**, bajo la guía del profesor **Jonathan Rolando**.

---

## 🚀 Características Principales

- Gestión completa de estudiantes, docentes, cursos y calificaciones.
- API RESTful organizada por módulos funcionales.
- Arquitectura basada en servicios (Service, Repository, Controller, Model).
- Persistencia con Spring Data JPA.

---

## 🧩 Módulos Funcionales

### 🎓 Gestión de Estudiantes *(Responsable: Sebastian Lopez)*

- Registro, modificación y eliminación de estudiantes.
- Matrícula e inscripción con verificación de requisitos.
- Control de asistencia y reportes.
- Consulta de historial académico.

### 👨‍🏫 Gestión de Docentes *(Responsable: Jesus Chinchilla)*

- Registro y mantenimiento de datos docentes.
- Asignación de cursos según disponibilidad.
- Gestión de evaluaciones.

### 📘 Gestión de Cursos y Asignaturas *(Responsable: Juan Arguello)*

- Creación y edición de cursos.
- Programación de horarios sin solapamientos.
- Inscripción y cancelación de materias.

### 📝 Evaluación y Calificaciones *(Responsable: Jonathan Guevara)*

- Registro y modificación de notas.
- Promedios, estadísticas y retroalimentación académica.

### 💬 Comunicación y Notificaciones *(Responsable: Jesus Chinchilla)*

- Mensajería interna entre usuarios del sistema.
- Alertas y recordatorios automáticos.

### 🧑‍🔬 Recursos Académicos *(Responsable: Juan Arguello)*

- Reserva de aulas y laboratorios.
- Gestión de materiales, libros y equipos.

### 🛡️ Módulo Administrativo y Seguridad *(Responsables: Jonathan Guevara, Sebastian López)*

- Gestión de roles y permisos.
- Autenticación y autorización con Spring Security.
- Pruebas unitarias e integración.

---

## 🛠️ Tecnologías Utilizadas

- Java 24+
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- Spring Security
- Lombok
- H2 / MySQL / PostgreSQL
- Maven

---

## 📁 Estructura del Proyecto

```plaintext
Sistema_Academico-main/
├── .mvn/                         # Configuración del wrapper de Maven
│   └── wrapper/
│       └── maven-wrapper.properties
│
├── frontend/                     # Proyecto frontend (estructura vacía por ahora)
│   └── frontend_system_academic/
│       └── src/
│           └── main/
│               ├── java/        # Código fuente Java
│               │   └── com/     # Paquete base (organización del sistema)
│               └── resources/   # Archivos de configuración
│                   └── application.properties
│
├── test/
│   └── java/                     # Pruebas unitarias
│       └── com/
│
├── .gitignore
├── README.md
├── pom.xml                       # Archivo de configuración de dependencias Maven
├── mvnw                          # Script para ejecutar Maven (Linux/Mac)
└── mvnw.cmd                      # Script para ejecutar Maven (Windows)


La separación entre service/ y serviceImpl/ en el código sigue el principio de inversión de dependencias, lo que facilita las pruebas, mantenimiento y desacoplamiento.

📦 Dependencias (pom.xml)
🔧 Principales
spring-boot-starter-web – Construcción de APIs REST.

spring-boot-starter-data-jpa – Persistencia con JPA/Hibernate.

spring-boot-starter-security – Autenticación y autorización.

spring-boot-devtools – Recarga automática durante desarrollo.

lombok – Reducción de código repetitivo.

mysql-connector-java / postgresql – Drivers de conexión a BD.

spring-boot-starter-actuator – Monitoreo y métricas del sistema.

👥 Contribuidores

| Nombre              | Correo                             | GitHub                                      |
|---------------------|------------------------------------|---------------------------------------------|
| Juan Arguello       | juandavidaa@ufps.edu.co            | [Arguellis17](https://github.com/Arguellis17) |
| Jonathan Guevara    | jonathangc@ufps.edu.co             | [JonathanGuevaraC](https://github.com/JonathanGuevaraC) |
| Jesus Chinchilla    | jesusdavidcm@ufps.edu.co           | [JesusDavidChinchillaMachuca](https://github.com/JesusDavidChinchillaMachuca) |
| Sebastian Lopez     | johansebastianlo@ufps.edu.co       | [SebsMusicLive](https://github.com/SebsMusicLive) |

🚀 Clonar el Proyecto

git clone https://github.com/SebsMusicLive/Sistema_Academico.git

📄 Licencia
Este proyecto está bajo la licencia MIT.

📅 Cronograma de Actividades
🔗 [Ver Cronograma en Google Sheets](https://docs.google.com/spreadsheets/d/1Na84YfwvMOs03YQNCtL16cIsvZBUrKN0/edit?gid=504086334#gid=504086334)
