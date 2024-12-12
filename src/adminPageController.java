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

    // ekleme editleme kismi
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
    void handleAddButtonClick(MouseEvent event) {

        try {

            crud.createBook(nametext.getText(),
                    writertext.getText(),
                    genretext.getText(),
                    Integer.parseInt(pagetext.getText()),
                    Integer.parseInt(yeartext.getText()),
                    Integer.parseInt(stocktext.getText()));

            syncBookTable();
            parentController.syncBookTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private void syncBookTable() {
        books = crud.listCachedBooks();
        observableBooks = FXCollections.observableArrayList(books);
        bookTableView.setItems(observableBooks);
    }

    private void loadTableData() {
        if (books == null || books.isEmpty()) {
            bookTableView.getItems().clear();
            return;
        }

        syncBookTable();
    }

    public void setParentController(Controller controller){
        parentController = controller;
    }
}
