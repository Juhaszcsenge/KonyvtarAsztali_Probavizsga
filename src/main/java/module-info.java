module com.example.konyvtarasztali_probavizsga {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens hu.petrik.konyvtarasztali_probavizsga to javafx.fxml;
    exports hu.petrik.konyvtarasztali_probavizsga;
}