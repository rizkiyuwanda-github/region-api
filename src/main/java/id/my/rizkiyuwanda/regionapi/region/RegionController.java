package id.my.rizkiyuwanda.regionapi.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping("/save")
    public ResponseEntity<Region> save(@RequestBody Region region) {
        try {
            Region regionResponse = regionService.save(region);
            return new ResponseEntity<>(regionResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        try {
            regionService.deleteById(id);
            return new ResponseEntity<>("Deleted Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Region> findById(@PathVariable("id") Long id) {
        Optional<Region> region = regionService.findById(id);
        if (region.isPresent()) {
            return new ResponseEntity<>(region.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findbyname/{name}")
    public ResponseEntity<Region> findbByName(@PathVariable("name") String name) {
        Optional<Region> region = regionService.findByName(name);
        if (region.isPresent()) {
            return new ResponseEntity<>(region.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Region>> findAll() {
        try {
            List<Region> regions = new ArrayList<>();
            regionService.findAll().forEach(regions::add);

            if (regions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(regions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
