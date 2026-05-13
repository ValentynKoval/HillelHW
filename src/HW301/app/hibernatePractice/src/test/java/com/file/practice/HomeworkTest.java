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
//class HomeworkTest {
//
//    @Test
//    void setStudentShouldMoveHomeworkBetweenStudents() {
//        Student first = new Student("First", "Student", "first@mail.com");
//        Student second = new Student("Second", "Student", "second@mail.com");
//        Homework homework = new Homework("Task", LocalDate.now().plusDays(3), 9);
//
//        first.addHomework(homework);
//        homework.setStudent(second);
//
//        assertTrue(second.getHomeworks().contains(homework));
//        assertTrue(first.getHomeworks().isEmpty());
//        assertEquals(second, homework.getStudent());
//    }
//
//    @Test
//    void setStudentNullShouldUnlinkHomework() {
//        Student student = new Student("Name", "Surname", "name@mail.com");
//        Homework homework = new Homework("Task", LocalDate.now().plusDays(4), 8);
//        student.addHomework(homework);
//
//        homework.setStudent(null);
//
//        assertNull(homework.getStudent());
//        assertTrue(student.getHomeworks().isEmpty());
//    }
//
//    @Test
//    void equalsAndHashCodeShouldUseId() {
//        Homework first = new Homework("A", LocalDate.now(), 10);
//        Homework second = new Homework("B", LocalDate.now().plusDays(1), 11);
//        first.setId(7L);
//        second.setId(7L);
//
//        assertEquals(first, second);
//        assertEquals(first.hashCode(), second.hashCode());
//
//        second.setId(8L);
//        assertNotEquals(first, second);
//    }
//}
