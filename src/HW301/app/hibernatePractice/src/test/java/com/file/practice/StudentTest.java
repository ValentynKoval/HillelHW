//package com.file.practice;
//
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class StudentTest {
//
//    @Test
//    void addHomeworkShouldLinkBothSides() {
//        Student student = new Student("Ivan", "Petrenko", "ivan@mail.com");
//        Homework homework = new Homework("Read chapter", LocalDate.now().plusDays(1), 10);
//
//        student.addHomework(homework);
//
//        assertTrue(student.getHomeworks().contains(homework));
//        assertEquals(student, homework.getStudent());
//    }
//
//    @Test
//    void removeHomeworkShouldUnlinkBothSides() {
//        Student student = new Student("Anna", "Shevchenko", "anna@mail.com");
//        Homework homework = new Homework("Write summary", LocalDate.now().plusDays(2), 11);
//        student.addHomework(homework);
//
//        student.removeHomework(homework);
//
//        assertTrue(student.getHomeworks().isEmpty());
//        assertNull(homework.getStudent());
//    }
//
//    @Test
//    void equalsAndHashCodeShouldUseId() {
//        Student first = new Student("A", "B", "a@mail.com");
//        Student second = new Student("C", "D", "c@mail.com");
//        first.setId(1L);
//        second.setId(1L);
//
//        assertEquals(first, second);
//        assertEquals(first.hashCode(), second.hashCode());
//
//        second.setId(2L);
//        assertNotEquals(first, second);
//    }
//}
