package com.example.exercice.view;

import com.example.exercice.model.Todo;
import com.example.exercice.viewmodel.TodoViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import java.util.Objects;

public class TodoView {
    @FXML private TextField txtNew;
    @FXML private Button btnAdd, btnDel;
    @FXML private CheckBox chkShowDone;
    @FXML private TableView<Todo> table;
    @FXML private TableColumn<Todo, String> colTitle;
    @FXML private TableColumn<Todo, Boolean> colDone;
    private final TodoViewModel vm;
    private final Parent root;
    public TodoView(TodoViewModel vm) throws Exception {
        this.vm = vm;
        FXMLLoader l = new FXMLLoader(Objects.requireNonNull(
                getClass().getResource("/view/TodoView.fxml"),
                "FXML /view/TodoView.fxml introuvable"));
        l.setController(this);
        this.root = l.load();
    }
    public Parent getRoot(){ return root; }
    @FXML private void initialize(){
        table.setEditable(true);
        colDone.setEditable(true);
        table.setItems(vm.items());
        colTitle.setCellValueFactory(cd -> cd.getValue().titleProperty());
        colTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        colDone.setCellValueFactory(cd -> cd.getValue().doneProperty());
        colDone.setCellFactory(CheckBoxTableCell.forTableColumn(colDone));
        txtNew.textProperty().bindBidirectional(vm.newTitleProperty());
        chkShowDone.selectedProperty().bindBidirectional(vm.showDoneProperty());
        btnAdd.setOnAction(e -> vm.add());
        btnDel.setOnAction(e -> {
            vm.selectedProperty().set(table.getSelectionModel().getSelectedItem());
            vm.deleteSelected();
            table.getSelectionModel().clearSelection();
        });
    }
}
