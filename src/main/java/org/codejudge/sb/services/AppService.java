package org.codejudge.sb.services;

import org.codejudge.sb.model.*;
import org.codejudge.sb.repositories.DriverRepository;
import org.codejudge.sb.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author TAYYAB
 */
@Service
public class AppService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LocationRepository locationRepository;

    public Driver registerDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Response saveLocation(Location location, long id) throws Exception {
        if(location.getLatitude() <= 0 || location.getLongitude() <= 0) {
            throw new Exception("error");
        }
        location.setDriverId(id);
        locationRepository.save(location);
        return new Response("success", null,null);
    }

    public CabResponse findCabs(Location location) throws Exception {
        if(location.getLatitude() <= 0 || location.getLongitude() <= 0) {
            throw new Exception("error");
        }
        List<Location> all = locationRepository.findAll();
        CabResponse cabResponse = new CabResponse();
        cabResponse.setCabs(new ArrayList<>());
        all.forEach(loc -> {
            double distance = haversine(location.getLatitude(), location.getLongitude(), loc.getLatitude(), loc.getLongitude());
            if(distance <= 4.0) {
                CabDetails cabDetails = new CabDetails();
                Driver driver = driverRepository.findOne(loc.getDriverId());
                cabDetails.setCarNumber(driver.getCarNumber());
                cabDetails.setName(driver.getName());
                cabDetails.setPhone(driver.getPhone());
                cabResponse.getCabs().add(cabDetails);
            }
        });
        return new CabResponse();
    }

    static double haversine(double lat1, double lon1,
                            double lat2, double lon2)
    {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}
