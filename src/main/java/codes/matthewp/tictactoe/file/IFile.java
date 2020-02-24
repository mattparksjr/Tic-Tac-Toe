package codes.matthewp.tictactoe.file;

import codes.matthewp.tictactoe.TicTacToe;
import sun.dc.pr.PRError;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Properties;

public class IFile {

    private String name;
    private boolean inJar;
    private Properties properties;

    public IFile(String name, boolean inJar) {
        this.name = name;
        this.inJar = inJar;
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
            properties.load(TicTacToe.class.getResourceAsStream("/" + name + ".properties"));
        } else {
            File file = new File(name + ".properties");
            if (! file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Created missing file: " + name + ".properties");
                    Files.copy(TicTacToe.class.getResourceAsStream("/" + name + ".properties"),
                            FileSystems.getDefault().getPath(name + ".properties"),
                            StandardCopyOption.REPLACE_EXISTING);
                } else {
                    System.out.println("Failed to create missing file: " + name + ".properties");
                }
            }
            Path path = FileSystems.getDefault().getPath(name + ".properties");
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(name + ".properties")));
            properties.store(writer, "");
        }catch (IOException ex) {
            System.out.println("Caught exp saving config");
            ex.printStackTrace();
        }
    }
}
