import TxtHandler.TxtDatabaseHandler;
import TxtHandler.Book;
import java.util.ArrayList;
import CrudHelper.crudHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("./gui.fxml"));
        primaryStage.setTitle("Veri Yapilari");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


// stok ekle sil 
// aynı kitaptan varsa count arttırma
// kitap listeleme 
// yazarlara göre kitap listeleme 
// türe göre kitap listeleme

// public class Main {

//     public static void main(String[] args) {
//         crudHandler crud = new crudHandler();

//         System.out.println();
//         System.out.println();


//         crud.createBook("Evet isyan", "Ismet Ozel", "Poem", 0, 1, 1);
//         Book deneme = crud.findByName("Evet isyan");

//         System.out.println(deneme.getBookName());



        
//     }


// }
