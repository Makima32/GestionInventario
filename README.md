# Gestión de Inventario

Aplicación de escritorio desarrollada en Java Swing para la gestión de inventario de productos. Permite cargar, visualizar, editar y guardar productos en múltiples formatos de archivo.

## Características

- **Cargar productos** desde archivos CSV, JSON, XML y DAT
- **Visualizar productos** en una tabla interactiva
- **Editar productos** directamente en la tabla con validación en tiempo real
- **Añadir productos** mediante formulario modal
- **Eliminar productos** con confirmación
- **Guardar productos** en cualquier formato soportado
- **Nombre personalizado** para los archivos guardados

## Capturas de Pantalla

```
┌─────────────────────────────────────────────────────────────┐
│  Gestión de Inventario                           [_][□][X]  │
├─────────────────────────────────────────────────────────────┤
│  [Abrir]  [Guardar]  [Añadir Producto]  [Eliminar Producto] │
├─────────────────────────────────────────────────────────────┤
│  ┌─────────────────────────────────────────────────────┐    │
│  │ Nombre      │ Cantidad │ Precio    │ Categoría     │    │
│  ├─────────────────────────────────────────────────────┤    │
│  │ Laptop      │ 10       │ 899,99 €  │ Electrónica   │    │
│  │ Teclado     │ 25       │ 29,99 €   │ Electrónica   │    │
│  │ Silla       │ 15       │ 120,00 €  │ Muebles       │    │
│  └─────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────┘
```

## Tecnologías

- **Java SE 17+**
- **Swing** - Interfaz gráfica
- **Gson** - Procesamiento JSON
- **JAXB** - Procesamiento XML
- **Maven** - Gestión de dependencias

## Requisitos

- JDK 17 o superior
- Maven 3.6+

## Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/GestionInventario.git
cd GestionInventario
```

2. Compilar el proyecto:
```bash
mvn clean compile
```

3. Ejecutar la aplicación:
```bash
mvn exec:java
```

### Ejecución alternativa (sin Maven)

```bash
# Compilar
javac -cp ".:~/.m2/repository/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar" \
      -d target/classes \
      src/main/java/com/mycompany/**/*.java

# Ejecutar
java -cp "target/classes:~/.m2/repository/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar" \
     com.mycompany.gestioninventario.GestionInventario
```

## Estructura del Proyecto

```
GestionInventario/
├── src/main/java/com/mycompany/
│   ├── controller/           # Conversores de formato
│   │   ├── CsvAObjeto.java      # CSV → List<Producto>
│   │   ├── ObjetoACsv.java      # List<Producto> → CSV
│   │   ├── JsonAObjeto.java     # JSON → List<Producto>
│   │   ├── ObjetoAJson.java     # List<Producto> → JSON
│   │   ├── XmlAObjeto.java      # XML → List<Producto>
│   │   ├── ObjetoAXml.java      # List<Producto> → XML
│   │   ├── DatHelper.java       # Operaciones DAT
│   │   └── LeerDatos.java       # Utilidad lectura DAT
│   ├── models/               # Clases de datos
│   │   ├── Producto.java        # Modelo de producto
│   │   └── ListaProductos.java  # Wrapper para XML
│   ├── view/                 # Interfaz gráfica
│   │   ├── InventarioFrame.java    # Ventana principal
│   │   ├── ProductoTableModel.java # Modelo de tabla
│   │   └── ProductoDialog.java     # Diálogo añadir producto
│   └── gestioninventario/    # Punto de entrada
│       └── GestionInventario.java
├── src/main/java/com/mycompany/Recursos/  # Archivos de ejemplo
├── pom.xml                   # Configuración Maven
├── MANUAL_USUARIO.md         # Manual de usuario
├── CONTRIBUTORS.md           # Contribuidores
└── README.md                 # Este archivo
```

## Formatos Soportados

| Formato | Extensión | Descripción |
|---------|-----------|-------------|
| CSV | `.csv` | Valores separados por comas |
| JSON | `.json` | Formato de intercambio de datos |
| XML | `.xml` | Formato de marcado extensible |
| DAT | `.dat` | Serialización binaria de Java |

## Uso

### Abrir archivo
1. Clic en **Abrir**
2. Seleccionar archivo (.csv, .json, .xml, .dat)
3. Los productos se cargan en la tabla

### Editar producto
1. Doble clic en la celda a editar
2. Modificar el valor
3. Presionar Enter para confirmar

### Añadir producto
1. Clic en **Añadir Producto**
2. Completar el formulario
3. Clic en **Aceptar**

### Eliminar producto
1. Seleccionar fila en la tabla
2. Clic en **Eliminar Producto**
3. Confirmar eliminación

### Guardar archivo
1. Clic en **Guardar**
2. Seleccionar formato en el filtro
3. Escribir nombre del archivo
4. Clic en **Guardar**

## Contribuidores

- [Omar](https://github.com/Makima32)
- [Richard](https://github.com/riordi80)
- [Santiago](https://github.com/Santiagoooo1)
- [Enrique](https://github.com/Enrique36247)

Ver [CONTRIBUTORS.md](CONTRIBUTORS.md) para detalles de las contribuciones.

## Documentación

- [Manual de Usuario](MANUAL_USUARIO.md) - Guía completa de uso de la aplicación

## Licencia

Este proyecto es parte de una actividad académica del módulo de Acceso a Datos (DAM 2025-26).
