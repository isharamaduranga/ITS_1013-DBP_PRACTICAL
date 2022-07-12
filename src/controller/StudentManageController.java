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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Student;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManageController {

    public TableView<Student> tblStudent;
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

    public void initialize(){


        colStudentID.setCellValueFactory(new PropertyValueFactory("student_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory("student_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colContact.setCellValueFactory(new PropertyValueFactory("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colNic.setCellValueFactory(new PropertyValueFactory("nic"));

        try{
            tableLoad();
            tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue!=null){
                    setData(newValue);

                }
            });
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }


    }

    private void setData(Student s) {


    }

    private void tableLoad() {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM student");
            ObservableList<Student> observableList = FXCollections.observableArrayList();

            while (result.next()){
                observableList.add(new Student(
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                ));
            }

            tblStudent.setItems(observableList);
            FilteredList<Student> filterData = new FilteredList<Student>(observableList, b -> true);

            txtSearchStudentId.textProperty().addListener((observable, oldValue, newValue) -> {
                filterData.setPredicate(student -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (student.getStudent_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else
                        return false;
                });
            });

            SortedList<Student> sortedData = new SortedList<>(filterData);
            sortedData.comparatorProperty().bind(tblStudent.comparatorProperty());
            tblStudent.setItems(sortedData);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }

    public void AddStudentOnAction(ActionEvent actionEvent) {

        Student s = new Student(txtStudentId.getText(), txtStudentName.getText(),
                txtEmail.getText(), txtContact.getText(), txtSAddress.getText(), txtNic.getText());

        try {
            if(CrudUtil.execute("INSERT INTO student VALUES(?,?,?,?,?,?)",s.getStudent_id(),s.getStudent_name(),
                    s.getEmail(),s.getContact(),s.getAddress(),s.getNic())){

                new Alert(Alert.AlertType.CONFIRMATION,"Saved...").showAndWait();

            }else{
                new Alert(Alert.AlertType.WARNING,"Student Already exists !!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearFields();
        tableLoad();
    }

    private void clearFields() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtEmail.clear();
        txtContact.clear();
        txtSAddress.clear();
        txtNic.clear();
    }

    public void UpdateStudentOnAction(ActionEvent actionEvent) {
        Student s = new Student(txtStudentId.getText(), txtStudentName.getText(),
                txtEmail.getText(), txtContact.getText(), txtSAddress.getText(), txtNic.getText());

        try {
            if(CrudUtil.execute("UPDATE student SET student_name=?,email=?,contact=?,address=? ,nic=? WHERE student_id=?"
                    ,s.getStudent_name(),s.getEmail(),s.getContact(),s.getAddress(),s.getNic(),s.getStudent_id())){

                new Alert(Alert.AlertType.CONFIRMATION,"Updated!").showAndWait();

            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tableLoad();
        clearFields();
    }

    public void DeleteStudentOnAction(ActionEvent actionEvent) {
        try{

            if(CrudUtil.execute("DELETE FROM Student WHERE student_id =?",txtStudentId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").showAndWait();
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again!").show();
            }
        }catch (ClassNotFoundException | SQLException |NullPointerException  e){
            e.printStackTrace();
        }
        tableLoad();
      clearFields();

    }

    public void btnClearFieldsOnAction(ActionEvent actionEvent) {
        clearFields();
    }

}
