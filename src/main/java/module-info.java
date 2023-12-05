module com.edisongrauman.lightcontrol {
    requires javafx.controls;
    requires artnet4j;
    requires commons.validator;
    requires com.google.gson;
    requires org.controlsfx.controls;
    exports com.edisongrauman.lightcontrol;
    
    opens com.edisongrauman.lightcontrol to com.google.gson;

}
