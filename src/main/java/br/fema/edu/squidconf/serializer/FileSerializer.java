package br.fema.edu.squidconf.serializer;

import br.fema.edu.squidconf.model.CacheSize;
import br.fema.edu.squidconf.nativ.ProcessRunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

@SuppressWarnings("SpellCheckingInspection")
public class FileSerializer {
    private static final Path SQUID_CONF_PATH = Paths.get("/", "etc", "squid3", "squid.conf");

    private FileSerializer() {
    }

    public static void writeConfiguration(SquidFileRepo squidFileRepo) {

        try (PrintWriter printer = new PrintWriter(Files.newBufferedWriter(SQUID_CONF_PATH,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE))) {
            printer.println("http_port 3128");
            printer.println("visible_hostname proxy.joaopedro.com.br");
            CacheSize cache = squidFileRepo.getCacheSize().orElse(new CacheSize(128, 16));
            printer.println(format("cache_mem {0} MB", cache.getDiskCacheSize()));
            printer.println(format("maximum_object_size_in_memory {0} MB", cache.getMemoryCacheSize()));
            printer.println("maximum_object_size 128 MB");
            printer.println("minimum_object_size 0 KB");
            printer.println("cache_swap_low 90");
            printer.println("cache_swap_high 95");
            printer.println("cache_dir ufs /var/spool/squid 256 10 128");
            printer.println("cache_access_log /var/log/squid3/access.log");
            if (!squidFileRepo.getUsers().isEmpty()) {
                ProcessRunner.writePasswords(squidFileRepo.getUsers());
                printer.println("auth_param basic realm squid");
                printer.println("auth_param basic program /usr/lib/squid3/basic_ncsa_auth  /etc/squid3/squid_passwd");
                printer.println("ACL AUTENTICADOS PROXY_AUTH REQUIRED");
                printer.println("acl permitidos proxy_auth –i  \"/etc/squid3/squid_passwd\"");
                printer.println("http_access allow permitidos");
            }
            if (squidFileRepo.isAllowEverything()) {
                printer.println("acl libera_geral src all");
                printer.println("http_access allow libera_geral");
                printer.println("acl blacklist url_regex -i ");
                printer.println("http_access deny blacklist \"/etc/squid3/bloqueados\"");
            } else {
                printer.println("acl whitelist url_regex –i \"/etc/squid3/liberados\"");
                printer.println("http_access deny all");
                printer.println("http_access allow whitelist");
            }
            String ipsBloqueados = squidFileRepo.getBlackListIp().stream().collect(Collectors.joining(" "));
            if (!ipsBloqueados.isEmpty()) {
                printer.println("acl ipbloqueado dst " + ipsBloqueados);
                printer.println("http_access  deny ipbloqueado");
            }
            String ipsAceitos = squidFileRepo.getWhiteListIp().stream().collect(Collectors.joining(" "));
            if (!ipsAceitos.isEmpty()) {
                printer.println("acl ipaceito src " + ipsAceitos);
                printer.println("http_access allow ipaceito");
            }
            squidFileRepo.getTimeRules().forEach(tr -> {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
                printer.println(String.format("acl %s time %s-%s", tr.getNome(), timeFormatter.format(tr.getBegin()), timeFormatter
                        .format(tr.getEnd())));
                String ruleType = tr.isDeny() ? "deny" : "allow";
                printer.println(String.format("http_access %s %s", ruleType, tr.getNome()));
            });
            printer.println("acl ext_bloq url_regex -i \"/etc/squid3/extbloq\"");
            writeBlackListUrl(squidFileRepo);
            writeWhiteListUrl(squidFileRepo);
            writeExtensionBlock(squidFileRepo);
            ObjectSerializer.write(squidFileRepo);
            ProcessRunner.reloadSquid();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

    private static void writeExtensionBlock(SquidFileRepo squidFileRepo) throws IOException {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get("/etc/squid3/extbloq"),
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE))) {
            squidFileRepo.getBlackListExtension()
                    .stream()
                    .map(ext -> String.format("\\%s$", ext))
                    .forEach(writer::println);
        }
    }

    private static void writeWhiteListUrl(SquidFileRepo squidFileRepo) throws IOException {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get("/etc/squid3/liberados"), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE))) {
            squidFileRepo.getWhiteListUrl().forEach(writer::println);
        }
    }

    private static void writeBlackListUrl(SquidFileRepo squidFileRepo) throws IOException {
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get("/etc/squid3/bloqueados"), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE))) {
            squidFileRepo.getBlackListUrl().forEach(writer::println);
        }
    }
}
