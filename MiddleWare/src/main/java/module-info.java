module MiddleWare {
    exports services;
    exports domains;
    exports clientInterface;
    requires java.rmi;
    requires java.sql;
    requires java.desktop;
    opens services to java.rmi;
    opens domains to java.rmi;
    opens clientInterface to java.rmi;
    requires javafx.controls;

}