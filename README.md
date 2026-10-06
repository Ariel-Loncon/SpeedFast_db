![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🧠  Actividad Sumativa 3: Persistiendo datos con objetos y bases de datos
## 👤 Autor del proyecto
- **Nombre completo:** [Ariel Gustavo Loncon Lefimil]
- **Sección:** [008A]
- **Carrera:** Desarrollo de aplicaciones
- **Sede:** [Online]

---

# Sistema de Gestión de Envíos - SpeedFast (Semana 8)

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II** (Evaluación Sumativa / Semana 8). La solución evoluciona la plataforma de SpeedFast al completar el ciclo funcional **CRUD (Crear, Leer, Actualizar y Eliminar)** mediante persistencia de datos en MySQL con **JDBC** e integración completa en una interfaz gráfica con **Java Swing**.

---

## 📋 Descripción del Proyecto y Nuevos Requerimientos

Esta etapa integra la lógica de negocio completa con la gestión de datos persistente. Permite administrar de manera interactiva repartidores, pedidos y la asignación de entregas en la base de datos `speedfast_db`, aplicando acceso seguro mediante `PreparedStatement`, manejo de excepciones SQL y validaciones de entrada en los formularios.

---

## ✨ Características y Principios Aplicados

### 1. CRUD Completo con JDBC y DAO
- **Operaciones Persistentes:** Implementación de métodos `create()`, `readAll()`, `update()` y `delete()` en las clases DAO (`PedidoDAO`, `RepartidorDAO`, `EntregaDAO`).
- **Seguridad en Consultas:** Uso estricto de `PreparedStatement` para prevenir inyección SQL y `ResultSet` para la lectura de datos.
- **Gestión Eficiente de Recursos:** Cierre seguro de conexiones mediante `try-with-resources` y manejo de excepciones con mensajes claros al usuario via `JOptionPane`.

### 2. Interfaz Gráfica Dinámica (Java Swing)
- **Formularios con Validación:** Control de campos obligatorios y formatos antes de ejecutar operaciones sobre la base de datos.
- **Visualización y Gestión en Tablas (`JTable`):** Sincronización en tiempo real para consultar, modificar el estado o eliminar registros de la base de datos.
- **Relaciones mediante `JComboBox`:** Asignación interactiva de entregas vinculando `Pedido` y `Repartidor` mediante desplegables cargados dinámicamente desde la BD.

### 3. Arquitectura y Buenas Prácticas
- **Separación en Capas:** Estructura limpia basada en los paquetes `model` (entidades y enums), `dao` (persistencia) y `view` (interfaz Swing).
- **Tipado Seguro:** Mapeo de enumeraciones (`EstadoPedido`) y tipos temporales (`LocalDate`, `LocalTime`) a MySQL.

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
    ├── VentanaListaEntregas.java
    ├── VentanaListaPedidos.java
    ├── VentanaListaRepartidores.java
    ├── VentanaPrincipal.java
    ├── VentanaRegistroEntrega.java
    ├── VentanaRegistroPedido.java
    └── VentanaRegistroRepartidor.java

--

**Fecha de entrega: [05/10/2026]

---

© Duoc UC | Escuela de Informática y Telecomunicaciones | Actividad Sumativa 3: Persistiendo datos con objetos y bases de datos.
