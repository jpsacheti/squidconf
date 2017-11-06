package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.serializer.FileSerializer;
import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class IpController {
    private final SquidFileRepo squidFileRepo;


    @Autowired
    public IpController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }

    @PostMapping("/ip/blacklist/add")
    public ResponseEntity<?> addBlackList(@RequestBody String ip) {
        squidFileRepo.addBlacklistIp(ip);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ip/blacklist/list")
    public Set<String> listBlackList() {
        return squidFileRepo.getBlackListIp();
    }

    @PostMapping("/ip/blacklist/remove")
    public ResponseEntity<?> removeBlackList(@RequestBody String ip) {
        boolean result = squidFileRepo.removeBlacklistIp(ip);
        FileSerializer.writeConfiguration(squidFileRepo);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/ip/blacklist/flush")
    public ResponseEntity<String> flushBlackList() {
        squidFileRepo.getBlackListIp().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ip/whitelist/add")
    public ResponseEntity<?> addWhiteList(@RequestBody String ip) {
        squidFileRepo.addWhitelistIp(ip);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ip/whitelist/list")
    public Set<String> listWhiteList() {
        return squidFileRepo.getWhiteListIp();
    }

    @PostMapping("/ip/whitelist/remove")
    public ResponseEntity<?> removeWhiteList(@RequestBody String ip) {
        boolean result = squidFileRepo.removeWhitelistIp(ip);
        FileSerializer.writeConfiguration(squidFileRepo);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/ip/whitelist/flush")
    public ResponseEntity<?> flushWhiteList() {
        squidFileRepo.getWhiteListIp().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }
}
