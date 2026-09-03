package com.banksphere.repository;

import com.banksphere.bank.Branch;
import com.banksphere.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

/**
 * BranchRepository — CRUD operations for the Branch entity.
 */
public class BranchRepository {

    public void save(Branch branch) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(branch);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /** Find a Branch by its IFSC code. */
    public Optional<Branch> findByIfsc(String ifsc) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Branch.class, ifsc));
        } finally {
            em.close();
        }
    }

    /** Get all Branches belonging to a specific Bank. */
    public List<Branch> findByBankId(String bankId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT br FROM Branch br WHERE br.bank.bankId = :bankId", Branch.class)
                    .setParameter("bankId", bankId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Branch branch) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(branch);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
