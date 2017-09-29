package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.repository.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/url")
public class UrlController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public UrlController(SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }
}
