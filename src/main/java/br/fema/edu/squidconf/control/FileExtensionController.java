package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/extension")
public class FileExtensionController {
    private final SquidFileRepo repo;

    @Autowired
    public FileExtensionController(SquidFileRepo repo) {
        this.repo = repo;
    }
}
