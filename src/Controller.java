import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import CrudHelper.crudHandler;
import TxtHandler.Book;

public class Controller {

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
        var books = crud.listBooks();

        for (Book book : books) {
            System.out.println(book.toString());
        }


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


}
