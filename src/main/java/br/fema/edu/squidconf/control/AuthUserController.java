package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.model.AuthUser;
import br.fema.edu.squidconf.serializer.FileSerializer;
import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthUserController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public AuthUserController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> addUser(@RequestBody AuthUser user) {
        squidFileRepo.addAuthUser(user);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/list")
    public List<AuthUser> list() {
        return squidFileRepo.getUsers();
    }

    @PostMapping("/user/remove/{codigo}")
    public ResponseEntity<?> remove(@PathVariable Integer codigo) {
        boolean result = squidFileRepo.removeAuthUser(codigo);
        FileSerializer.writeConfiguration(squidFileRepo);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/flush")
    public ResponseEntity<?> flush() {
        squidFileRepo.getUsers().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }
}
