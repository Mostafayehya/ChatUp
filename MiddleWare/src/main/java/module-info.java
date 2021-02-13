module MiddleWare {
    exports services;
    exports domains;

    requires java.rmi;
    requires java.sql;
    opens services to java.rmi;
    opens domains to java.rmi;
}