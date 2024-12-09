import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;

import CrudHelper.crudHandler;
import TxtHandler.Book;

public class Controller {

    @FXML
    private Button adminLoginButton;

    @FXML
    private Button mybutton;

    @FXML
    private TextField mytext;

    @FXML
    private TableView<Book> bookTableView;

    @FXML
    private TableColumn<Book, String> namecol;

    @FXML
    private TableColumn<Book, String> writercol;

    @FXML
    private TableColumn<Book, String> genrecol;

    @FXML
    private TableColumn<Book, Integer> pagecol;

    @FXML
    private TableColumn<Book, Integer> yearcol;

    @FXML
    private TableColumn<Book, Integer> stockcol;

    private final crudHandler crud = new crudHandler();
    private ArrayList<Book> books = crud.listBooks();
    private ObservableList<Book> observableBooks;

    @FXML
    private void initialize() {
        namecol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        writercol.setCellValueFactory(new PropertyValueFactory<>("writer"));
        genrecol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        pagecol.setCellValueFactory(new PropertyValueFactory<>("page"));
        yearcol.setCellValueFactory(new PropertyValueFactory<>("writtenYear"));
        stockcol.setCellValueFactory(new PropertyValueFactory<>("count"));

        loadTableData();
    }

    private void loadTableData() {
        if (books == null || books.isEmpty()) {
            bookTableView.getItems().clear();
            return;
        }

        syncBookTable();
    }

    @FXML
    private void addBookName(MouseEvent event) {

    }

    @FXML
    private void handleSearchingEvent(KeyEvent event) {
        // finding the related books

        ObservableList<Book> bookSearch;

        ArrayList<Book> foo = new ArrayList<Book>();
        String text = mytext.getText().toLowerCase();
        for (Book book : books) {
            if (book.getBookName().toLowerCase().startsWith(text)) {
                foo.add(book);
            }

        }

        bookSearch = FXCollections.observableArrayList(foo);
        bookTableView.setItems(bookSearch);

    }

    @FXML
    private void handleRowClick(MouseEvent event) {

        if (event.getClickCount() == 2) {

            Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {

                System.out.println(selectedBook.getBookName());
                showBookDetails(selectedBook);

            }
        }
    }

    // d&r da kitabi aramasi icin.
    private void showBookDetails(Book book) {
        try {

            String url = "https://www.dr.com.tr/search?q=" + URLEncoder.encode(book.getBookName(), "UTF8")
                    + "&redirect=search";
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.BROWSE)) {

                desktop.browse(new URI(url));

            } else {
                System.out.println("olmadi yar");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void adminLoginButtonClicked(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("adminLogin.fxml"));
            Parent adminLogin = loader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(adminLogin));
            stage.setTitle("Admin Login");
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void syncBookTable() {
        books = crud.listBooks();
        observableBooks = FXCollections.observableArrayList(books);
        bookTableView.setItems(observableBooks);
    }

}
