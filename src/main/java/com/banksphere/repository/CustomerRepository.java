package com.banksphere.repository;

import com.banksphere.customer.Customer;
import com.banksphere.enums.CustomerStatus;
import com.banksphere.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

/**
 * CustomerRepository — CRUD operations for the Customer entity.
 */
public class CustomerRepository {

    public void save(Customer customer) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /** Find by customer ID (e.g. "CID1"). */
    public Optional<Customer> findById(String customerId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Customer.class, customerId));
        } finally {
            em.close();
        }
    }

    /** All ACTIVE customers across all branches. */
    public List<Customer> findAllActive() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Customer c WHERE c.status = :status", Customer.class)
                    .setParameter("status", CustomerStatus.ACTIVE)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /** All customers (any status). */
    public List<Customer> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        } finally {
            em.close();
        }
    }

    /** Update customer (e.g. status change). */
    public void update(Customer customer) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(customer);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
