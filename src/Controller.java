import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller{ 

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> bookListView;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button mybutton;

    @FXML
    private TextField mytext;

    @FXML
    void initialize() {
        assert bookListView != null : "fx:id=\"bookListView\" was not injected: check your FXML file 'gui.fxml'.";
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'gui.fxml'.";
        assert mybutton != null : "fx:id=\"mybutton\" was not injected: check your FXML file 'gui.fxml'.";
        assert mytext != null : "fx:id=\"mytext\" was not injected: check your FXML file 'gui.fxml'.";

    }


    @FXML
    void addBookName(MouseEvent event){
        bookListView.getItems().add(mytext.getText());
    }

}
