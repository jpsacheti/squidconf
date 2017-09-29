package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.repository.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileExtensionController {
    private final SquidFileRepo repo;

    @Autowired
    public FileExtensionController(SquidFileRepo repo) {
        this.repo = repo;
    }
}
