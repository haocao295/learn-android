package com.dh51804107.dat_hang.model;

import java.io.Serializable;

import com.dh51804107.dat_hang.util.FormatUtil;

public class Mathang implements Serializable {
    private  String ten;
    private int dongia;
    private int soluong;

    public Mathang() {
    }

    public Mathang(String ten, int dongia, int soluong) {
        this.ten = ten;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "Tên hàng : " + ten + "\n" +
                "Đơn giá : " + FormatUtil.formatNumber(dongia) +"\n"+
                "Số lượng : " + FormatUtil.formatNumber(soluong) +"\n"+
                "Thành tiền : " + FormatUtil.formatNumber((soluong * dongia));

    }
}
