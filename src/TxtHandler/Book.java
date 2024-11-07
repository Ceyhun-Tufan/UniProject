package TxtHandler;
import java.util.Date;

public class Book {

    String book_name;
    Date created_at; // constructer ile tanımlanacak
    String genre;
    Writer writer;
    int count;

    Book(String book_name,String genre, Writer writer,int count){
        this.book_name = book_name;
        this.genre = genre;
        this.writer = writer;
        this.count = count;
    }

}
