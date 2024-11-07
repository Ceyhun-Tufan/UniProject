package TxtHandler;

public class Book {

    String book_name;
    String writer;
    String genre;
    int page;
    int written_year; // constructer ile tanımlanacak
    int count;

    public Book(String book_name,String writer,String genre, int page,int written_year, int count){
        this.book_name = book_name;
        this.genre = genre;
        this.writer = writer;
        this.page = page;
        this.count = count;
        this.written_year = written_year;
    }


    // kitap yazdırmada kolaylık için

    @Override
    public String toString() {

        String bookstring;
        bookstring = this.book_name +"-"+ this.writer + "-" + this.genre + "-" + String.valueOf(this.page) +"-"+ String.valueOf(this.written_year) +"-"+String.valueOf(this.count);
        return bookstring;
    }
}
