package com.banksphere.repository;

import com.banksphere.transaction.Transaction;
import com.banksphere.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

/**
 * TransactionRepository — CRUD operations for the Transaction entity.
 */
public class TransactionRepository {

    public void save(Transaction transaction) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /** Update a transaction (e.g. status change from PENDING → SUCCESS/FAILED). */
    public void update(Transaction transaction) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<Transaction> findById(int transactionId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Transaction.class, transactionId));
        } finally {
            em.close();
        }
    }

    /** All transactions for a given account number (as sender or receiver). */
    public List<Transaction> findByAccountNumber(int accountNumber) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT t FROM Transaction t " +
                    "WHERE t.senderAccount.accountNumber = :acc " +
                    "   OR t.receiverAccount.accountNumber = :acc " +
                    "ORDER BY t.timestamp DESC",
                    Transaction.class)
                    .setParameter("acc", accountNumber)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /** All transactions ever recorded. */
    public List<Transaction> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT t FROM Transaction t ORDER BY t.timestamp DESC", Transaction.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
