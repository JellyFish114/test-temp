import controllers.AppController;

//TODO make it a Springbootapp
public class Main {
    public static void main(String[] args) {
        AppController app = new AppController();
        app.processAllInvoices();
        app.processTripDetails();
        app.processPaymentRequests();
    }
}
