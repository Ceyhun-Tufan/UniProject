package CrudHelper;

import TxtHandler.Book;
import TxtHandler.TxtDatabaseHandler;
import java.util.ArrayList;

public class crudHandler {

    private static crudHandler instance; // Singleton yapi 
    private ArrayList<Book> book_list;
    private final TxtDatabaseHandler db;


    private crudHandler() {
        this.book_list = new ArrayList<>();
        this.db = new TxtDatabaseHandler();
        this.db.setPath("database.kitap");
        this.book_list = db.fetchData();
    }

    // cok anlamiyorum burayi bana da sorma
    public static synchronized crudHandler getInstance() {
        if (instance == null) {
            instance = new crudHandler();
        }
        return instance;
    }

    public void createBook(String book_name, String writer, String genre, long page, int written_year, long count) {
        Book newBook = new Book(book_name, writer, genre, page, written_year, count);
        book_list.add(newBook);
        System.out.println("Yeni kitap başarıyla eklendi: " + newBook);
        syncDb();
    }

    public void createBook(Book book){
        book_list.add(book);
        syncDb();
    }

    public Book findBook(Book book) {
        if (book_list.isEmpty()) {
            System.out.println("Kitap listesi boş");
            return null;
        }else if(book_list.contains(book)){
            return book_list.get(book.getId()-1);
        }

        System.out.println("Bu ada sahip kitap bulunamadı");
        return null;
    }


    public void updateBook(Book book){ //make this function to find the book and update it


        try {
            book_list.set(book.getId()-1, book);
            
        } catch (Exception e) {
            return;  
        }
        
        syncDb();

    }


    public void deleteBook(Book book) {
        if(!book_list.contains(book)){
            return;
        }

        book_list.remove(book);
        System.out.println("Kitap başarıyla silindi: " + book);
        syncDb();
    }


    public ArrayList<Book> listCachedBooks() {
        if (book_list.isEmpty()) {
            System.out.println("Kitap listesi boş");
            return null;
        } else {
            return book_list;
        }
    }

    private void syncDb() {
        db.writeData(book_list);
    }
}
