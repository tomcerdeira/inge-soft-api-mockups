package com.example.demo.services;

import com.example.demo.InvalidIdException;
import com.example.demo.RequestStatus;
import com.example.demo.UnavailableDriverException;
import com.example.demo.models.*;
import com.example.demo.repositories.DriverRepository;
import com.example.demo.repositories.OurRequestRepository;
import com.example.demo.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    //private int botIndice = 0;

    public List<DriverModel> getAvailablesDrivers(Double latitudeInit,Double longitudeInit,Double latitudeDest,Double longitudeDest){

        List<DriverModel> driverAvailables =  driverService.obtenerConductoresLibres();
        List<DriverModel> aux = new ArrayList<>();
        Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,latitudeDest,longitudeDest);
        ProductModel productModel;
        for(DriverModel d:driverAvailables){
            productModel = productService.obtenerPorId(d.getProduct_id());
                if(productModel.getMinimum()<dist){
                    if(calculateDistanceInMeters(latitudeInit,longitudeInit,d.getLatitude(),d.getLongitude()) <500)
                    {
                        //System.out.println(calculateDistanceInMeters(latitudeInit,longitudeInit,d.getLatitude(),d.getLongitude())+" Dist");
                        aux.add(d);
                    }
                    }
        }
        if (aux.size() == 0){
            ArrayList<DriverModel> auxBotDrivers = new ArrayList<>();
            for (int i=0; i<5; i++){
                System.out.println("LLEGOOOO!!");
                //DriverModel botDriver = driverService.guardarDriver(new DriverModel());
                DriverModel botDriver = new DriverModel();
                ProductModel botDriverProduct = productService.guardarProducto(new ProductModel());
                botDriver.setProduct_id(botDriverProduct.getId());
                botDriver.setPatente_auto("ABC");
                botDriver.setNombre("Bot Driver "+botDriver.getId());
                botDriver.setMarca_auto("BOTTOR");
                driverService.guardarDriver(botDriver);
                Random r = new Random();
                //rangeMin + (rangeMax - rangeMin) * r.nextDouble();
                botDriver.setLatitude(latitudeInit + (0.001 + (0.009 - 0.001) * r.nextDouble()));
                botDriver.setLongitude(longitudeInit + (0.001 + (0.009 - 0.001) * r.nextDouble()));
                botDriver.setPatente_auto("BOT" + botDriver.getId());
                //TODO: ver si seteamos toda la info del conductor
                botDriverProduct.setCapacity(4);
                //ThreadLocalRandom.current().nextInt(min, max + 1);
                botDriverProduct.setCancellation_fee(ThreadLocalRandom.current().nextInt(20, 80 + 1));
                botDriverProduct.setCost_per_minute(ThreadLocalRandom.current().nextInt(2, 12 + 1));
                botDriverProduct.setCost_per_distance(ThreadLocalRandom.current().nextInt(20, 60 + 1));
                botDriverProduct.setMinimum(ThreadLocalRandom.current().nextInt(300, 1000 + 1));
                botDriverProduct.setCurrency_code("ARS");
                botDriverProduct.setCash_enabled(true);
                botDriverProduct.setShared(false);
                botDriverProduct.setShort_description("Bot Driver "+botDriver.getId());
                botDriverProduct.setDisplay_name("Bot Driver "+botDriver.getId());
                botDriverProduct.setDescription("Bot Driver "+botDriver.getId());

                System.out.println("Patente bot "+ botDriver.getId() + ":" + botDriver.getPatente_auto());
                productService.guardarProducto(botDriverProduct);
                driverService.guardarDriver(botDriver);
                if(calculateDistanceInMeters(latitudeInit,longitudeInit,botDriver.getLatitude(),botDriver.getLongitude()) <500)
                {
                    //System.out.println(calculateDistanceInMeters(latitudeInit,longitudeInit,d.getLatitude(),d.getLongitude())+" Dist");
                    aux.add(botDriver);
                }

            }
        }
        return aux;
    }

    public double getPriceEstimate(Long driverID, Double latitudeInit,Double longitudeInit,Double latitudeDest,Double longitudeDest){
        double totalTripCost = 0.0;
        DriverModel driver = driverService.obtenerDriverPorId(driverID);

            ProductModel product = productService.obtenerPorId(driver.getProduct_id());

                Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,latitudeDest,longitudeDest);
                totalTripCost += product.getService_fee();
                //TODO: ver si agregamos el costPerMinute
                totalTripCost += dist*product.getCost_per_distance();
                //System.out.println("Distancia: " + dist +  " Precio: " + totalTripCost);


        return totalTripCost;
    }

    public long getTimeOfPickUpEstimate(Long driverID, Double latitudeInit,Double longitudeInit){
        long timeEstimate = 0;
        DriverModel driver = driverService.obtenerDriverPorId(driverID);

            Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,driver.getLatitude(),driver.getLongitude());
            timeEstimate += (dist/100)*60000;
        return timeEstimate;
    }

    public long getTimeOfArrivalEstimate(Long driverID, Double latitudeInit,Double longitudeInit,Double latitudeDest,Double longitudeDest){
        long timeEstimate = 0;
        timeEstimate += getTimeOfPickUpEstimate(driverID, latitudeInit, longitudeInit);
        Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,latitudeDest,longitudeDest);
        timeEstimate += (dist/100)*60000;
        return timeEstimate;
    }

    public double calculateDistanceInMeters(double lat1, double long1, double lat2,
                                            double long2) {


        double dist = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, lat2, long2);
        return dist;
    }


    public RequestModel setNewDrive(Long userId, Long driverId,Double latitudeInit, Double longitudeInit, Double latitudeDest, Double longitudeDest){
        UsuarioModel user = userService.obtenerPorId(userId);
        DriverModel driverModel = driverService.obtenerDriverPorId(driverId);
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

}
