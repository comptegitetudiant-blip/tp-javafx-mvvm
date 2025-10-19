package com.example.exercice.view;

import com.example.exercice.model.Todo;
import com.example.exercice.viewmodel.TodoViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.binding.Bindings;
import java.io.IOException;

public class TodoView {
    @FXML private TextField txtNew;
    @FXML private Button btnAdd;
    @FXML private TableView<Todo> tblTodos;
    @FXML private CheckBox chkShowDone;
    @FXML private Button btnDelete;
    @FXML private Label lblNewTitlePreview;
    @FXML private Label lblWelcome;

    private final TodoViewModel vm;
    private final Parent root;

    public TodoView(TodoViewModel vm) throws IOException {
        this.vm = vm;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TodoView.fxml"));
        loader.setController(this);
        this.root = loader.load();
    }

    public Parent getRoot() {
        return root;
    }

    @FXML
    private void initialize() {
        txtNew.textProperty().bindBidirectional(vm.newTitleProperty());
        btnAdd.disableProperty().bind(vm.canAddProperty().not());
        tblTodos.setItems(vm.items());

        TableColumn<Todo, String> titleColumn = new TableColumn<>("Titre");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Todo, Boolean> doneColumn = new TableColumn<>("Fait");
        doneColumn.setCellValueFactory(new PropertyValueFactory<>("done"));
        doneColumn.setCellFactory(CheckBoxTableCell.forTableColumn(doneColumn));

        TableColumn<Todo, Number> lengthColumn = new TableColumn<>("Longueur");
        lengthColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getTitle().length()).asObject()
        );

        tblTodos.getColumns().addAll(titleColumn, doneColumn, lengthColumn);

        chkShowDone.selectedProperty().bindBidirectional(vm.showDoneProperty());
        btnDelete.disableProperty().bind(vm.canDeleteProperty().not());

        lblNewTitlePreview.textProperty().bind(
                Bindings.concat("Vous allez ajouter : ", vm.newTitleProperty())
        );

        lblWelcome.textProperty().bind(
                Bindings.concat("Bienvenue, ", vm.userNameProperty())
        );
    }
}
