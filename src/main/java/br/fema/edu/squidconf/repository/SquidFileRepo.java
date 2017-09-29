package br.fema.edu.squidconf.repository;

import br.fema.edu.squidconf.model.SquidRule;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@ApplicationScope
@Repository
public class SquidFileRepo implements Serializable {
    private final List<SquidRule> squidRules = new ArrayList<>();
    private final Set<String> whiteListUrl = new LinkedHashSet<>();
    private final Set<String> blackListUrl = new LinkedHashSet<>();
    private final Set<String> blackListExtension = new LinkedHashSet<>();

    public synchronized void addRule(SquidRule squidRule) {
        if (!squidRules.contains(squidRule)) {
            squidRules.add(squidRule);
        }
    }

    public synchronized boolean removeRule(SquidRule squidRule) {
        return squidRules.remove(squidRule);
    }

    public synchronized void addWhiteList(String url) {
        whiteListUrl.add(url);
    }

    public synchronized boolean removeWhiteList(String url) {
        return whiteListUrl.remove(url);
    }

    public synchronized void addBlackList(String url) {
        blackListUrl.add(url);
    }

    public synchronized boolean removeBlackList(String url) {
        return blackListUrl.remove(url);
    }

    public synchronized void addFileExtension(String fex) {
        blackListExtension.add(fex);
    }

    public synchronized boolean removeFileExtension(String fex) {
        return blackListExtension.remove(fex);
    }
}
