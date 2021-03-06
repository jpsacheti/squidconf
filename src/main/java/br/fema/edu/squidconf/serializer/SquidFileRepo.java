package br.fema.edu.squidconf.serializer;

import br.fema.edu.squidconf.model.AuthUser;
import br.fema.edu.squidconf.model.CacheSize;
import br.fema.edu.squidconf.model.TimeRule;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@ApplicationScope
@Repository
public class SquidFileRepo implements Serializable {
    private final Set<String> whiteListUrl = new LinkedHashSet<>();
    private final Set<String> blackListUrl = new LinkedHashSet<>();
    private final Set<String> blackListExtension = new LinkedHashSet<>();
    private final Set<AuthUser> users = new LinkedHashSet<>();
    private final Set<String> whiteListIp = new LinkedHashSet<>();
    private final Set<String> blackListIp = new LinkedHashSet<>();
    private final Set<TimeRule> timeRules = new LinkedHashSet<>();
    private boolean allowEverything;
    private CacheSize cacheSize;

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

    public synchronized void addAuthUser(AuthUser auth) {
        users.add(auth);
    }

    public synchronized boolean removeAuthUser(int codigo) {
        return users.removeIf(au -> au.getCodigo() == codigo);
    }

    public synchronized void addWhitelistIp(String ip) {
        whiteListIp.add(ip);
    }

    public synchronized boolean removeWhitelistIp(String ip) {
        return whiteListIp.remove(ip);
    }

    public synchronized void addBlacklistIp(String ip) {
        blackListIp.add(ip);
    }

    public synchronized boolean removeBlacklistIp(String ip) {
        return blackListIp.remove(ip);
    }

    public synchronized void addTimeRule(TimeRule timeRule) {
        timeRules.add(timeRule);
    }

    public synchronized boolean removeTimeRule(String nome) {
        return timeRules.removeIf(tr -> tr.getNome().equals(nome));
    }

    public Optional<CacheSize> getCacheSize() {
        return Optional.ofNullable(cacheSize);
    }

    public void setCacheSize(CacheSize cacheSize) {
        this.cacheSize = cacheSize;
    }

    boolean isAllowEverything() {
        return allowEverything;
    }

    public void setAllowEverything(boolean allowEverything) {
        this.allowEverything = allowEverything;
    }

    public Set<String> getWhiteListUrl() {
        return whiteListUrl;
    }

    public Set<String> getBlackListUrl() {
        return blackListUrl;
    }

    public Set<String> getBlackListExtension() {
        return blackListExtension;
    }

    public Set<AuthUser> getUsers() {
        return users;
    }

    public Set<String> getWhiteListIp() {
        return whiteListIp;
    }

    public Set<String> getBlackListIp() {
        return blackListIp;
    }

    public Set<TimeRule> getTimeRules() {
        return timeRules;
    }


}
