package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.serializer.FileSerializer;
import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController("/extensoes")
public class FileExtensionController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public FileExtensionController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }

    @PostMapping("/addext")
    public ResponseEntity<?> addExtensao(@RequestBody String ext) {
        ext = ext.startsWith("\\.") ? ext : "." + ext;
        squidFileRepo.addFileExtension(ext);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listext")
    public Set<String> listExtensao() {
        return squidFileRepo.getBlackListExtension();
    }

    @PostMapping("/removeext")
    public ResponseEntity<?> removeExtensao(@RequestBody String fex) {
        boolean result = squidFileRepo.removeFileExtension(fex);
        FileSerializer.writeConfiguration(squidFileRepo);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/flushext")
    public ResponseEntity<?> flushExtensao() {
        squidFileRepo.getBlackListExtension().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

}
