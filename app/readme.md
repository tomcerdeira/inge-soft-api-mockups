## Spring Boot Api Mockup

[comment]: <> (Tutorial para crear una API de Spring boot.)

[comment]: <> (Ver los vídeos para entender la construcción:)

[comment]: <> (Fundamentos)

[comment]: <> (https://www.youtube.com/watch?v=WVHnk04skPc&t)

[comment]: <> (Rest  API )

[comment]: <> (https://www.youtube.com/watch?v=vTu2HQrXtyw)

[comment]: <> (* Se actualizo el tipo de empaquetado a .jar)

[comment]: <> (Rest Full Api para crear simular el comportamiento de un servicio VTC.)

[comment]: <> (Para leer la documenatcion de las funciones y sus modelos:)

[comment]: <> (http://localhost:8080/swagger-ui.html#/)

## Pasos para ejecutar la API

### 1. Ejecutar desde su IDE  Maven install

### 2. Asegurese de tener una instancia de Postresql corriendo


### 3. Agregar la configuración de la base en applicacitons.properties
app/src/resources.application.properties 

Actualiza las siguientes propiedades 

* url y puerto donde se corre su base de datos
* username y password para ingresar a la base de datos


#### 4. Ejecutalo
usar mvn para ejecutar

Windows example:

    mvnw.cmd spring-boot:run

Unix based:

    mvwn spring-boot:run 

### Listo ya tenes la API ejecutandose en el puerto 8080!


## Aprende a usar la API
Una vez que tenga la API corriendo, la documentación se encuentra en: http://localhost:8080/swagger-ui.html#/

En las siguientes secciones se explicaran el funcionamiento de algunos metodos para facilitar su uso. No se detallara sobre metodos/parametros considerado triviales que se pueden deducir desde la documentacion provista.

### Como crear un driver?
 El mecanismo de crear es intuitivo.Solamente debe tener en cuenta que antes de crear un driver, debe tener creado el producto que este mismo va a ofrecer, para asi poder cargarle el ID en el campo correspondiente.
### Como pedir un viaje?
1. Obtener los conductores disponibles para tomar su viaje. Debe utilizar el método GET /our_requests/avaliableDrivers.
   Ahora tenes todos los drivers que están disponible para tomar tu viaje.
2. Hace el request a la API usando POST /our_requests/ride con el driverId que elijas de la lista obtenida en (1) y el userId correspondiente a tu user.
3. **Debes** hacer GET /requests/{id}, donde id es el del request que hiciste en (2), para ir verificando el estado del viaje. El conductor no te avisará cuando esté llegando.

### Como estimar el costo de un viaje?
1. Obtener los conductores disponibles para tomar su viaje. Debe utilizar el método GET /our_requests/avaliableDrivers.
      Ahora tenes todos los drivers que están disponible para tomar tu viaje.
      
1. Con el driverId que desea consultar, hacer GET /our_requests/estimatePrice. Este metodo le retornara el valor consultado.

### Como estimar el tiempo de un viaje?
1. Obtener los conductores disponibles para tomar su viaje. Debe utilizar el método GET /our_requests/avaliableDrivers.
   Ahora tenes todos los drivers que están disponible para tomar tu viaje.

1. Con el driverId que desea consultar, hacer GET /our_requests/estimateArrival. Este metodo le retornara el valor consultado.

### Como estimar el tiempo que tardara el conductor en recogerlo?
1. Obtener los conductores disponibles para tomar su viaje. Debe utilizar el método GET /our_requests/avaliableDrivers.
   Ahora tenes todos los drivers que están disponible para tomar tu viaje.

1. Con el driverId que desea consultar, hacer GET /our_requests/estimatePickUp. Este metodo le retornara el valor consultado.












