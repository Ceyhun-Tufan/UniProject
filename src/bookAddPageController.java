import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class bookAddPageController {

    @FXML
    private Button addButton;

    @FXML
    private TextField genretext;

    @FXML
    private TextField nametext;

    @FXML
    private TextField pagetext;

    @FXML
    private TextField stocktext;

    @FXML
    private TextField writertext;

    @FXML
    private TextField yeartext;

    adminPageController parentController;

    @FXML
    void handleAddButtonClick(MouseEvent event) {

    }

    public void setParentController(adminPageController controller){

        parentController = controller;

    }

}
