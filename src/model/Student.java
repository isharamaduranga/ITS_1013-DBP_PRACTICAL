/**
 * @author : Ishara Maduarnga
 * Project Name: ITS_1013-DBP_PRACTICAL
 * Date        : 7/12/2022
 * Time        : 10:50 AM
 * Year        : 2022
 */

package model;

public class Student {
    private String student_id;
    private String student_name;
    private String email;
    private String contact;
    private String address;
    private String nic;

    public Student() {
    }

    public Student(String student_id, String student_name, String email, String contact, String address, String nic) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.nic = nic;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id='" + student_id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
