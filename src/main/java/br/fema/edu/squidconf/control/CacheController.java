package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.model.CacheSize;
import br.fema.edu.squidconf.serializer.FileSerializer;
import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("/cache")
public class CacheController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public CacheController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }

    @GetMapping("/status")
    public ResponseEntity<CacheSize> getStatus() {
        final Optional<CacheSize> cacheSize = squidFileRepo.getCacheSize();
        return cacheSize.map(cs -> new ResponseEntity<>(cs, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping(value = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CacheSize> criar(@RequestBody CacheSize cacheSize) {
        squidFileRepo.setCacheSize(cacheSize);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/reset")
    public ResponseEntity<CacheSize> reset() {
        squidFileRepo.setCacheSize(null);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }
}
