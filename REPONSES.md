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

---

# Partie 3 — Liens View ↔ ViewModel

## Q6 : Différence entre `bind(...)` et `bindBidirectional(...)`
- **`bind(...)`** : Crée un **lien unidirectionnel**. La cible est mise à jour lorsque la source change, mais pas l’inverse.
- **`bindBidirectional(...)`** : Crée un **lien bidirectionnel**. La cible et la source se mettent à jour mutuellement.

## Q7 : Pourquoi utilise-t-on `bindBidirectional` ici ?
On utilise `bindBidirectional` pour **synchroniser** la propriété `newTitle` du ViewModel avec le champ de texte (`TextField`) de la vue. Cela permet :
- À l’utilisateur de saisir un titre dans le champ de texte, ce qui met à jour `newTitle` dans le ViewModel.
- Au ViewModel de mettre à jour le champ de texte si `newTitle` change programmatiquement.
 
---

# Partie 4 — Évolution de la Todo
## Q8 : Pourquoi utilise-t-on CheckBoxTableCell pour la colonne "Fait" ?

- On utilise `CheckBoxTableCell` pour la colonne "Fait" car cela permet d'afficher une case à cocher (`CheckBox`) dans chaque cellule de la colonne. Cela facilite la modification de l'état "fait/non fait" d'une tâche directement depuis la vue, en liant visuellement et fonctionnellement la propriété `done` de chaque objet `Todo` à une case à cocher.

---

# Partie 5 — Amélioration du Login

## Q9 : Navigation entre l’écran Login et l’écran Todo
La navigation entre les écrans Login et Todo est gérée par la classe `Navigator`. Voici comment cela fonctionne :

1. **Initialisation du `Navigator`** : Dans la méthode `start` de la classe `App`, le `Navigator` est initialisé avec le `Stage` principal de l'application.
2. **Changement de scène** : La méthode `goTo` de `Navigator` est utilisée pour changer la scène affichée par le `Stage` principal.
3. **Mécanisme de navigation** : Le `Navigator` utilise une instance statique de `Stage` pour changer la scène affichée.
4. **Navigation depuis le LoginViewModel** : Lorsqu'un utilisateur se connecte avec succès, le `LoginViewModel` appelle la méthode `setOnSuccess`, qui crée une nouvelle scène pour l'écran Todo et utilise `Navigator.goTo` pour afficher cette scène.
