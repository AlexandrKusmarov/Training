package main.java;

public class DemoTransportCompany {
    public static void main(String[] args) {
        ArrayCollectionImplApp app = new ArrayCollectionImplApp();
        app.createCargos();
        app.createCarriers();
        app.createTransportations();
        app.fillArrStorage();
        app.fillCollectionStorage();
        app.testingCollectionsMethods();
        app.testingArraysMethods();
    }
}
