package org.openjfx;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


    //Verbindung testen
    Connection con = InvoiceDaoImpl.dbConnection();
    InvoiceDaoImpl impl = new InvoiceDaoImpl();

    //CRUD
    impl.showInvoices();
    impl.deleteInvoice(3);
    impl.insertInvoice(new Invoice(Date.valueOf("2020-05-19"),"Kartoffel",1,true));
    impl.updateInvoice(1, Date.valueOf("2020-06-17"),"Salat",5,true);

    }
}
