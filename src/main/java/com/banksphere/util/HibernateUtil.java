package com.banksphere.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * HibernateUtil — manages the lifecycle of the JPA EntityManagerFactory.
 *
 * The EMF is an expensive object — create once, reuse everywhere.
 * Call HibernateUtil.getEntityManager() to get a fresh EntityManager
 * for each unit of work (repository method).
 * Always close the EntityManager after use.
 *
 * Call HibernateUtil.shutdown() when the application exits.
 */
public class HibernateUtil {

    private static final String PERSISTENCE_UNIT = "BankSpherePU";
    private static EntityManagerFactory emf;

    private HibernateUtil() {}

    /** Returns the singleton EntityManagerFactory, creating it if needed. */
    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
            System.out.println("[HibernateUtil] EntityManagerFactory created.");
        }
        return emf;
    }

    /** Returns a fresh EntityManager. Caller is responsible for closing it. */
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    /** Closes the EntityManagerFactory gracefully on application exit. */
    public static void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("[HibernateUtil] EntityManagerFactory closed.");
        }
    }
}
