package com.banksphere.repository;

import com.banksphere.account.Account;
import com.banksphere.enums.AccountStatus;
import com.banksphere.enums.AccountType;
import com.banksphere.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

/**
 * AccountRepository — CRUD operations for the Account entity hierarchy.
 *
 * Because Account uses JOINED inheritance, a single JPQL query against
 * Account will automatically fetch SavingsAccount and CurrentAccount rows.
 */
public class AccountRepository {

    public void save(Account account) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(account);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /** Find an account by account number. */
    public Optional<Account> findByAccountNumber(int accountNumber) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Account.class, accountNumber));
        } finally {
            em.close();
        }
    }

    /** All accounts (any type / status). */
    public List<Account> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
        } finally {
            em.close();
        }
    }

    /** All ACTIVE accounts. */
    public List<Account> findAllActive() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT a FROM Account a WHERE a.status = :status", Account.class)
                    .setParameter("status", AccountStatus.ACTIVE)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /** Accounts by type. */
    public List<Account> findByType(AccountType type) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT a FROM Account a WHERE a.type = :type", Account.class)
                    .setParameter("type", type)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /** Accounts for a specific customer. */
    public List<Account> findByCustomerId(String customerId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT a FROM Account a WHERE a.customer.customerId = :cid", Account.class)
                    .setParameter("cid", customerId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Merge updated account state back to the DB.
     * Call this after deposit / withdraw / transfer to persist balance changes.
     */
    public void update(Account account) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(account);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
