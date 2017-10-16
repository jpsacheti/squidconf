package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.model.AuthUser;
import br.fema.edu.squidconf.serializer.FileSerializer;
import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController("/usuario")
public class AuthUserController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public AuthUserController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }

    @PostMapping("/new")
    public ResponseEntity<?> addUser(@RequestBody AuthUser user) {
        squidFileRepo.addAuthUser(user);
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public Map<Integer, String> list() {
        return squidFileRepo.getUsers()
                .stream()
                .collect(Collectors.toMap(AuthUser::getCodigo, AuthUser::getUsername));
    }

    @PostMapping("/remove/{codigo}")
    public ResponseEntity<?> remove(@PathVariable Integer codigo) {
        boolean result = squidFileRepo.removeAuthUser(codigo);
        FileSerializer.writeConfiguration(squidFileRepo);
        return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/flush")
    public ResponseEntity<?> flush() {
        squidFileRepo.getUsers().clear();
        FileSerializer.writeConfiguration(squidFileRepo);
        return ResponseEntity.ok().build();
    }
}
