module MiddleWare {
    exports services;
    exports domains;

    requires java.rmi;
    opens services to java.rmi;
    opens domains to java.rmi;
}