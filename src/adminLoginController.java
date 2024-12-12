import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class adminLoginController {

    @FXML
    private TextField IDText;

    @FXML
    private TextField PassText;

    @FXML
    private Label adminLoginAlertLabel;

    @FXML
    private Button loginButton;

    private String id = "123";
    private String password = "deneme";
    private Controller parentController;
    @FXML
    private void loginPressed(MouseEvent event) {


        System.out.println(IDText.getText());
        System.out.println(PassText.getText());


        if(IDText.getText().equals(id) && PassText.getText().equals(password)){
            redirectToAdminPanel();
        }else{
            adminLoginAlertLabel.setVisible(true);
        }


    }

    private void redirectToAdminPanel(){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminPage.fxml"));
            Parent adminPage = loader.load();
            
            adminPageController adminController = loader.getController();
            adminController.setParentController(parentController);

            Stage stage = new Stage();
            stage.setScene(new Scene(adminPage));
            stage.setTitle("Admin Panel");
            stage.setMinHeight(600);
            stage.setMinWidth(800);
    
            stage.show();

            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();


            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void setParentController(Controller controller){
        parentController = controller;
    }

}
