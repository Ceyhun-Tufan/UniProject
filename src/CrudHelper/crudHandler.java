package CrudHelper;

import TxtHandler.Book;
import TxtHandler.TxtDatabaseHandler;
import java.util.ArrayList;

public class crudHandler {

    private ArrayList<Book> book_list;
    private final TxtDatabaseHandler db;

    public crudHandler() {
        this.book_list = new ArrayList<>();
        this.db = new TxtDatabaseHandler();
        this.db.setPath("database.txt.kac");
        this.book_list = db.fetchData();

    }

    public void createBook(String book_name, String writer, String genre, int page, int written_year, int count) {

        Book newBook = new Book(book_name, writer, genre, page, written_year, count);
        book_list.add(newBook);
        System.out.println("Yeni kitap başarıyla eklendi: " + newBook);

        syncDb();

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

    public boolean updateBook(String book_name, String new_writer, String new_genre, int new_page, int new_written_year,
            int new_count) {

        Book book = findByName(book_name);

        if (book != null) {

            book.setWriter(new_writer);
            book.setGenre(new_genre);
            book.setPage(new_page);
            book.setWrittenYear(new_written_year);
            book.setCount(new_count);
            System.out.println("Kitap başarıyla güncellendi: " + book);
            syncDb();

            return true;

        }
        return false;
    }

    public boolean deleteBook(String book_name) {

        Book book = findByName(book_name);

        if (book != null) {

            book_list.remove(book);
            System.out.println("Kitap başarıyla silindi: " + book);
            syncDb();
            return true;
        }

        System.out.println("Silinecek kitap bulunamadı.");
        return false;

    }

    public ArrayList<Book> listBooks() {

        if (book_list.isEmpty()) {
            System.out.println("Kitap listesi boş");

            return null;
        }

        else {

            return book_list;

        }

    }

    private void syncDb() {
        db.writeData(book_list);
    }

}
