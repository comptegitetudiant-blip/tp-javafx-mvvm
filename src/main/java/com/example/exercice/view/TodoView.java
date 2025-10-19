package com.example.exercice.view;

import com.example.exercice.viewmodel.TodoViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.beans.binding.Bindings;
import java.io.IOException;

public class TodoView {
    @FXML private TextField txtNew;
    @FXML private Button btnAdd;
    @FXML private ListView lstTodos;
    @FXML private CheckBox chkShowDone;
    @FXML private Button btnDelete;
    @FXML private Label lblNewTitlePreview; // Ajout pour l'Exercice 3

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
        lstTodos.itemsProperty().bind(Bindings.createObjectBinding(() -> vm.items(), vm.showDoneProperty()));
        chkShowDone.selectedProperty().bindBidirectional(vm.showDoneProperty());
        btnDelete.disableProperty().bind(vm.canDeleteProperty().not());

        // Ajout pour l'Exercice 3 : Lier le Label à la propriété newTitle
        lblNewTitlePreview.textProperty().bind(
                Bindings.concat("Vous allez ajouter : ", vm.newTitleProperty())
        );
    }
}
