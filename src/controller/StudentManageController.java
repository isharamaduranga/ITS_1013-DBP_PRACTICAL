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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Student;
import util.CrudUtil;
import util.ValidationUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

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

    /** Define Linked-HashMap for the validation  */
    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize(){

        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);


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
        /** create validation pattern*/
        //Create a pattern and compile it to use
        Pattern idPattern = Pattern.compile("^(ST)[0-9]{3,5}$");
        Pattern namePattern = Pattern.compile("^[A-z]{3,20}$");
        Pattern EmailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Pattern contactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9}$");
        Pattern AddressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern nicPattern = Pattern.compile("^[A-z0-9]{4,20}$");


        //add pattern and text to the map
        map.put(txtStudentId,idPattern);
        map.put(txtStudentName,namePattern);
        map.put(txtEmail,EmailPattern);
        map.put(txtContact,contactPattern);
        map.put(txtSAddress,AddressPattern);
        map.put(txtNic,nicPattern);

    }

    private void setData(Student s) {
        txtStudentId.setText(s.getStudent_id());
        txtStudentName.setText(s.getStudent_name());
        txtEmail.setText(s.getEmail());
        txtContact.setText(s.getContact());
        txtSAddress.setText(s.getAddress());
        txtNic.setText(s.getNic());
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
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
    /** Define the function of cross to among the textFields(use Enter press)  */
    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAdd);

        if (keyEvent.getCode()== KeyCode.ENTER){
            Object response = ValidationUtil.validate(map,btnAdd);
            if (response instanceof JFXTextField){
                JFXTextField textField = (JFXTextField) response;
                textField.requestFocus();
            }else if (response instanceof Boolean){

            }
        }
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

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblStudent.refresh();
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
