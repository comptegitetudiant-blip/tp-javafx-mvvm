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
```java
btnLogin.disableProperty().bind(vm.canLoginProperty().not());
