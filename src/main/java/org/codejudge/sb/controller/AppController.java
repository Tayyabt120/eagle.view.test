package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.codejudge.sb.model.CabResponse;
import org.codejudge.sb.model.Driver;
import org.codejudge.sb.model.Location;
import org.codejudge.sb.model.Response;
import org.codejudge.sb.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping("/")
    @ApiOperation("This is the hello world api")
    public String hello() {
        return "Hello World!!";
    }

    @PostMapping("/api/v1/driver/register")
    public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver) {
        return new ResponseEntity<>(appService.registerDriver(driver), HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/driver/{id}/sendLocation")
    public ResponseEntity<Response> registerDriver(@PathVariable long id, @RequestBody Location location) throws Exception {
        return new ResponseEntity<Response>(appService.saveLocation(location, id), HttpStatus.valueOf(202));
    }

    @PostMapping("/api/v1/passenger/available_cabs")
    public ResponseEntity<Object> cabs(@RequestBody Location location) throws Exception {
        CabResponse cabs = appService.findCabs(location);
        if (cabs.getCabs().size() > 0) {
            return new ResponseEntity<>(cabs, HttpStatus.valueOf(200));
        } else {
            Response response = new Response();
            response.setMessage("No cabs available!");
            return new ResponseEntity<>(response, HttpStatus.valueOf(200));
        }
    }
}
