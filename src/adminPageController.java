import TxtHandler.Book;
import CrudHelper.crudHandler;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class adminPageController {

    @FXML
    private Button addBookButton;
    @FXML
    private Button deleteBookButton;
    @FXML
    private Button updateBookButton;

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

    private final crudHandler crud = crudHandler.getInstance();
    private ArrayList<Book> books = crud.listCachedBooks();
    private ObservableList<Book> observableBooks;
    private Controller parentController;

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
                // after double clicking

            }
        }
    }

    @FXML
    void handleAddButtonEvent(MouseEvent event) {
        // bookAddPage i acacak
        // System.out.println("deneme");
        // try {
        // FXMLLoader loader = new
        // FXMLLoader(getClass().getResource("bookAddPage.fxml"));
        // Parent bookAddController = loader.load();

        // bookAddPageController loginController = loader.getController();
        // loginController.setParentController(this);

        // Stage stage = new Stage();
        // stage.centerOnScreen();
        // stage.setAlwaysOnTop(true);
        // stage.setResizable(false);
        // stage.setScene(new Scene(bookAddController));
        // stage.setTitle("Add Book");
        // stage.show();
        // System.out.println(stage.getHeight());
        // System.out.println(stage.getWidth());

        // } catch (Exception e) {
        // e.printStackTrace();
        // }

    }

    @FXML
    void handleDeleteButtonEvent(MouseEvent event) {
        // pop up ile emin misin diye sorup silecek
    }

    @FXML
    void handleUpdateButtonEvent(MouseEvent event) {
        // update sayfasini acacak
    }

    public void syncBookTable() {
        books = crud.listCachedBooks();
        observableBooks = FXCollections.observableArrayList(books);
        bookTableView.setItems(observableBooks);
    }

    public void syncBoth() {
        syncBookTable();
        parentController.syncBookTable();
    }

    private void loadTableData() {
        if (books == null || books.isEmpty()) {
            bookTableView.getItems().clear();
            return;
        }

        syncBookTable();
    }

    public void setParentController(Controller controller) {
        parentController = controller;
    }
}
