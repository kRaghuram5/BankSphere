package com.banksphere.repository;

import com.banksphere.bank.Bank;
import com.banksphere.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

/**
 * BankRepository — CRUD operations for the Bank entity.
 */
public class BankRepository {

    /** Persist a new Bank. */
    public void save(Bank bank) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(bank);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /** Find a Bank by its ID. */
    public Optional<Bank> findById(String bankId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Bank.class, bankId));
        } finally {
            em.close();
        }
    }

    /** Retrieve all Banks. */
    public List<Bank> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT b FROM Bank b", Bank.class).getResultList();
        } finally {
            em.close();
        }
    }

    /** Merge (update) an existing Bank. */
    public void update(Bank bank) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(bank);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
