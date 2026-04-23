package com.file.practice;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentDaoTest {

    private EntityManagerFactory entityManagerFactory;
    private StudentDao studentDao;

    @BeforeEach
    void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hillel-persistence-unit-test");
        studentDao = new StudentDao(entityManagerFactory);
    }

    @AfterEach
    void tearDown() {
        if (studentDao != null) {
            studentDao.close();
        }
    }

    @Test
    void saveAndFindByIdShouldWork() {
        Student student = new Student("Ivan", "Petrenko", "ivan.id@mail.com");
        studentDao.save(student);

        assertNotNull(student.getId());
        Student found = studentDao.findById(student.getId());
        assertNotNull(found);
        assertEquals("Ivan", found.getFirstName());
    }

    @Test
    void findByEmailShouldReturnEntityOrNull() {
        Student student = new Student("Anna", "Koval", "anna.find@mail.com");
        studentDao.save(student);

        Student found = studentDao.findByEmail("anna.find@mail.com");
        Student missing = studentDao.findByEmail("not.exists@mail.com");

        assertNotNull(found);
        assertEquals(student.getId(), found.getId());
        assertNull(missing);
    }

    @Test
    void findAllShouldReturnAllSavedStudents() {
        studentDao.save(new Student("A", "One", "a.one@mail.com"));
        studentDao.save(new Student("B", "Two", "b.two@mail.com"));

        List<Student> students = studentDao.findAll();
        assertEquals(2, students.size());
    }

    @Test
    void updateShouldPersistChanges() {
        Student student = new Student("Taras", "Old", "taras.update@mail.com");
        studentDao.save(student);

        student.setLastName("New");
        Student updated = studentDao.update(student);

        Student found = studentDao.findById(updated.getId());
        assertNotNull(found);
        assertEquals("New", found.getLastName());
    }

    @Test
    void deleteByIdShouldReturnResultAndDeleteEntity() {
        Student student = new Student("Delete", "Me", "delete.me@mail.com");
        studentDao.save(student);

        boolean deleted = studentDao.deleteById(student.getId());
        boolean deletedMissing = studentDao.deleteById(999_999L);

        assertTrue(deleted);
        assertFalse(deletedMissing);
        assertNull(studentDao.findById(student.getId()));
    }

    @Test
    void toStringShouldNotFailForDetachedStudentWithLazyHomeworks() {
        Student student = new Student("Lazy", "Case", "lazy.case@mail.com");
        studentDao.save(student);

        Student detached = studentDao.findByEmail("lazy.case@mail.com");

        String text = assertDoesNotThrow(detached::toString);
        assertTrue(text.contains("homeworksCount="));
    }
}
