# Proyecto VIGUD
Este proyecto fue desarrollado durante una practica profesional en la Universidad Andrés Bello. 

## Funciones
Gran parte del back-end de la aplicación no se encuentra programado, los funciones principales que se requerían para este sistema son:
- Alertas de Proximidad (utilizando ProximityAlerts). 
- Navegación entre Activitys a modo DEMO. 
- Notificaciones Georreferenciadas. 

## Funcionamiento

La aplicación puede ser instalada en cualquier smartphone Android con sdk 26, 21 cómo minimo, las pruebas fueron realizadas en uno de alta gama Samsung Galaxy S8.
Se puede descargar el código para depurarlo en el smartphone o bien generar un apk para instalar, solo es navegación entre ventanas, algunos botones presentan funcionalidades requeridas para los requisitos solicitados.
La aplicación cuenta con animaciones de salida y entrada de activitys. 
Menus de manera carrousel, slides de bienvenida. 

## Dev

### Añadir puntos de proximidad.
Las alertas de proximidad funcionan a través de posiciones georreferenciadas con coordenadas latitud y longitud, ademas de un radio en metros en de formato float. Estas puedes ser agregadas en la clase ProximityAlerts que es un servicio en las lineas 41 y 42 en la función OnStartCommand.

```java
 public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Notificaciones Activadas", Toast.LENGTH_LONG).show();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
        registerReceiver(new ProximityIntentReceiver(), filter);

        setProximityAlert(-33.020758,-71.547234,25f,300000,1,1);
        setProximityAlert(-33.020787, -71.546043,60f,300000,2,2 );

        return START_NOT_STICKY; //indica que el servicio no debe recrearse al ser destruido sin importar que haya quedado un trabajo pendiente.
    }
```

La función que agrega alertas proximadas es: 
```java
private void setProximityAlert(double lat, double lon, float radius, long expiration, final long eventID, int requestCode)
    {

        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Intent intent = new Intent(PROX_ALERT_INTENT);
        intent.putExtra(ProximityIntentReceiver.EVENT_ID_INTENT_EXTRA, eventID);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        locManager.addProximityAlert(lat, lon, radius, expiration, pendingIntent);
    }
```
Donde
```java
  double lat; // es la latitud del punto.
  double lon; // es la longitud del punto.
  float radius; // es el radio del punto centro en formato metro. 
  long expiration; // la expiración de la alerta.
  final long eventID; // variable para identificar el evento. 
  int requestCode; // codigo de respuesta cuando se ejecuta la alerta.
  ```
  URL de referencia utilizada para realizar esta funcion: https://www.javacodegeeks.com/2011/01/android-proximity-alerts-tutorial.html
  
  
