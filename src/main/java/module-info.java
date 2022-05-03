module pl.polsl.michal.slusarczyk.sapper {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.polsl.michal.slusarczyk.sapper to javafx.fxml;
    exports pl.polsl.michal.slusarczyk.sapper;
}
