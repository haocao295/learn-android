package vn.edu.stu.qlynhanvien.model;

import java.io.Serializable;
import java.util.Date;

import vn.edu.stu.qlynhanvien.util.FormatUtil;

public class Congviec implements Serializable {
    private String ten;
    private Date han;

    public Congviec() {
    }

    public Congviec(String ten, Date han) {
        this.ten = ten;
        this.han = han;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getHan() {
        return han;
    }

    public void setHan(Date han) {
        this.han = han;
    }

    @Override
    public String toString() {
        return ten + '-' + FormatUtil.formatDatetime(this.han);
    }
}