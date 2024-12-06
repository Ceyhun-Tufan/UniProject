package CrudHelper;

import TxtHandler.Book;
import java.util.ArrayList;

public class Crud {

    private final ArrayList<Book> book_list;

    public Crud() {
        this.book_list = new ArrayList<>();
    }

    public void createBook(String book_name, String writer, String genre, int page, int written_year, int count) {

        Book newBook = new Book(book_name, writer, genre, page, written_year, count);
        book_list.add(newBook);
        System.out.println("Yeni kitap başarıyla eklendi: " + newBook);

    }

    public Book findByName(String book_name) { 

        if (book_list.isEmpty()) {

            System.out.println("Kitap listesi boş");
            return null;

        }

        for (Book book : book_list) {

            if (book.getBookName().equals(book_name)) {
                return book;
            }

        }

        System.out.println("Bu ada sahip kitap bulunamadı");
        return null;

    }

    public boolean updateBook(String book_name, String new_writer, String new_genre, int new_page, int new_written_year, int new_count) {
        
        Book book = findByName(book_name);
       
        if (book != null) {
            
            book.setWriter(new_writer);
            book.setGenre(new_genre);
            book.setPage(new_page);
            book.setWrittenYear(new_written_year);
            book.setCount(new_count);
            System.out.println("Kitap başarıyla güncellendi: " + book);
            
            return true;

        }
        
        return false;
    }

    public boolean deleteBook(String book_name) {

        Book book = findByName(book_name);

        if (book != null) {

            book_list.remove(book);
            System.out.println("Kitap başarıyla silindi: " + book);
            return true;

        }

        System.out.println("Silinecek kitap bulunamadı.");
        return false;

    }

    public void listBooks() {

        if (book_list.isEmpty()) {
            System.out.println("Kitap listesi boş");
        } 
        
        else {
            
            System.out.println("Kitap Listesi:");
            for (Book book : book_list) {
                System.out.println(book);
            }

        }

    }
    

}
