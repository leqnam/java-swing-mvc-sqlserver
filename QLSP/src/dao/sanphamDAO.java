/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.loaisp;
import model.sanpham;

/**
 *
 * @author toi
 */
public class sanphamDAO {

    public ArrayList<loaisp> getListLoai() throws SQLException {

        Connection connection = connectDB.getConnection();
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM LoaiSP");
        ArrayList<loaisp> list = new ArrayList<loaisp>();
        while (rs.next()) {
            loaisp loai = new loaisp();
            loai.setMaloai(rs.getString("Maloai"));
            loai.setTenloai(rs.getString("Tenloai"));
            list.add(loai);
        }
        connection.close();
        return list;
    }

    public String tenLoai(String ID) throws SQLException {
        Connection connection = connectDB.getConnection();
        String sql = "SELECT\n"
                + "dbo.LoaiSP.Tenloai as tenloai\n"
                + "FROM\n"
                + "dbo.LoaiSP\n"
                + "WHERE\n"
                + "dbo.LoaiSP.Maloai = ?";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setString(1, ID);
        ResultSet rs = ps.executeQuery();
        //ps.executeUpdate();
        String ten = null;
        while (rs.next()) {
            ten = rs.getString("Tenloai");
        }
        connection.close();
        return ten;
    }

    public ArrayList<sanpham> getListSanPham() throws SQLException {
        Connection connection = connectDB.getConnection();
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM Sanpham");
        ArrayList<sanpham> list = new ArrayList<sanpham>();
        while (rs.next()) {
            sanpham sp = new sanpham();
            sp.setMaSP(rs.getString("MaSP"));
            sp.setTenSP(rs.getString("TenSP"));
            sp.setDongia(rs.getInt("Dongia"));
            sp.setMaloai(rs.getString("Maloai"));
            list.add(sp);
        }
        connection.close();
        return list;
    }
    
    public ArrayList<sanpham> getTrang(long trang) throws SQLException {
        Connection connection = connectDB.getConnection();
        String sql = "SELECT top 5 * FROM Sanpham \n"
                + "WHERE TenSP not in (SELECT top " + trang + " TenSP from Sanpham)";
        PreparedStatement ps = connection.prepareCall(sql);
        //ps.setLong(1, trang);
        ResultSet rs = ps.executeQuery();
        ArrayList<sanpham> list = new ArrayList<sanpham>();
        while (rs.next()) {
            sanpham sp = new sanpham();
            sp.setMaSP(rs.getString("MaSP"));
            sp.setTenSP(rs.getString("TenSP"));
            sp.setDongia(rs.getInt("Dongia"));
            sp.setMaloai(rs.getString("Maloai"));
            list.add(sp);
        }
        connection.close();
        return list;
    }

    public void deleteSP(String ID) throws SQLException, ClassNotFoundException {
        Connection connection = connectDB.getConnection();
        String sql = "Delete from Sanpham where MaSP=?";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setString(1, ID);
        ps.executeUpdate();
        connection.close();
    }

    public long chekid(String ID) throws SQLException {
        Connection connection = connectDB.getConnection();
        String sql = "Select count(*) as sl from Sanpham where MaSP=?";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setString(1, ID);
        ResultSet rs = ps.executeQuery();
        long count = 0;
        while (rs.next()) {
            count = rs.getLong("sl");
        }
        connection.close();
        return count;
    }

    public sanpham addNew(sanpham sp) throws SQLException {
        Connection connection = connectDB.getConnection();
        String sql = "INSERT INTO Sanpham VALUES(?,?,?,?)";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setString(1, sp.getMaSP());
        ps.setString(2, sp.getTenSP());
        ps.setLong(3, sp.getDongia());
        ps.setString(4, sp.getMaloai());
        ps.executeUpdate();
        connection.close();
        return sp;
    }
    
    public sanpham update(sanpham sp) throws SQLException {
        Connection connection = connectDB.getConnection();
        String sql = "Update Sanpham set TenSP=?,Dongia=?,Maloai=? Where MaSP=?";
        PreparedStatement ps = connection.prepareCall(sql);
        ps.setString(4, sp.getMaSP());
        ps.setString(1, sp.getTenSP());
        ps.setLong(2, sp.getDongia());
        ps.setString(3, sp.getMaloai());
        ps.executeUpdate();
        connection.close();
        return sp;
    }
}
