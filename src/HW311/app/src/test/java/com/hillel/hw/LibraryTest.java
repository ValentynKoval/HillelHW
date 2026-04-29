package com.hillel.hw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.hillel.hw.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryTest {

    private static Library library;

    @BeforeEach
    public void setupLibrary() {
        library = new Library();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("War and Peace", "Leo Tolstoy");
        Book book2 = new Book("The Brothers Karamazov", "Fyodor Dostoevsky");
        assertEquals(0, library.getBookCount());
        library.addBook(book);
        assertEquals(1, library.getBookCount());
        library.addBook(book2);
        assertEquals(2, library.getBookCount());
        library.addBook(book);
        assertEquals(2, library.getBookCount());
    }

    @Test
    public void testRemoveBook() {
        Book book = new Book("War and Peace", "Leo Tolstoy");
        Book book2 = new Book("The Brothers Karamazov", "Fyodor Dostoevsky");
        assertEquals(0, library.getBookCount());
        library.addBook(book);
        assertEquals(1, library.getBookCount());
        assertFalse(library.removeBook(book2));
        assertTrue(library.removeBook(book));
        assertEquals(0, library.getBookCount());
    }

    @Test
    public void testGetBooks() {
        Book book = new Book("War and Peace", "Leo Tolstoy");
        Book book2 = new Book("The Brothers Karamazov", "Fyodor Dostoevsky");
        assertEquals(0, library.getBookCount());
        library.addBook(book);
        library.addBook(book2);
        assertEquals(2, library.getBookCount());
        assertEquals(List.of(book, book2), library.getBooks());
    }

    @Test
    public void testGetBookCount() {
        Book book = new Book("War and Peace", "Leo Tolstoy");
        Book book2 = new Book("The Brothers Karamazov", "Fyodor Dostoevsky");
        assertEquals(0, library.getBookCount());
        library.addBook(book);
        assertEquals(1, library.getBookCount());
        library.addBook(book2);
        assertEquals(2, library.getBookCount());
    }
}