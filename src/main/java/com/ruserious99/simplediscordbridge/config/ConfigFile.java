package com.ruserious99.simplediscordbridge.config;

import com.sun.tools.javac.Main;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigFile {

    public static void loadConfig() {

        String fileName = "config.yml";
        ClassLoader classLoader = Main.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {

                String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

                System.out.println(result);
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
                    bufferedWriter.write(result);
                    bufferedWriter.close();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
