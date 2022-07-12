/**
 * @author : Ishara Maduarnga
 * Project Name: ITS_1013-DBP_PRACTICAL
 * Date        : 7/11/2022
 * Time        : 11:03 PM
 * Year        : 2022
 */

package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.NavigationUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardMainController {
    public BorderPane FullBoardContext;
    public AnchorPane dashBoardContext;
    public Label lblDate;
    public Label lblDay;
    public Label lblTime;

    public void initialize(){
        loadTimeDate();
    }

    private void loadTimeDate() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {

            LocalTime currentTime = LocalTime.now();

            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());

            lblDay.setText(LocalDate.now().getDayOfWeek().toString());
        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void logOutOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void shutDownOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardMain.fxml"))));
    }

    public void manageStudentOnAction(ActionEvent actionEvent) throws IOException {
        NavigationUtil.setUiChildren(dashBoardContext, "StudentManage");
    }
}
