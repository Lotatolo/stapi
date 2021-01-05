package net.stapi.mv;

import net.stapi.mv.config.StubConfig;
import net.stapi.mv.config.StubConfigConstructor;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        InputStream input = new FileInputStream(new File(
                "C:\\Users\\User\\IdeaProjects\\Test\\src\\main\\resources\\config.yaml"));

        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        Yaml yaml = new Yaml(new StubConfigConstructor(), representer);
        StubConfig sc = yaml.loadAs(input, StubConfig.class);
        sc.initServices();
        System.out.println("end");
    }
}
