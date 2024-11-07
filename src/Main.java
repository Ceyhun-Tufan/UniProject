import TxtHandler.TxtDataBase;
import TxtHandler.Book;
import TxtHandler.Serializer;

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

public class Main {

    public static void main(String[] args) {
        TxtDataBase handler = new TxtDataBase();
        Serializer serializer = new Serializer();

        serializer.Serialize();

        if(!(handler.setPath("database.txt"))){
            return;
        }

        ArrayList<String> datas = handler.loadData();

        System.out.println(datas);
        
        datas.add("denem 1 2");

        handler.writeData(datas);
        System.out.println(handler.loadData());

        
    }
    
}