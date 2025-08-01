module com.example.tic_tac_toc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tic_tac_toc to javafx.fxml;
    exports com.example.tic_tac_toc;
}