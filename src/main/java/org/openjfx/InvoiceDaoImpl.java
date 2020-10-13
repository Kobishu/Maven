package org.openjfx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InvoiceDaoImpl implements InvoiceDao{
    private List<Invoice> invoiceList;

    //Connection
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/Invoices";
    public static final String USER = "root";
    public static final String PW = "1234";


    public static Connection dbConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PW);
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
        return con;
    }

    @Override
    public Invoice extractInvoice(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice(null,null,0,false);
        invoice.setId(rs.getInt("id"));
        invoice.setDate(rs.getDate("date"));
        invoice.setDescription(rs.getString("description"));
        invoice.setValue(rs.getInt("value"));
        invoice.setPaid(rs.getBoolean("paitd"));

        return invoice;
    }

    //CRUD Methoden vom Interface ausimplementiert
    @Override
    public List<Invoice> showInvoices() {
       Connection con = InvoiceDaoImpl.dbConnection();
       try{
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT * FROM Invoice");
           while(rs.next()){
               System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getBoolean(5));
           }
       }
       catch (SQLException e){
           System.out.println(e);
       }
       return null;

    }

    @Override
    public boolean updateInvoice(int id, Date date, String description, int value, boolean paid) {
        Connection con = InvoiceDaoImpl.dbConnection();
        try{
            Statement stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement("UPDATE Invoice SET date=?, description=?,value=?,paid=? WHERE id=?");
            ps.setDate(1,date);
            ps.setString(2,description);
            ps.setInt(3,value);
            ps.setBoolean(4,paid);
            ps.setInt(5,id);
            int i = ps.executeUpdate();

            if(i==1){
                return true;
            }
            }
            catch (SQLException e){
                System.out.println(e);
            }
            return false;
    }

    @Override
    public boolean deleteInvoice(int id) {
        Connection con = InvoiceDaoImpl.dbConnection();
        try{
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate("DELETE FROM Invoice Where id="+id);

            if(i==1){
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void insertInvoice(Invoice i) {
        Connection con = InvoiceDaoImpl.dbConnection();
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO Invoice (date,description,value,paid) VALUES (?,?,?,?)");
            ps.setDate(1,i.getDate());
            ps.setString(2,i.getDescription());
            ps.setInt(3,i.getValue());
            ps.setBoolean(4,i.isPaid());
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Invoice> getInvoices(int id) {
        Connection con = InvoiceDaoImpl.dbConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Invoice");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return invoiceList;
    }
}
