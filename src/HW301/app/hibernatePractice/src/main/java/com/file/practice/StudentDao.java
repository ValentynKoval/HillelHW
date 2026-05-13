//package com.file.practice;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//
//import java.util.List;
//import java.util.Objects;
//
//public class StudentDao implements GenericDao<Student, Long>, AutoCloseable {
//
//    private final EntityManagerFactory entityManagerFactory;
//
//    public StudentDao() {
//        this(Persistence.createEntityManagerFactory("hillel-persistence-unit"));
//    }
//
//    public StudentDao(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
//
//    @Override
//    public void save(Student entity) {
//        Objects.requireNonNull(entity, "entity must not be null");
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            entityManager.persist(entity);
//            transaction.commit();
//        } catch (RuntimeException ex) {
//            rollbackIfNeeded(transaction);
//            throw ex;
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    @Override
//    public Student findById(Long id) {
//        if (id == null) {
//            return null;
//        }
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            return entityManager.find(Student.class, id);
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    @Override
//    public Student findByEmail(String email) {
//        if (email == null || email.isBlank()) {
//            return null;
//        }
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            List<Student> result = entityManager
//                    .createQuery("select s from Student s where s.email = :email", Student.class)
//                    .setParameter("email", email)
//                    .setMaxResults(1)
//                    .getResultList();
//            return result.isEmpty() ? null : result.get(0);
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    @Override
//    public List<Student> findAll() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        try {
//            return entityManager.createQuery("select s from Student s", Student.class).getResultList();
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    @Override
//    public Student update(Student entity) {
//        Objects.requireNonNull(entity, "entity must not be null");
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            Student merged = entityManager.merge(entity);
//            transaction.commit();
//            return merged;
//        } catch (RuntimeException ex) {
//            rollbackIfNeeded(transaction);
//            throw ex;
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        if (id == null) {
//            return false;
//        }
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            Student student = entityManager.find(Student.class, id);
//            if (student == null) {
//                transaction.commit();
//                return false;
//            }
//            entityManager.remove(student);
//            transaction.commit();
//            return true;
//        } catch (RuntimeException ex) {
//            rollbackIfNeeded(transaction);
//            throw ex;
//        } finally {
//            entityManager.close();
//        }
//    }
//
//    @Override
//    public void close() {
//        if (entityManagerFactory.isOpen()) {
//            entityManagerFactory.close();
//        }
//    }
//
//    protected EntityManagerFactory getEntityManagerFactory() {
//        return entityManagerFactory;
//    }
//
//    private void rollbackIfNeeded(EntityTransaction transaction) {
//        if (transaction != null && transaction.isActive()) {
//            transaction.rollback();
//        }
//    }
//}
