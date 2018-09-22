package springcloud.locationservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DriverController {

    private final AtomicLong counter = new AtomicLong();
    private static HashMap<String, Driver>  drivers = new HashMap<>();

    @RequestMapping(value="/driver", method= RequestMethod.POST)
    public ResponseEntity<Driver> create(
            @RequestBody(required = false) Driver driver) {

        long id = counter.incrementAndGet();
        driver.setId(id);

        drivers.put(String.valueOf(id), driver);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @RequestMapping(value="/driver", method= RequestMethod.GET)
    public ResponseEntity<List<Driver>> getAll() {
        return new ResponseEntity<>(new ArrayList<>(drivers.values()), HttpStatus.OK);
    }

    @RequestMapping(value="/driver/{id}", method= RequestMethod.GET)
    public ResponseEntity<Driver> get(
            @RequestParam("id") String id) {
        Driver driver = drivers.get(id);
        if (driver == null) {
            return new ResponseEntity<>(driver, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(driver, HttpStatus.OK);
        }
    }


    public static boolean isDriverValid(String id) {
        return drivers.containsKey(id);
    }










}
