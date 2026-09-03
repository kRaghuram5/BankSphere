package com.banksphere.util;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * EnvLoader — reads the project-root .env file and injects all keys
 * as System properties so that persistence.xml can reference them
 * via ${DB_URL}, ${DB_USERNAME}, ${DB_PASSWORD}.
 *
 * Call EnvLoader.load() once at application startup before
 * HibernateUtil is initialised.
 */
public class EnvLoader {

    private EnvLoader() {}

    /**
     * Loads .env from the project root (or classpath) and sets each
     * entry as a System property.  Safe to call multiple times — it
     * will only load once.
     */
    public static void load() {
        Dotenv dotenv = Dotenv.configure()
                .directory("./")          // project root
                .ignoreIfMissing()        // won't crash if .env is absent
                .load();

        dotenv.entries().forEach(e ->
                System.setProperty(e.getKey(), e.getValue())
        );

        System.out.println("[EnvLoader] Environment loaded.");
    }
}
