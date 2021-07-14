package com.example.demo.services;

import com.example.demo.*;
import com.example.demo.models.*;
import com.example.demo.repositories.DriverRepository;
import com.example.demo.repositories.OurRequestRepository;
import com.example.demo.repositories.RequestRepository;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.text.html.Option;
import java.util.stream.Collectors;

@Service
public class OurRequestService {

    @Autowired
    OurRequestRepository ourRequestRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    RequestService requestService;
    @Autowired
    DriverService driverService;
    @Autowired
    ProductService productService;
    @Autowired
    UsuarioService userService;

    private String [] names = {"Matias", "Santiago", "Tomas", "Ana", "Martina", "Rodrigo", "Pablo", "Enrique", "Esteban", "Federico", "Sergio", "Raul", "Roberto", "Juan", "Agustin", "Victoria", "Alejandra", "Marcela", "Leticia", "Paula", "Daniel", "Gustavo", "Mariano", "Brain"};
    private List<String> namesList = Arrays.asList(names);
    private String [] lastNames = {"Perez", "Garcia", "Lin", "Rodriguez", "Ramirez", "Martinez", "Ramele", "Flores", "Alaise", "Ragalli", "Regueira", "Falbo", "Reggi", "Pulido", "Luccetti", "Ferreira", "Mendive", "Di Napoli", "Bridger", "Augustoni", "Boskis", "Diesel", "Nahin", "Fara", "Lopez", "Mora", "Roma", "Slaguero"};
    private List<String> lastNamesList = Arrays.asList(lastNames);
    private String [] carBrands = {"Ford", "Renault", "Nissan", "Fiat", "Toyota", "Chevrolet", "VW", "Peugeot", "Citroen", "Honda", "Hyundai"};
    private List<String> carBrandsList = Arrays.asList(carBrands);
    private Integer telephone = 1543512000;
    private  String patente = "AAA";
    private Integer patenteNumero = 0;
    private boolean initialized =false;
    private Map<String,List<String>> cars = new HashMap<>();

    private String getPatente(){
        if(patenteNumero == 999 && patente.equals("AAA")) {
            patente = "AAB";
            patenteNumero = 0;
        }else if(patenteNumero == 999 && patente.equals("AAB")){
            patente = "AAC";
            patenteNumero = 0;
        }else{
            patenteNumero++;
        }
        return patente.concat(String.format("%03d",patenteNumero));
    }



    public List<DriverModel> getAvailablesDrivers(Double latitudeInit,Double longitudeInit,Double latitudeDest,Double longitudeDest,Integer api_id){
    if(!initialized) {
        for (String carb : carBrandsList) {
            cars.putIfAbsent(carb, new ArrayList<>());
            switch (carb) {
                case "Ford": {
                    cars.get("Ford").addAll(Arrays.asList("Mondeo", "Fiesta", "Focus", "Ka", "Ka+", "Ranger"));
                    break;
                }
                case "Renault": {
                    cars.get("Renault").addAll(Arrays.asList("Clio", "Megan", "Stepway", "Duster", "KWID", "Sandero", "Logan", "Kangoo"));
                    break;
                }
                case "Nissan": {
                    cars.get("Nissan").addAll(Arrays.asList("Versa", "Kicks", "Sentra"));
                    break;
                }
                case "Fiat": {
                    cars.get("Fiat").addAll(Arrays.asList("Argo", "Uno", "Palio", "Mobi", "Cronos", "Siena"));
                    break;
                }
                case "Toyota": {
                    cars.get("Toyota").addAll(Arrays.asList("Corolla", "Etios", "Yaris", "Prius", "Innova"));
                    break;
                }
                case "Chevrolet": {
                    cars.get("Chevrolet").addAll(Arrays.asList("Corsa", "Spin", "Cruze", "Cruze 5", "Onix"));
                    break;
                }
                case "VW": {
                    cars.get("VW").addAll(Arrays.asList("Gol", "Golf", "T-Cross", "Vento", "Tiguan", "Polo"));
                    break;
                }
                case "Peugeot": {
                    cars.get("Peugeot").addAll(Arrays.asList("208", "308", "2008"));
                    break;
                }
                case "Citroen": {
                    cars.get("Citroen").addAll(Arrays.asList("C4", "C3", "Berlingo"));
                    break;
                }
                case "Honda": {
                    cars.get("Honda").addAll(Arrays.asList("Civic", "CR-V", "HR-V", "Fit"));
                    break;
                }
                case "Hyundai": {
                    cars.get("Hyundai").addAll(Arrays.asList("Santa Fe", "i10"));
                    break;
                }
            }
        }
        initialized = true;
    }

        List<DriverModel> driverAvailables =  driverService.obtenerConductoresLibres(api_id);
        List<DriverModel> aux = new ArrayList<>();


        Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,latitudeDest,longitudeDest);
        ProductModel productModel;
        for(DriverModel d : driverAvailables){
            productModel = productService.obtenerPorId(d.getProduct_id());
                if(productModel.getMinimum()<dist){
                    if(calculateDistanceInMeters(latitudeInit,longitudeInit,d.getLatitude(),d.getLongitude()) <= 500)
                    {
                        //System.out.println(calculateDistanceInMeters(latitudeInit,longitudeInit,d.getLatitude(),d.getLongitude())+" Dist");
                        aux.add(d);
                    }
                }
        }
        int randomCant = ThreadLocalRandom.current().nextInt(1,5);
        while (aux.size() < randomCant){
            //DriverModel botDriver = driverService.guardarDriver(new DriverModel());
                DriverModel botDriver = new DriverModel();
                ProductModel botDriverProduct = productService.guardarProducto(new ProductModel());
                botDriver.setProduct_id(botDriverProduct.getId());
                botDriver.setPatente_auto("ABC");

                botDriver.setNombre("Bot Driver "+botDriver.getId());
                botDriver.setMarca_auto("BOTTOR");
               botDriver = driverService.guardarDriver(botDriver,api_id);
                Random r = new Random();
                botDriver.setMarca_auto(carBrandsList.get(ThreadLocalRandom.current().nextInt(0,carBrandsList.size()-1)));
                botDriver.setModelo_auto(cars.get(botDriver.getMarca_auto()).get(ThreadLocalRandom.current().nextInt(0,cars.get(botDriver.getMarca_auto()).size()-1))); //
//                botDriver.setLatitude(latitudeInit + (0.001 + (0.009 - 0.001) * r.nextDouble()));
//                botDriver.setLongitude(longitudeInit + (0.001 + (0.009 - 0.001) * r.nextDouble()));
                botDriver.setLatitude(latitudeInit + ThreadLocalRandom.current().nextDouble(0.001, 0.015));
                botDriver.setLongitude(longitudeInit + ThreadLocalRandom.current().nextDouble(0.001, 0.015));
                while(calculateDistanceInMeters(latitudeInit,longitudeInit,botDriver.getLatitude(),botDriver.getLongitude()) > 500){
                    botDriver.setLatitude(latitudeInit + ThreadLocalRandom.current().nextDouble(0.001, 0.015));
                    botDriver.setLongitude(longitudeInit + ThreadLocalRandom.current().nextDouble(0.001, 0.015));
                }
                botDriver.setPatente_auto(getPatente());
                botDriver.setTelefono(telephone++);
                botDriver.setRate(ThreadLocalRandom.current().nextInt(0,5));

                botDriverProduct.setCapacity(ThreadLocalRandom.current().nextInt(2,4));
                botDriverProduct.setCancellation_fee(ThreadLocalRandom.current().nextInt(20, 80 + 1));
                botDriverProduct.setCost_per_minute(ThreadLocalRandom.current().nextInt(2, 12 + 1));
                botDriverProduct.setCost_per_distance(ThreadLocalRandom.current().nextInt(1, 10));
                botDriverProduct.setMinimum(ThreadLocalRandom.current().nextInt(300, 1000 + 1));
                botDriverProduct.setCurrency_code("ARS");
                botDriverProduct.setCash_enabled(true);
                botDriverProduct.setShared(false);
                botDriver.setNombre(namesList.get(ThreadLocalRandom.current().nextInt(0,namesList.size()-1)));
                botDriver.setApellido(lastNamesList.get(ThreadLocalRandom.current().nextInt(0,carBrandsList.size()-1)));

                botDriverProduct.setShort_description("Bot Product "+botDriver.getId());
                botDriverProduct.setDisplay_name("Bot Product "+botDriver.getId());
                botDriverProduct.setDescription("Bot Product "+botDriver.getId());
                botDriverProduct.setService_fee(ThreadLocalRandom.current().nextInt(10, 50 + 1));
                productService.guardarProducto(botDriverProduct);
                driverService.guardarDriver(botDriver,api_id);

                aux.add(botDriver);

        }
        return aux;
    }

    public double getPriceEstimate(Long driverID, Double latitudeInit,Double longitudeInit,Double latitudeDest,Double longitudeDest,Integer api_id){
        double totalTripCost = 0.0;
        DriverModel driver = driverService.obtenerDriverPorId(driverID,api_id);

            ProductModel product = productService.obtenerPorId(driver.getProduct_id());

                Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,latitudeDest,longitudeDest)/100;
                totalTripCost += product.getService_fee();
                //TODO: ver si agregamos el costPerMinute
                totalTripCost += dist*product.getCost_per_distance();
                //System.out.println("Distancia: " + dist +  " Precio: " + totalTripCost);


        return totalTripCost;
    }

    public long getTimeOfPickUpEstimate(Long driverID, Double latitudeInit,Double longitudeInit,Integer api_id){
        long timeEstimate = 0;
        DriverModel driver = driverService.obtenerDriverPorId(driverID,api_id);

            Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,driver.getLatitude(),driver.getLongitude());
            timeEstimate += (dist/100)*60000;
        return timeEstimate;
    }

    public long getTimeOfArrivalEstimate(Long driverID, Double latitudeInit,Double longitudeInit,Double latitudeDest,Double longitudeDest,Integer api_id){
        long timeEstimate = 0;
        timeEstimate += getTimeOfPickUpEstimate(driverID, latitudeInit, longitudeInit,api_id);
        Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,latitudeDest,longitudeDest);
        timeEstimate += (dist/100)*60000;
        return timeEstimate;
    }

    public static double calculateDistanceInMeters(double lat1, double long1, double lat2,
                                            double long2) {


        double dist = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, lat2, long2);
        return dist;
    }


    public RequestModel setNewDrive(Long userId, Long driverId,Double latitudeInit, Double longitudeInit, Double latitudeDest, Double longitudeDest,Integer api_id){
        UsuarioModel user = userService.obtenerPorId(userId,api_id);
        DriverModel driverModel = driverService.obtenerDriverPorId(driverId, user.getApi_id());
        RequestModel requestModel =  requestService.guardarRequest(new RequestModel());
        requestModel.setStatus(RequestStatus.CONFIRMING_DRIVER.toString());
            if (driverModel.isAvailable()) {
                    user.setCurrentTripId(requestModel.getId());
                    driverModel.setCurrentTripId(requestModel.getId());
                    requestModel.setProduct_id(driverModel.getProduct_id());
                    requestModel.setRequestTime(System.currentTimeMillis());
                    requestModel.setDriver_id(driverModel.getId());
                    requestModel.setStatus(RequestStatus.WAITING_FOR_PICK_UP.toString());
                    requestModel.setDes_longitude(longitudeDest);
                    requestModel.setDest_latitude(latitudeDest);
                    requestModel.setInit_pos_lat(latitudeInit);
                    requestModel.setInit_pos_long(longitudeInit);
                    driverModel.setAvailable(false);
                    driverRepository.save(driverModel);
                    requestService.guardarRequest(requestModel);
            } else {
                throw new UnavailableDriverException("Conductor con ID: " + driverId + " no se encuentra disponible");
            }
        return requestModel;
    }

    public void cancelRide(Long userId,Long id,Integer api_id){
        RequestModel  requestModel = requestService.obtenerRequestPorId(id);

        if(requestModel.getStatus().equals(RequestStatus.WAITING_FOR_PICK_UP.toString())){
            DriverModel driverModel = driverService.obtenerDriverPorId(requestModel.getDriver_id(),api_id);
            ProductModel productModel = productService.obtenerPorId(driverModel.getProduct_id());
            UsuarioModel usuarioModel = userService.obtenerPorId(userId,api_id);
            if(usuarioModel.getCurrentTripId() != id){
                throw new UnauthorizedMethodException("No puede cancelar el viaje que no es suyo");
            }
            double currentSaldo = usuarioModel.getCurrentSaldo();
            usuarioModel.setCurrentSaldo(currentSaldo+productModel.getCancellation_fee());
            usuarioModel.setCurrentTripId(null);
            driverModel.setCurrentTripId(null);
            driverModel.setAvailable(true);

            userService.guardarUsuario(usuarioModel,api_id);
            driverService.guardarDriver(driverModel,api_id);
            requestService.borrarPorId(id);
        }else{
            throw new UnauthorizedMethodException("No puede cancelar el viaje a menos que este espando para ser recogido");
        }

    }

}
