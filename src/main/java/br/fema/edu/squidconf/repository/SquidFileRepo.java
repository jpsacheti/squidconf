package br.fema.edu.squidconf.repository;

import br.fema.edu.squidconf.model.SquidRule;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

@Repository
@ApplicationScope
public class SquidFileRepo {
    private final List<SquidRule> squidRules = new ArrayList<>();
    private final List<String> whiteListUrl = new ArrayList<>();
    private final List<String> blackListUrl = new ArrayList<>();

    public void serialize(){
        //TODO: Implement serialization
    }
    public void deserialize(){
        //TODO: Implement deserialization
    }
}
