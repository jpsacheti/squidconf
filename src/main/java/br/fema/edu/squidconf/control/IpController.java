package br.fema.edu.squidconf.control;

import br.fema.edu.squidconf.serializer.SquidFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController("/ip")
public class IpController {
    private final SquidFileRepo squidFileRepo;

    @Autowired
    public IpController(@Qualifier("squidFileRepo") SquidFileRepo squidFileRepo) {
        this.squidFileRepo = squidFileRepo;
    }
}
