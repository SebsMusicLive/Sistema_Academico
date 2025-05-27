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

### 🎓 Gestión de Estudiantes *(Responsable: Sebas)*

- **Registro y Actualización:** Alta, modificación y eliminación de información personal y académica de estudiantes.
- **Matrícula e Inscripción:** Gestión de matrículas e inscripción a cursos, con verificación de requisitos.
- **Gestión de Asistencia:** Registro de asistencia y generación de reportes de inasistencia.
- **Historial Académico:** Consulta del rendimiento académico y cursos aprobados.

---

### 👨‍🏫 Gestión de Docentes *(Responsable: Chinchilla)*

- **Registro y Actualización:** Alta, modificación y eliminación de datos de docentes.
- **Asignación de Cursos:** Asignación de cursos a docentes según disponibilidad.
- **Evaluaciones:** Creación y registro de evaluaciones, exámenes, y actividades académicas.

---

### 📘 Gestión de Cursos y Asignaturas *(Responsable: Arguello)*

- **Creación y Administración:** Diseño y modificación de cursos, objetivos y contenidos.
- **Programación de Horarios:** Organización de horarios evitando solapamientos.
- **Inscripción y Cancelación:** Inscripción/cancelación en cursos con validación de cupos y prerrequisitos.

---

### 📝 Evaluación y Calificaciones *(Responsable: Jonathan)*

- **Registro de Calificaciones:** Ingreso y modificación de notas.
- **Promedios y Estadísticas:** Cálculo de promedios y reportes de desempeño.
- **Retroalimentación:** Comunicación de resultados entre docentes y estudiantes.

---

### 💬 Comunicación y Notificaciones *(Responsable: Jesus Chinchilla)*

- **Mensajería Interna:** Comunicación entre estudiantes, docentes y administrativos.
- **Alertas y Recordatorios:** Notificaciones automáticas por email o push.

---

### 🧑‍🔬 Recursos Académicos *(Responsable: Juan Arguello)*

- **Reserva de Aulas y Laboratorios:** Asignación y reserva de espacios físicos y virtuales.
- **Gestión de Materiales:** Préstamo y mantenimiento de libros y equipos.

---

### 🛡️ Módulo Administrativo y Seguridad *(Responsables: Jonathan Colmenares, Johan López)*

- **Roles y Permisos:** Asignación de roles (docente, estudiante, admin) y niveles de acceso.
- **Autenticación y Autorización:** Inicio de sesión seguro con Spring Security.
- **Testing y Corrección:** Pruebas unitarias e integración para garantizar la calidad.

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

```
src/
└── main/
    ├── java/
    │   └── sistemaAcademico/
    │       ├── controllers/      # Controladores REST
    │       ├── model/            # Entidades (JPA)
    │       ├── repository/       # Repositorios (JpaRepository)
    │       ├── service/          # Interfaces de servicios
    │       └── serviceImpl/      # Implementaciones de los servicios
    └── resources/                # Configuración (application.properties, etc.)
```

> La separación entre `service/` y `serviceImpl/` sigue el principio de inversión de dependencias, facilitando pruebas, mantenimiento y desacoplamiento.

---

## 📦 Dependencias (pom.xml)

### 🔧 Dependencias Principales

- `spring-boot-starter-web` – Construcción de APIs REST.
- `spring-boot-starter-data-jpa` – Persistencia con JPA/Hibernate.
- `spring-boot-starter-security` – Autenticación y autorización.
- `spring-boot-devtools` – Recarga automática durante desarrollo.
- `lombok` – Reducción de boilerplate.
- `postgresql` / `mysql-connector-java` – Drivers de conexión a BD.
- `spring-boot-starter-actuator` – Monitoreo y métricas.

---

## 👥 Contribuidores

| Nombre              | Correo                             | GitHub                                      |
|---------------------|------------------------------------|---------------------------------------------|
| Juan Arguello       | juandavidaa@ufps.edu.co            | [Arguellis17](https://github.com/Arguellis17) |
| Jonathan Guevara    | jonathangc@ufps.edu.co             | [JonathanGuevaraC](https://github.com/JonathanGuevaraC) |
| Jesus Chinchilla    | jesusdavidcm@ufps.edu.co           | [JesusDavidChinchillaMachuca](https://github.com/JesusDavidChinchillaMachuca) |
| Sebastian Lopez     | johansebastianlo@ufps.edu.co       | [SebsMusicLive](https://github.com/SebsMusicLive) |

---

## 🚀 Clonar el Proyecto

```bash
git clone https://github.com/SebsMusicLive/Sistema_Academico.git
```

---

## 📄 Licencia

Este proyecto está bajo la licencia **MIT**.

---

## 📅 Cronograma de Actividades

🔗 [Ver Cronograma en Google Sheets](https://docs.google.com/spreadsheets/d/1Na84YfwvMOs03YQNCtL16cIsvZBUrKN0/edit?gid=504086334#gid=504086334)