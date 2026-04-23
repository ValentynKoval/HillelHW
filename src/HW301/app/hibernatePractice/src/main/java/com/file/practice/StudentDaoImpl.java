package com.file.practice;

import jakarta.persistence.EntityManagerFactory;

public class StudentDaoImpl extends StudentDao {

    public StudentDaoImpl() {
        super();
    }

    public StudentDaoImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }
}
