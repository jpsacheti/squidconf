package br.fema.edu.squidconf.nativ;

import br.fema.edu.squidconf.model.AuthUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ProcessRunner {

    public static void writePasswords(Set<AuthUser> users) {
        try {
            final Path path = Paths.get("/etc/squid/squid_passwd");
            if (Files.exists(path)) {
                Files.delete(path);
            }
            Files.createFile(path);
            for (AuthUser au : users) {
                ProcessBuilder pb = new ProcessBuilder();
                pb.command("htpasswd", "-b", path.toString(), au.getUsername(), au.getPassword());
                pb.start().waitFor();

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void reloadSquid() {
        try {
            ProcessBuilder pb = new ProcessBuilder();
            final List<String> comandos = Arrays.asList("service", "squid", "restart");
            pb.command(comandos);
            final Process start = pb.start();
            start.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
