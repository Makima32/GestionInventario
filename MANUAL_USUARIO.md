# Manual de Usuario - Gestión de Inventario

## 1. Introducción

**Gestión de Inventario** es una aplicación de escritorio desarrollada en Java que permite gestionar productos de un inventario. La aplicación ofrece funcionalidades para cargar, visualizar, editar y guardar productos en múltiples formatos de archivo.

### Características principales:
- Cargar productos desde archivos CSV, JSON, XML y DAT
- Visualizar productos en una tabla interactiva
- Editar productos directamente en la tabla
- Añadir nuevos productos mediante formulario
- Eliminar productos existentes
- Guardar productos en cualquier formato soportado
- Elegir nombre personalizado para los archivos guardados

---

## 2. Requisitos del Sistema

### Requisitos mínimos:
- **Sistema Operativo:** Windows 10/11, Linux o macOS
- **Java:** JDK 17 o superior
- **Memoria RAM:** 512 MB mínimo
- **Espacio en disco:** 50 MB

### Dependencias incluidas:
- Gson (procesamiento JSON)
- JAXB (procesamiento XML)
- Java Serialization (archivos binarios)

---

## 3. Instalación y Ejecución

### Desde un IDE (IntelliJ IDEA / NetBeans / Eclipse):
1. Abrir el proyecto en el IDE
2. Ejecutar la clase `GestionInventario.java` ubicada en:
   ```
   src/main/java/com/mycompany/gestioninventario/GestionInventario.java
   ```
3. La ventana de la aplicación se abrirá automáticamente

### Desde línea de comandos (con Maven):
```bash
cd GestionInventario
mvn clean compile
mvn exec:java
```

---

## 4. Interfaz de Usuario

### 4.1 Ventana Principal

Al iniciar la aplicación, se muestra la ventana principal con los siguientes elementos:

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
│  │ ...         │ ...      │ ...       │ ...           │    │
│  └─────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────┘
```

### 4.2 Barra de Herramientas

| Botón | Función |
|-------|---------|
| **Abrir** | Abre un archivo de productos existente |
| **Guardar** | Guarda los productos en un archivo |
| **Añadir Producto** | Abre formulario para crear nuevo producto |
| **Eliminar Producto** | Elimina el producto seleccionado |

### 4.3 Tabla de Productos

La tabla muestra los productos con las siguientes columnas:

| Columna | Descripción | Editable |
|---------|-------------|----------|
| **Nombre** | Nombre del producto | Sí |
| **Cantidad** | Unidades disponibles (número entero) | Sí |
| **Precio** | Precio en euros (con formato €) | Sí |
| **Categoría** | Categoría del producto | Sí |

---

## 5. Guía de Uso

### 5.1 Abrir un Archivo de Productos

1. Haga clic en el botón **"Abrir"**
2. Se abrirá un selector de archivos
3. Navegue hasta la ubicación del archivo
4. Seleccione el archivo (formatos soportados: .csv, .json, .xml, .dat)
5. Haga clic en **"Abrir"**
6. Los productos se cargarán en la tabla

**Formatos soportados:**
- **CSV** (.csv) - Formato de texto con valores separados por comas
- **JSON** (.json) - Formato de intercambio de datos ligero
- **XML** (.xml) - Formato de marcado extensible
- **DAT** (.dat) - Formato binario de Java (serialización)

**Nota:** La aplicación detecta automáticamente el formato por la extensión del archivo.

### 5.2 Editar Productos en la Tabla

1. Haga **doble clic** en la celda que desea editar
2. Modifique el valor
3. Presione **Enter** o haga clic fuera de la celda para confirmar

**Validaciones automáticas:**
- **Nombre:** No puede estar vacío
- **Cantidad:** Debe ser un número entero no negativo
- **Precio:** Debe ser un número decimal no negativo
- **Categoría:** No puede estar vacía

Si introduce un valor inválido, aparecerá un mensaje de error.

### 5.3 Añadir un Nuevo Producto

1. Haga clic en el botón **"Añadir Producto"**
2. Se abrirá un formulario con los campos:
   - Nombre
   - Cantidad
   - Precio
   - Categoría
3. Complete todos los campos
4. Haga clic en **"Aceptar"** para añadir el producto
5. O haga clic en **"Cancelar"** para cerrar sin guardar

**Atajos de teclado:**
- **Enter:** Confirma y añade el producto
- **Escape:** Cancela y cierra el formulario

### 5.4 Eliminar un Producto

1. Seleccione el producto haciendo clic en su fila
2. Haga clic en el botón **"Eliminar Producto"**
3. Aparecerá un diálogo de confirmación:
   ```
   ¿Está seguro de eliminar el producto 'NombreProducto'?
   ```
4. Haga clic en **"Sí"** para confirmar o **"No"** para cancelar

**Nota:** Si no hay ningún producto seleccionado, aparecerá un mensaje de advertencia.

### 5.5 Guardar Productos en un Archivo

1. Haga clic en el botón **"Guardar"**
2. Se abrirá un selector de archivos en modo guardado
3. Seleccione el formato deseado en el filtro desplegable:
   - Archivos CSV (*.csv)
   - Archivos JSON (*.json)
   - Archivos XML (*.xml)
   - Archivos DAT (*.dat)
4. Escriba el nombre del archivo
5. Haga clic en **"Guardar"**

**Nota:** La aplicación añade automáticamente la extensión correcta si no la especifica.

---

## 6. Formatos de Archivo

### 6.1 Formato CSV

Archivo de texto plano con valores separados por comas.

**Estructura:**
```
Nombre,Cantidad,Precio,Categoria
Laptop,10,899.99,Electrónica
Teclado,25,29.99,Electrónica
Silla,15,120.0,Muebles
```

**Características:**
- Fácil de editar con cualquier editor de texto
- Compatible con Excel y hojas de cálculo
- No incluye cabecera (solo datos)

### 6.2 Formato JSON

Formato de intercambio de datos estructurado.

**Estructura:**
```json
[
  {
    "nombre": "Laptop",
    "cantidad": 10,
    "precio": 899.99,
    "categoria": "Electrónica"
  },
  {
    "nombre": "Teclado",
    "cantidad": 25,
    "precio": 29.99,
    "categoria": "Electrónica"
  }
]
```

**Características:**
- Formato legible y estructurado
- Ampliamente utilizado en aplicaciones web
- Soporta tipos de datos nativos

### 6.3 Formato XML

Formato de marcado extensible.

**Estructura:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<inventario>
  <producto>
    <nombre>Laptop</nombre>
    <cantidad>10</cantidad>
    <precio>899.99</precio>
    <categoria>Electrónica</categoria>
  </producto>
  <producto>
    <nombre>Teclado</nombre>
    <cantidad>25</cantidad>
    <precio>29.99</precio>
    <categoria>Electrónica</categoria>
  </producto>
</inventario>
```

**Características:**
- Formato con etiquetas jerárquicas
- Fácil de validar con esquemas
- Compatible con sistemas empresariales

### 6.4 Formato DAT (Binario)

Formato de serialización nativa de Java.

**Características:**
- Formato binario compacto
- Carga y guardado más rápido
- Solo legible por aplicaciones Java
- Ideal para uso interno de la aplicación

---

## 7. Mensajes de Error Comunes

### Error: "Formato de archivo no reconocido"
**Causa:** El archivo seleccionado no tiene una extensión válida.
**Solución:** Asegúrese de que el archivo tenga extensión .csv, .json, .xml o .dat

### Error: "Valor numérico inválido"
**Causa:** Se intentó introducir texto en un campo numérico.
**Solución:** Introduzca solo números en los campos Cantidad y Precio.

### Error: "La cantidad/precio no puede ser negativo"
**Causa:** Se introdujo un valor negativo.
**Solución:** Introduzca un valor mayor o igual a cero.

### Error: "El nombre/categoría no puede estar vacío"
**Causa:** Se dejó un campo de texto vacío.
**Solución:** Introduzca un valor en el campo.

### Error: "No hay productos para guardar"
**Causa:** Se intentó guardar con la tabla vacía.
**Solución:** Añada al menos un producto antes de guardar.

### Error: "Por favor, seleccione un producto para eliminar"
**Causa:** Se intentó eliminar sin seleccionar ninguna fila.
**Solución:** Haga clic en una fila de la tabla antes de eliminar.

---

## 8. Consejos y Buenas Prácticas

1. **Guarde frecuentemente:** Recuerde guardar los cambios para no perder información.

2. **Use nombres descriptivos:** Al guardar, use nombres de archivo que describan el contenido.

3. **Haga copias de seguridad:** Antes de hacer cambios importantes, guarde una copia del archivo original.

4. **Formato recomendado:**
   - Para intercambio con otras aplicaciones: JSON o CSV
   - Para uso interno rápido: DAT
   - Para sistemas empresariales: XML

5. **Edición masiva:** Si necesita editar muchos productos, puede ser más rápido editar directamente el archivo CSV con Excel y luego cargarlo.

---

## 9. Atajos de Teclado

| Atajo | Función |
|-------|---------|
| **Doble clic** en celda | Editar celda |
| **Enter** (en celda) | Confirmar edición |
| **Escape** (en diálogo) | Cancelar diálogo |
| **Enter** (en diálogo) | Confirmar diálogo |

---

## 10. Información Técnica

### Estructura del Proyecto
```
GestionInventario/
├── src/main/java/com/mycompany/
│   ├── controller/          # Conversores de formato
│   ├── models/              # Clases de datos
│   ├── view/                # Interfaz gráfica
│   └── gestioninventario/   # Punto de entrada
├── src/main/java/com/mycompany/Recursos/  # Archivos de ejemplo
├── pom.xml                  # Configuración Maven
└── MANUAL_USUARIO.md        # Este manual
```

### Tecnologías Utilizadas
- **Java SE 17+** - Lenguaje de programación
- **Swing** - Biblioteca de interfaz gráfica
- **Gson** - Procesamiento JSON
- **JAXB** - Procesamiento XML
- **Maven** - Gestión de dependencias

---

## 11. Soporte

Para reportar errores o solicitar nuevas funcionalidades, contacte con el equipo de desarrollo.

**Versión:** 1.0
**Fecha:** Enero 2026
