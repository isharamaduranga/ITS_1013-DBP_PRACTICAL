/**
 * @author : Ishara Maduarnga
 * Project Name: ITS_1013-DBP_PRACTICAL
 * Date        : 7/12/2022
 * Time        : 10:05 AM
 * Year        : 2022
 */

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class StudentManageController {
    public TableView tblStudent;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colNic;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtEmail;
    public JFXTextField txtContact;
    public JFXTextField txtSAddress;
    public JFXTextField txtNic;
    public JFXTextField txtSearchStudentId;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton btnClear;

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }

    public void AddStudentOnAction(ActionEvent actionEvent) {
    }

    public void UpdateStudentOnAction(ActionEvent actionEvent) {
    }

    public void DeleteStudentOnAction(ActionEvent actionEvent) {
    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
    }

    public void btnMouseMovedOnAction(MouseEvent mouseEvent) {
    }
}
