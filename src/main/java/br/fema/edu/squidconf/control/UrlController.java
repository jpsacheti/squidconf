package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.serializer.FileSerializer;
import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController("/url")
public class UrlController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public UrlController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }

    @PostMapping("/blacklist/add")
    public ResponseEntity<?> addBlackList(@RequestBody String regexUrl) {
        squidFileRepo.addBlackList(regexUrl);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/blacklist/list")
    public Set<String> listBlackList() {
        return squidFileRepo.getBlackListUrl();
    }

    @PostMapping("/blacklist/remove")
    public ResponseEntity<?> removeBlackList(@RequestBody String regexUrl) {
        boolean result = squidFileRepo.removeBlackList(regexUrl);
        FileSerializer.writeConfiguration(squidFileRepo);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/blacklist/flush")
    public ResponseEntity<?> flushBlackList() {
        squidFileRepo.getBlackListUrl().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/whitelist/add")
    public ResponseEntity<?> addWhiteList(String regexUrl) {
        squidFileRepo.addWhiteList(regexUrl);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/whitelist/list")
    public Set<String> listWhiteList() {
        return squidFileRepo.getWhiteListUrl();
    }

    @PostMapping("/whitelist/remove")
    public ResponseEntity<?> removeWhiteList(@RequestBody String regexUrl) {
        boolean result = squidFileRepo.removeWhiteList(regexUrl);
        FileSerializer.writeConfiguration(squidFileRepo);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/whitelist/flush")
    public ResponseEntity<?> flushWhiteList() {
        squidFileRepo.getWhiteListUrl().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }
}
