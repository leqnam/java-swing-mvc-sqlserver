/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Me
 */
public class sanpham {

    private String MaSP;// char(4) primary key, 
    private String TenSP;// nvarchar(20), 
    private int Dongia;// bigint, 
    private String Maloai;// char(2) foreign key references  LoaiSP(Maloai) 
    
    public sanpham() {
    }

    public sanpham(String MaSP, String TenSP, int Dongia, String Maloai) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.Dongia = Dongia;
        this.Maloai = Maloai;
    }

    public int getDongia() {
        return Dongia;
    }

    public void setDongia(int Dongia) {
        this.Dongia = Dongia;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getMaloai() {
        return Maloai;
    }

    public void setMaloai(String Maloai) {
        this.Maloai = Maloai;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }
}
