package br.fema.edu.squidconf.serializer;

import br.fema.edu.squidconf.repository.SquidFileRepo;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ObjectSerializer {
    private static final Path confFile = Paths.get("state.srlz");

    public synchronized static void write(SquidFileRepo squidFileRepo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(confFile))) {
            oos.writeObject(squidFileRepo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean("serialized")
    public synchronized static SquidFileRepo read() {
        if (Files.exists(confFile)) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(confFile))) {
                SquidFileRepo squidFileRepo = (SquidFileRepo) ois.readObject();
                return squidFileRepo;
            } catch (IOException | ClassNotFoundException ioe) {
                ioe.printStackTrace();
            }
        }
        return new SquidFileRepo();
    }
}
