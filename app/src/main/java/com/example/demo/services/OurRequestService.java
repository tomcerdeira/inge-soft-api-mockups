package com.example.demo.services;

import com.example.demo.models.DriverModel;
import com.example.demo.models.OurRequestModel;
import com.example.demo.models.ProductModel;
import com.example.demo.models.RequestModel;
import com.example.demo.repositories.DriverRepository;
import com.example.demo.repositories.OurRequestRepository;
import com.example.demo.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<DriverModel> getAvailablesDrivers(Double latitudeInit,Double longitudeInit,Double latitudeDest,Double longitudeDest){

        List<DriverModel> driverAvailables =  driverService.obtenerConductoresLibres();
        List<DriverModel> aux = new ArrayList<>();
        Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,latitudeDest,longitudeDest);
        Optional<ProductModel> productModel;
        for(DriverModel d:driverAvailables){
            productModel = productService.obtenerPorId(d.getProduct_id());
                if(productModel.isPresent()){
                    if(productModel.get().getMinimum()<dist){
                        if(calculateDistanceInMeters(latitudeInit,longitudeInit,d.getLatitude(),d.getLongitude()) <500)
                        {
                            System.out.println(calculateDistanceInMeters(latitudeInit,longitudeInit,d.getLatitude(),d.getLongitude())+" Dist");
                            aux.add(d);
                        }
                    }
                }

        }
        return aux;
    }

    public double getPriceEstimate(Long driverID, Double latitudeInit,Double longitudeInit,Double latitudeDest,Double longitudeDest){
        double totalTripCost = 0.0;
        Optional<DriverModel> driver = driverService.obtenerDriverPorId(driverID);
        if (driver.isPresent()){
            Optional<ProductModel> product = productService.obtenerPorId(driver.get().getProduct_id());
            if (product.isPresent()){
                Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,latitudeDest,longitudeDest);
                totalTripCost += product.get().getService_fee();
                //TODO: ver si agregamos el costPerMinute
                totalTripCost += dist*product.get().getCost_per_distance();
                System.out.println("Distancia: " + dist +  " Precio: " + totalTripCost);
            }
        }
        return totalTripCost;
    }

    public long getTimeOfPickUpEstimate(Long driverID, Double latitudeInit,Double longitudeInit){
        long timeEstimate = 0;
        Optional<DriverModel> driver = driverService.obtenerDriverPorId(driverID);
        if (driver.isPresent()){
            Double dist = calculateDistanceInMeters(latitudeInit,longitudeInit,driver.get().getLatitude(),driver.get().getLongitude());
            timeEstimate += (dist/100)*60000;
        }
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


    public RequestModel setNewDrive(Long driverId,Double latitudeInit, Double longitudeInit, Double latitudeDest, Double longitudeDest){
        Optional<DriverModel> driver = driverService.obtenerDriverPorId(driverId);
        DriverModel driverModel;
        RequestModel requestModel =  requestService.guardarRequest(new RequestModel());
        if(driver.isPresent()){
            driverModel = driver.get();
            requestModel.setCar_model(driverModel.getModelo_auto());
            if(driverModel.isAvailable()){
                requestModel.setCar_model(driverModel.getModelo_auto());
                requestModel.setCar_brand(driverModel.getMarca_auto());
                requestModel.setCar_plate(driverModel.getPatente_auto());
                requestModel.setCar_image(driverModel.getImagen_auto());
                requestModel.setProduct_id(driverModel.getProduct_id());
                requestModel.setStatus("coming"); // todo hace enum
                requestModel.setShared(false); // todo ver q hacer
                requestModel.setDriver_telephone(driverModel.getTelefono());
                requestModel.setDriver_picture(driverModel.getImagen_conductor());
                requestModel.setDriver_name(driverModel.getNombre());
                requestModel.setDriver_rate(driverModel.getRate());
                requestModel.setDriver_longitude(driverModel.getLongitude());
                requestModel.setDriver_latitude(driverModel.getLatitude());

                requestModel.setDes_longitude(longitudeDest);
                requestModel.setDest_latitude(latitudeDest);
                driver.get().setAvailable(false);
                driverRepository.save(driverModel);

               requestService.guardarRequest(requestModel);
            }
//            else{
//                //todo ver que hacer en caso de que no haya
//            }
        }

        return requestModel;
    }


}
