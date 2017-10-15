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
import java.util.regex.Pattern;

@RestController("/ip")
public class IpController {
    private final SquidFileRepo squidFileRepo;
    private static final Pattern IP_PATTERN = Pattern.compile(
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");


    @Autowired
    public IpController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }

    @PostMapping("/blacklist/add")
    public ResponseEntity<?> addBlackList(String ip) {
        if (IP_PATTERN.matcher(ip).matches()) {
            squidFileRepo.addBlacklistIp(ip);
            FileSerializer.writeConfiguration(squidFileRepo);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/blacklist/list")
    public Set<String> listBlackList() {
        return squidFileRepo.getBlackListIp();
    }

    @PostMapping("/blacklist/remove")
    public ResponseEntity<?> removeBlackList(String ip) {
        if (IP_PATTERN.matcher(ip).matches()) {
            boolean result = squidFileRepo.removeBlacklistIp(ip);
            FileSerializer.writeConfiguration(squidFileRepo);
            return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/blacklist/flush")
    public ResponseEntity<?> flushBlackList() {
        squidFileRepo.getBlackListIp().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/whitelist/add")
    public ResponseEntity<?> addWhiteList(String ip) {
        if (IP_PATTERN.matcher(ip).matches()) {
            squidFileRepo.addWhitelistIp(ip);
            FileSerializer.writeConfiguration(squidFileRepo);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/whitelist/list")
    public Set<String> listWhiteList() {
        return squidFileRepo.getWhiteListIp();
    }

    @PostMapping("/whitelist/remove")
    public ResponseEntity<?> removeWhiteList(String ip) {
        if (IP_PATTERN.matcher(ip).matches()) {
            boolean result = squidFileRepo.removeWhitelistIp(ip);
            FileSerializer.writeConfiguration(squidFileRepo);
            return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/whitelist/flush")
    public ResponseEntity<?> flushWhiteList() {
        squidFileRepo.getWhiteListIp().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }
}
