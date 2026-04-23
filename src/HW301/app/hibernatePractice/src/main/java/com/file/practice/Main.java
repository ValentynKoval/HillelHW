package com.file.practice;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try (StudentDao studentDao = new StudentDao()) {
            Student student = new Student("Ivan", "Petrenko", "ivan." + System.currentTimeMillis() + "@mail.com");
            Homework homework = new Homework("Hibernate intro task", LocalDate.now().plusDays(7), 12);
            student.addHomework(homework);

            studentDao.save(student);
            System.out.println("Saved student: " + student);

            Student foundByEmail = studentDao.findByEmail(student.getEmail());
            System.out.println("Found by email: " + foundByEmail);

            foundByEmail.setLastName("Shevchenko");
            Student updated = studentDao.update(foundByEmail);
            System.out.println("Updated student: " + updated);

            System.out.println("All students in DB: " + studentDao.findAll().size());

            boolean deleted = studentDao.deleteById(updated.getId());
            System.out.println("Deleted: " + deleted);
        } catch (Exception e) {
            System.err.println("Demo failed: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            System.err.println("If this is a connection/auth issue, check src/main/resources/META-INF/persistence.xml");
            e.printStackTrace();
        }
    }
}
