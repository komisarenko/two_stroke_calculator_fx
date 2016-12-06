package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controller.Controller;
import main.model.entity.Engine;
import org.hibernate.Session;

public class Main extends Application {

    Controller controller = new Controller();
    static Session session;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/main_beta.fxml"));
        primaryStage.setTitle("Two Stroke Calc");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {Platform.exit(); System.exit(0);});
    }


    public static void main(String[] args) {
        session = HibernateUtil.getSessionFactory().openSession();
        launch(args);
    }

    public static Session getSession() {
        return session;
    }
}

