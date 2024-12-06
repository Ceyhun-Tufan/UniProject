package TxtHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Txt dosya islemlerinde yazma ve okumayi hizlandirmak icin class 

// checkDb() - argumanlar : Yok - return tipi : Boolean
//
// db nin varligini kontrol eder. Duruma gore TF return eder

// loadData() - argumanlar : Yok - return tipi : ArrayList<Book>
//
// txt dosyasindan tum verileri bir ArrayList'ine alir.
// listedeki tum elemanlari daha oncesinde atanmis #bakiniz setSerializerClass()# 
// class yapisina gore serializer'dan gecirerek ayni class yapisindan olusan bir liste dondurur

// writeData() - argumanlar : ArrayList<Book> ; return tipi : Void
// 
// parametre olarak belirlenmis class yapisindan olusan bir liste alir.
// bu listedeki elemanlari Book un icerisine yazılmıs toString methodu ile 
// degerleri tek satirlik bir stringe cevirir. daha sonrasinda verileri 
// txt dosyaniza yazar 

// setPath() - argumanlar : String ; return tipi : boolean
//
// txt dosyanizin yolunu belirler 

// DATABASEIN CALISMA MANTIGI:
// SIRASIYLA!!!
// BOOKNAME - WRITER - GENRE - PAGE - WRITTEN YEAR - COUNT
// yani her zaman String[6]


public class TxtDatabaseHandler {

    private String path;

    public boolean setPath(String newpath) {
        path = newpath;
        return checkDb();
    }

    public ArrayList<Book> fetchData() {

        Book.id_increment = 1;

        String line;
        ArrayList<String[]> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            System.out.println("Veriler yukleniyor");
            while ((line = reader.readLine()) != null) {
                data.add(line.split("-"));
            }
            reader.close();

        } catch (IOException e) {
            System.err.println(e);
        }

        return Serialize(data);

    }

    public void writeData(ArrayList<Book> data) {


        ArrayList<String> bookdata = DeSerialize(data);
        

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            for (String book : bookdata) {
                book += "\n";
                writer.write(book);
            }

            writer.close();

        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public ArrayList<Book> Serialize(ArrayList<String[]> datas) {

        String[] line;
        ArrayList<Book> books = new ArrayList<>();


        for (int i = 0; i < datas.size(); i++) {
            line = datas.get(i);

            books.add(new Book(line[1], line[2], line[3], Integer.parseInt(line[4]), Integer.parseInt(line[5]),
                    Integer.parseInt(line[6])));
        }

        return books;

    }


    // serialize single
    public Book Serialize(String[] data){
        
        return new Book(data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]),
        Integer.parseInt(data[6]));

    }



    public ArrayList<String> DeSerialize(ArrayList<Book> data) {

        ArrayList<String> bookdata = new ArrayList<>();
        
        for (Book book : data) {
            bookdata.add(book.toString());

        }

        return bookdata;
    }


    public String DeSerialize(Book data){
        return data.toString();
    }

    private boolean checkDb() {

        File f = new File(path);

        if (f.exists()) {

            return true;

        } else {
            try {
                f.createNewFile();
                return true;
            } catch (IOException e) {
                System.err.println(e);
                return false;
            }
        }

    }

}