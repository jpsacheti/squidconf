package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.serializer.FileSerializer;
import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController("/extension")
public class FileExtensionController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public FileExtensionController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(String ext) {
        ext = ext.startsWith("\\.") ? ext : "." + ext;
        squidFileRepo.addFileExtension(ext);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public Set<String> list() {
        return squidFileRepo.getBlackListExtension();
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(String fex) {
        boolean result = squidFileRepo.removeFileExtension(fex);
        FileSerializer.writeConfiguration(squidFileRepo);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/flush")
    public ResponseEntity<?> flush() {
        squidFileRepo.getBlackListExtension().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

}
