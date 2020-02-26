package codes.matthewp.tictactoe.file;

import codes.matthewp.tictactoe.TicTacToe;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class IFile {

    private String name;
    private boolean inJar;
    private Properties properties;
    private String path;

    public IFile(String name, boolean inJar) {
        this(name, "", inJar);
    }

    public IFile(String name, String path, boolean inJar) {
        this.name = name;
        this.inJar = inJar;
        this.path = path;
        try {
            init();
        } catch (IOException ex) {
            System.out.println("Caught error loading file: " + name + ".properties");
            ex.printStackTrace();
        }
    }

    private void init() throws IOException {
        properties = new Properties();
        if (inJar) {
            properties.load(TicTacToe.class.getResourceAsStream(path + "/" + name + ".properties"));
        } else {
            File file = new File(name + ".properties");
            if (! file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Created missing file: " + name + ".properties");
                    Files.copy(TicTacToe.class.getResourceAsStream(path + "/" + name + ".properties"),
                            FileSystems.getDefault().getPath(name + ".properties"),
                            StandardCopyOption.REPLACE_EXISTING);
                } else {
                    System.out.println("Failed to create missing file: " + name + ".properties");
                }
            }
            Path pa = FileSystems.getDefault().getPath(path + name + ".properties");
            BufferedReader reader = Files.newBufferedReader(pa, StandardCharsets.UTF_8);
            properties.load(reader);
        }
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public double getDouble(String key) {
        return Double.parseDouble(getString(key));
    }

    public void save(Properties properties) {
        if(inJar) return;
        this.properties = properties;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path + name + ".properties")));
            properties.store(writer, "");
        }catch (IOException ex) {
            System.out.println("Caught exp saving config");
            ex.printStackTrace();
        }
    }
}
