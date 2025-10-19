# Réponses au TP MVVM JavaFX

## Q0 : Écrans disponibles
Les deux écrans sont Login et Todo.

## Q1 : Packages
- View : com.example.exercice.view
- ViewModel : com.example.exercice.viewmodel
- Model : com.example.exercice.model

## Q2 : canLoginProperty()
La propriété `canLoginProperty()` est utilisée pour activer/désactiver le bouton de connexion en fonction de la validité des champs `username` et `password`.

## Q3 : Utilisation dans LoginView
Elle est liée au bouton de connexion dans `LoginView.java` via :

`btnLogin.disableProperty().bind(vm.canLoginProperty().not());`


# Partie 2 — Analyse de l’écran Todo

## Q4 : Rôle de `FilteredList<Todo>` dans le ViewModel
La classe `FilteredList<Todo>` permet de **filtrer dynamiquement** la liste des tâches en fonction d’un prédicat (par exemple, afficher uniquement les tâches non terminées). Elle est utilisée pour mettre à jour automatiquement la liste affichée dans la vue lorsque les données ou le prédicat changent.

## Q5 : Propriété `showDone`
- **Rôle** : `showDone` est une propriété booléenne (`BooleanProperty`) qui détermine si les tâches terminées doivent être affichées.
- **Lien avec la Vue** : Elle est généralement reliée à une case à cocher dans la vue (`TodoView.fxml` ou `TodoView.java`). Lorsqu’elle change, elle met à jour le prédicat du `FilteredList<Todo>`.


 

