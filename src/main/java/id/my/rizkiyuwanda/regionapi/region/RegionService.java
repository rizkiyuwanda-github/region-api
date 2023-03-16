package id.my.rizkiyuwanda.regionapi.region;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public Region save(Region region) {return regionRepository.save(region);}

    public void deleteById(String id) {regionRepository.deleteById(id);}

    public Optional<Region> findById(String id) {
        return regionRepository.findById(id);
    }

    public Iterable<Region> findAll() {
        return regionRepository.findAll();
    }
}
