import TxtHandler.Book;
import CrudHelper.crudHandler;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class adminPageController {

    // kitap ekleme sayfasi
    @FXML
    private Label badInputAlert;

    @FXML
    private Button addBookButton;

    @FXML
    private TextField addNameText;

    @FXML
    private TextField addGenreText;

    @FXML
    private TextField addPageText;

    @FXML
    private TextField addStockText;

    @FXML
    private TextField addWriterText;

    @FXML
    private TextField addYearText;

    // kitap silme degistirme sayfasi

    @FXML
    private TextField updateGenre;

    @FXML
    private TextField updateName;

    @FXML
    private TextField updatePage;

    @FXML
    private TextField updateStock;

    @FXML
    private TextField updateWriter;

    @FXML
    private TextField updateYear;

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

    private Book selectedBook;

    private final crudHandler crud = crudHandler.getInstance();
    private ArrayList<Book> books = crud.book_list;
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

            selectedBook = bookTableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                updateName.setText(selectedBook.getBookName());
                updateGenre.setText(selectedBook.getGenre());
                updateWriter.setText(selectedBook.getWriter());
                updatePage.setText(String.valueOf(selectedBook.getPage()));
                updateStock.setText(String.valueOf(selectedBook.getCount()));
                updateYear.setText(String.valueOf(selectedBook.getWrittenYear()));
                deleteBookButton.setDisable(false);
                deleteBookButton.setOpacity(1);
                updateBookButton.setDisable(false);
                updateBookButton.setOpacity(1);
            }
        } else {

            updateName.clear();
            updateGenre.clear();
            updateWriter.clear();
            updatePage.clear();
            updateStock.clear();
            updateYear.clear();

            this.selectedBook = null;
            deleteBookButton.setDisable(true);
            deleteBookButton.setOpacity(0.4);
            updateBookButton.setDisable(true);
            updateBookButton.setOpacity(0.4);


        }
    }

    @FXML
    void handleDeleteButtonEvent(MouseEvent event) {
        if (selectedBook != null) {
            crud.deleteBook(selectedBook);
            syncBoth();
            selectedBook = null;
        }
    }


    @FXML
    void handleUpdateButtonEvent(MouseEvent event) {
        if (selectedBook != null) {
            String name = updateName.getText().trim();
            String genre = updateGenre.getText().trim();
            String writer = updateWriter.getText().trim();
            String pageText = updatePage.getText().trim();
            String stockText = updateStock.getText().trim();
            String yearText = updateYear.getText().trim();

            try {
                int page = Integer.parseInt(pageText);
                int stock = Integer.parseInt(stockText);
                int year = Integer.parseInt(yearText);

                crud.updateBook(selectedBook, name, writer, genre, page, year, stock);
                selectedBook = null;
                syncBoth();

            } catch (NumberFormatException e) {
                // not handling much
            } catch (Exception e) {
                // not handling much
            }
        }
    }

    // add textleri kontrolleri

    @FXML
    void handleAddButtonEvent(MouseEvent event) {
        String name = addNameText.getText().trim();
        String genre = addGenreText.getText().trim();
        String writer = addWriterText.getText().trim();
        String pageText = addPageText.getText().trim();
        String stockText = addStockText.getText().trim();
        String yearText = addYearText.getText().trim();

        try {
            int page = Integer.parseInt(pageText);
            int stock = Integer.parseInt(stockText);
            int year = Integer.parseInt(yearText);

            Book book = new Book(name, writer, genre, page, year, stock);

            crud.createBook(book);
            syncBoth();

            addNameText.clear();
            addGenreText.clear();
            addWriterText.clear();
            addPageText.clear();
            addStockText.clear();
            addYearText.clear();
        } catch (NumberFormatException e) {
                // not handling much
        } catch (Exception e) {
                // not handling much
        
        }
    }

    @FXML
    void handleAddNameText(KeyEvent event) {
        validateAddButton();
    }

    @FXML
    void handleAddWriterText(KeyEvent event) {
        validateAddButton();
    }

    @FXML
    void handleAddGenreText(KeyEvent event) {
        validateAddButton();
    }

    @FXML
    void handleAddPageText(KeyEvent event) {
        validateAddButton();
    }

    @FXML
    void handleAddStockText(KeyEvent event) {
        validateAddButton();
    }

    @FXML
    void handleAddYearText(KeyEvent event) {
        validateAddButton();
    }

    // anlik java threadleri = https://www.youtube.com/shorts/Maad-U9v-pY

    private void validateAddButton() {
        boolean isNameValid = !addNameText.getText().trim().isEmpty();
        boolean isWriterValid = !addWriterText.getText().trim().isEmpty();
        boolean isGenreValid = !addGenreText.getText().trim().isEmpty();

        boolean isPageValid = isValidInteger(addPageText.getText().trim(), 1, Integer.MAX_VALUE);
        boolean isStockValid = isValidInteger(addStockText.getText().trim(), 1, Integer.MAX_VALUE);
        boolean isYearValid = isValidInteger(addYearText.getText().trim(), -2637, java.time.Year.now().getValue());

        if (isNameValid && isWriterValid && isGenreValid && isPageValid && isStockValid && isYearValid) {
            badInputAlert.setVisible(false);
            addBookButton.setDisable(false);
            addBookButton.setOpacity(1);
        } else {
            badInputAlert.setVisible(true);
            addBookButton.setDisable(true);
            addBookButton.setOpacity(0.4);
        }

    }

    private boolean isValidInteger(String text, int minValue, int maxValue) {
        try {
            int value = Integer.parseInt(text);
            return value >= minValue && value <= maxValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void syncBookTable() {
        books = crud.listCachedBooks();
        observableBooks = FXCollections.observableArrayList(books);
        bookTableView.setItems(observableBooks);
    }

    private void syncBoth() {
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
