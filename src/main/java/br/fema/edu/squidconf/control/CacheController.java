package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.repository.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/cache")
public class CacheController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public CacheController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }
}
