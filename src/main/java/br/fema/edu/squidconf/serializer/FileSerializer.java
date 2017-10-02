package br.fema.edu.squidconf.serializer;

import br.fema.edu.squidconf.model.CacheSize;
import br.fema.edu.squidconf.repository.SquidFileRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.text.MessageFormat.format;

public class FileSerializer {
    private static final Path SQUID_CONF_PATH = Paths.get("etc", "squid", "squid.conf");

    private FileSerializer() {
    }

    public static void writeConfiguration(SquidFileRepo squidFileRepo) throws IOException {
        PrintWriter writer = new PrintWriter(Files.newBufferedWriter(SQUID_CONF_PATH,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE));
        writer.println("http_port 3128");
        writer.println("visible_hostname proxy.joaopedro.com.br");
        CacheSize cache = squidFileRepo.getCacheSize().orElse(new CacheSize(128, 16));
        writer.println(format("cache_mem {0} MB", cache.getMemoryCacheSize()));
        writer.println(format("maximum_object_size_in_memory {0} MB", cache.getMemoryCacheSize()));
        writer.println("maximum_object_size 128 MB");
        writer.println("minimum_object_size 0 KB");
        writer.println("cache_swap_low 90");
        writer.println("cache_swap_high 95");
        writer.println("cache_dir ufs /var/spool/squid3 256 10 128");
        writer.println("cache_access_log /var/log/squid3/access.log");
    }
}
