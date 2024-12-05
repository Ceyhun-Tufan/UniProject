import TxtHandler.TxtDatabaseHandler;
import TxtHandler.Book;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// public class Main extends Application {

//     @Override
//     public void start(Stage primaryStage) throws Exception{
//         Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
//         primaryStage.setTitle("Veri Yapilari");
//         primaryStage.setScene(new Scene(root, 800, 600));
//         primaryStage.show();
//     }


//     public static void main(String[] args) {
//         launch(args);
//     }
// }


// stok ekle sil 
// aynı kitaptan varsa count arttırma
// kitap listeleme 
// yazarlara göre kitap listeleme 
// türe göre kitap listeleme

public class Main {

    public static void main(String[] args) {
        TxtDatabaseHandler handler = new TxtDatabaseHandler();

        System.out.println();
        System.out.println();

        if(!(handler.setPath("database.txt.kac"))){
            return;
        }

        
        ArrayList<Book> books = handler.fetchData();

        for (Book book : books) {
            System.out.println(book.toString());            
        }

        books.add(new Book("Suç Ve Ceza","Dosto","Drama",600,1900,1));

        handler.writeData(books);

        books = handler.fetchData();

        System.out.println();
        System.out.println();

        
        for (Book book : books) {
            System.out.println(book.toString());            
        }


    }

    // buralara search ve filter algoritmalarının fonksiyonları tek tek yazılacak
    // temel kalıplar ve istenilen returnler
    // fetch_all - arguman : yok - return: ArrayList<Book>
    
    // write_all - arguman : ArrayList<Book> - return: boolean ( islem basarılı basarısız )
    
    // fetch_single - overload edilmis bir fonksiyon olacak
    //  overload 1: book id si alır buna göre tek bir kitabı döndürür
    // 


}

