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

import java.util.ArrayList;

import CrudHelper.crudHandler;
import TxtHandler.Book;

public class Controller {

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
    private final ArrayList<Book> books = crud.listBooks();

    @FXML
    void initialize() {
        // Sütunlara veri bağlama
        namecol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        writercol.setCellValueFactory(new PropertyValueFactory<>("writer"));
        genrecol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        pagecol.setCellValueFactory(new PropertyValueFactory<>("page"));
        yearcol.setCellValueFactory(new PropertyValueFactory<>("writtenYear"));
        stockcol.setCellValueFactory(new PropertyValueFactory<>("count"));

        // Tabloyu doldur
        loadTableData();
    }

    private void loadTableData() {
        ArrayList<Book> books = crud.listBooks();

        if (books == null || books.isEmpty()) {
            bookTableView.getItems().clear();
            return;
        }

        ObservableList<Book> bookData = FXCollections.observableArrayList(books);
        bookTableView.setItems(bookData);
    }

    @FXML
    void addBookName(MouseEvent event) {

    }

    @FXML
    void searchingEvent(KeyEvent event) {
        // finding the related books

        ObservableList<Book> bookSearch;

        ArrayList<Book> foo = new ArrayList<Book>();

        for (Book book : books) {
            if (book.getBookName().startsWith(mytext.getText())) {
                foo.add(book);
            }

        }

        bookSearch = FXCollections.observableArrayList(foo);

        bookTableView.setItems(bookSearch);

    }

}
