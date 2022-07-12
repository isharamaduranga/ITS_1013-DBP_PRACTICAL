package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NavigationUtil {

    public static void setUi(String URI) throws IOException {
        Parent parent = FXMLLoader.load(NavigationUtil.class.getResource("../view/" + URI + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void setUiToCloseCurrentWindow(AnchorPane ap, String location, String title) throws IOException {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(NavigationUtil.class.getResource("../view/" + location + ".fxml"))));
        stage.setTitle(title);
        stage.centerOnScreen();
        stage.show();
    }

    public static void setUiChildren(AnchorPane ap, String location) throws IOException {
        ap.getChildren().clear();
        Parent parent = FXMLLoader.load(NavigationUtil.class.getResource("../view/" + location + ".fxml"));
        ap.getChildren().add(parent);
    }

    public static void setUiChildren(AnchorPane ap, Parent parent) throws IOException {
        ap.getChildren().clear();
        ap.getChildren().add(parent);
    }

    public static void setUiNew(String location, String title) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(NavigationUtil.class.getResource("../view/" + location + ".fxml"))));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle(title);
        stage.show();
    }

    public static void close(AnchorPane ap) {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

}