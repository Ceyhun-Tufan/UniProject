package TxtHandler;

public class Book {
    public static int id_increment = 1; // Tüm kitapları unique şekilde id ilemek için
    private final int id;
    private String book_name;
    private String writer;
    private String genre;
    private long page;
    private int written_year;
    private long count;

    public Book(String book_name, String writer, String genre, long page, int written_year, long count) {
        this.id = id_increment;
        id_increment++;

        this.book_name = book_name;
        this.genre = genre;
        this.writer = writer;
        this.page = page;
        this.count = count;
        this.written_year = written_year;
    }

    // Getter ve Setterlar
    public int getId() {
        return id;
    }

    // id okunabilir olmalı, setter eklemedim

    public String getBookName() {
        return book_name;
    }

    public void setBookName(String book_name) {
        this.book_name = book_name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        if (page > 0) { // Sayfa sayısı pozitif olmalı
            this.page = page;
        } else {
            System.out.println("Page count cannot be negative or zero!");
        }
    }

    public int getWrittenYear() {
        return written_year;
    }

    public void setWrittenYear(int written_year) {
        this.written_year = written_year;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        if (count >= 0) { // Stok sayısı negatif olamaz
            this.count = count;
        } else {
            System.out.println("Count cannot be negative!");
        }
    }

    @Override
    public String toString() {
        return this.id + "-" + this.book_name + "-" + this.writer + "-" + this.genre + "-" +
               this.page + "-" + this.written_year + "-" + this.count;
    }
}
