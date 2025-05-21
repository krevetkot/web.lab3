
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Replacer {
    public static void main(String[] args) throws Exception {
    System.out.println("[DEBUG] Current working directory: " + System.getProperty("user.dir"));
        System.out.println("[DEBUG] Starting replacement process");
        System.out.println("[DEBUG] Replacements file: C:/Users/User/java_workspace/4sem/opi_web.lab3/src/main/resources/replacements/replacements.properties");

        // Загрузка замен
        Map<String,String> replaces = new HashMap<>();
        try (InputStream is = new FileInputStream("C:/Users/User/java_workspace/4sem/opi_web.lab3/src/main/resources/replacements/replacements.properties")) {
            Properties props = new Properties();
            props.load(is);
            props.forEach((k,v) -> replaces.put((String)k, (String)v));
            System.out.println("[DEBUG] Loaded replacements: " + replaces);
        }

        // Обработка файлов
        Files.walk(Paths.get("./"))
            .filter(p -> p.toString().endsWith(".java") && !p.toString().contains("Replacer.java"))
            .forEach(p -> {
                try {
                    System.out.println("\n[DEBUG] Processing: " + p);
                    String originalContent = new String(Files.readAllBytes(p));
                    String modifiedContent = originalContent;

                    for (Map.Entry<String,String> e : replaces.entrySet()) {
                        modifiedContent = modifiedContent.replace(e.getKey(), e.getValue());
                    }

                    if (!originalContent.equals(modifiedContent)) {
                        Files.write(p, modifiedContent.getBytes());
                        System.out.println("[SUCCESS] File updated: " + p);
                    } else {
                        System.out.println("[SKIP] No changes for: " + p);
                    }
                } catch (Exception e) {
                    System.err.println("[ERROR] In file: " + p);
                    e.printStackTrace();
                }
            });

        System.out.println("[DEBUG] Replacement process completed");
    }
}
