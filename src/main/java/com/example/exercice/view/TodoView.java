package com.example.exercice.view;

import com.example.exercice.Navigator;
import com.example.exercice.auth.AuthService;
import com.example.exercice.model.Todo;
import com.example.exercice.view.LoginView;
import com.example.exercice.viewmodel.LoginViewModel;
import com.example.exercice.viewmodel.TodoViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.binding.Bindings;
import java.io.IOException;
import java.time.LocalDate;

public class TodoView {
    @FXML private TextField txtNew;
    @FXML private Button btnAdd;
    @FXML private TableView<Todo> tblTodos;
    @FXML private CheckBox chkShowDone;
    @FXML private Button btnDelete;
    @FXML private Button btnDeleteAll;
    @FXML private Button btnLogout;
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
        chkShowDone.selectedProperty().bindBidirectional(vm.showDoneProperty());
        btnDelete.disableProperty().bind(vm.canDeleteProperty().not());

        lblNewTitlePreview.textProperty().bind(
                Bindings.concat("Vous allez ajouter : ", vm.newTitleProperty())
        );

        lblWelcome.textProperty().bind(
                Bindings.concat("Bienvenue, ", vm.userNameProperty())
        );

        btnDeleteAll.setOnAction(e -> vm.deleteAll());

        btnLogout.setOnAction(e -> {
            try {
                var loginVM = new LoginViewModel(new AuthService());
                var loginView = new LoginView(loginVM);
                Navigator.goTo(new Scene(loginView.getRoot(), 420, 260), "Connexion");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
