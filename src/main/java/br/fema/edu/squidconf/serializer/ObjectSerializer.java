package br.fema.edu.squidconf.serializer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ObjectSerializer {
    private static final Path confFile = Paths.get("state.srlz");

    public synchronized static void write(SquidFileRepo squidFileRepo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(confFile))) {
            oos.writeObject(squidFileRepo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public synchronized static SquidFileRepo read() {
        if (Files.exists(confFile)) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(confFile))) {
                return (SquidFileRepo) ois.readObject();
            } catch (IOException | ClassNotFoundException ioe) {
                ioe.printStackTrace();
            }
        }
        return new SquidFileRepo();
    }
}
