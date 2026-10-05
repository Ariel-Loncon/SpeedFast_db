![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🧠  Actividad Formativa 5: Conectando aplicaciones java con bases de datos mediante JDBC

## 👤 Autor del proyecto
- **Nombre completo:** [Ariel Gustavo Loncon Lefimil]
- **Sección:** [008A]
- **Carrera:** Desarrollo de aplicaciones
- **Sede:** [Online]

---

# Sistema de Gestión de Envíos - SpeedFast (Semana 7)

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II** (Evaluación Formativa / Semana 7). La solución evoluciona la plataforma de SpeedFast al integrar **persistencia de datos en MySQL** a través de **JDBC**, permitiendo el registro y consulta de información en tiempo real desde una interfaz gráfica construida con **Java Swing**.

---

## 📋 Descripción del Proyecto y Nuevos Requerimientos

En esta etapa se implementa la arquitectura de acceso a datos mediante la capa **DAO (Data Access Object)** y el conector **JDBC**. La aplicación permite registrar y consultar de forma persistente los pedidos, repartidores y la asignación de entregas directamente en la base de datos MySQL `speedfast_db`, garantizando la integridad de los datos entre ejecuciones.

---

## ✨ Características y Principios Aplicados

### 1. Gestión de Conexión a Base de Datos (`ConexionBD`)
- **Control de Recursos JDBC:** Implementa una clase centralizada para gestionar la conexión con MySQL mediante `DriverManager`.
- **Manejo Seguro de Recursos:** Uso del patrón `try-with-resources` para la apertura y cierre automático de conexiones (`Connection`), declaraciones (`PreparedStatement`) y conjuntos de resultados (`ResultSet`).

### 2. Patrón de Diseño DAO (Data Access Object)
- **Capa de Persistencia:** Separación limpia de la lógica de negocio y la interfaz gráfica respecto a las operaciones SQL (`INSERT`, `SELECT`).
- **Prevención de Inyección SQL:** Implementación estricta de `PreparedStatement` para parametrizar de forma segura todas las consultas enviadas a la base de datos.
- **Mapeo Objeto-Relacional Manual:** Conversión transparente entre registros de las tablas MySQL y las instancias de objetos Java (`Pedido`, `Repartidor`, `Entrega`).

### 3. Interfaz Gráfica con Swing e Integración en Tiempo Real
- **Formularios de Registro:** Captura de datos validados para pedidos, repartidores y asignación de entregas mediante componentes Swing (`JTextField`, `JComboBox`).
- **Visualización Dinámica (`JTable`):** Consulta e inserción de datos en tiempo real mediante `DefaultTableModel`, reflejando directamente el contenido almacenado en la base de datos.

### 4. Integridad de Datos y Tipado Seguro
- **Soporte de Fechas y Horas:** Mapeo de `LocalDate` y `LocalTime` de Java 8+ hacia tipos SQL (`DATE` y `TIME`) en la tabla `entrega`.
- **Persistencia de Enums:** Conversión bidireccional entre la enumeración `EstadoPedido` (`PENDIENTE`, `EN_REPARTO`, `ENTREGADO`) y la columna `VARCHAR` de MySQL.

---

## 🛠️ Estructura del Proyecto

```text
src/
├── app/
│   └── Main.java
├── dao/
│   ├── ConexionBD.java
│   ├── EntregaDAO.java
│   ├── PedidoDAO.java
│   └── RepartidorDAO.java
├── model/
│   ├── Entrega.java
│   ├── EstadoPedido.java
│   ├── Pedido.java
│   └── Repartidor.java
└── view/
    ├── VentanaListaPedidos.java
    ├── VentanaPrincipal.java
    ├── VentanaRegistroEntrega.java
    ├── VentanaRegistroPedido.java
    └── VentanaRegistroRepartidor.java

--

**Fecha de entrega: [28/09/2026]

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Actividad Formativa 5: Conectando aplicaciones java con bases de datos mediante JDBC
