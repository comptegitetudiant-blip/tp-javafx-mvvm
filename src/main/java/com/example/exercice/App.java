package com.example.exercice;

import com.example.exercice.auth.AuthService;
import com.example.exercice.auth.User;
import com.example.exercice.model.Todo;
import com.example.exercice.repo.TodoRepository;
import com.example.exercice.view.LoginView;
import com.example.exercice.view.TodoView;
import com.example.exercice.viewmodel.LoginViewModel;
import com.example.exercice.viewmodel.TodoViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Navigator.init(stage);
        var auth = new AuthService();
        var loginVM = new LoginViewModel(auth);
        var loginView = new LoginView(loginVM);

        loginVM.setOnSuccess(user -> {
            try {
                var repo = new TodoRepository();
                repo.add(new Todo("1", "Acheter du lait", false, LocalDate.now().plusDays(1)));
                repo.add(new Todo("2", "Payer loyer", false, LocalDate.now().plusDays(7)));
                var todoVM = new TodoViewModel(repo, user.username());
                var todoView = new TodoView(todoVM);
                Navigator.goTo(new Scene(todoView.getRoot(), 640, 480), "Todo — " + user.username());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Navigator.goTo(new Scene(loginView.getRoot(), 420, 260), "Connexion");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
