package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.model.TimeRule;
import br.fema.edu.squidconf.serializer.FileSerializer;
import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class TimeController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public TimeController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }

    @PostMapping("/time/add")
    public ResponseEntity<?> add(@RequestBody TimeRule ext) {
        squidFileRepo.addTimeRule(ext);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/time/list")
    public Set<String> list() {
        return squidFileRepo.getBlackListExtension();
    }

    @GetMapping("/time/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        boolean result = squidFileRepo.removeTimeRule(id);
        FileSerializer.writeConfiguration(squidFileRepo);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/time/flush")
    public ResponseEntity<?> flush() {
        squidFileRepo.getTimeRules().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }
}
