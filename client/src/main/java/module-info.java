module me.siyum.client {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens me.siyum.client to javafx.fxml;
    exports me.siyum.client;
}