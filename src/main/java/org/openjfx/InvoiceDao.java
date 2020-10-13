package org.openjfx;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InvoiceDao {

    List<Invoice> showInvoices();

    //Methoden für Implementierungen
    Invoice extractInvoice(ResultSet rs) throws SQLException;
    List<Invoice> getInvoices(int id);
    boolean updateInvoice(int id, Date date, String description, int value, boolean paid);
    boolean deleteInvoice(int id);
    void insertInvoice(Invoice i);
}
