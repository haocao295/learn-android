package vn.edu.stu.student;



import java.io.Serializable;

public class student implements Serializable {
    private String msv;
    private  String hoTen;
    private int namSinh;
    public student (String msv, String hoTen, int namSinh){
        this.msv= msv;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }
    public  String toString(){
        return "Mã sinh viên : " + msv + "\nHọ và tên : " +hoTen + "\n Năm sinh : "+ namSinh;
    }
}

