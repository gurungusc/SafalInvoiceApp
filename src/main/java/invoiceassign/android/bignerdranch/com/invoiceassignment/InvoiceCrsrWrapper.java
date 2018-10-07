package invoiceassign.android.bignerdranch.com.invoiceassignment;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class InvoiceCrsrWrapper  extends CursorWrapper {
    public InvoiceCrsrWrapper(Cursor cursor) {
        super(cursor);    }
    public Invoice getInvoice() {
        String uuidString = getString(getColumnIndex(InvoiceDatabase.InvoiceTable.Columns.UUID));
        String title = getString(getColumnIndex(InvoiceDatabase.InvoiceTable.Columns.TITLE));
        String cmnt = getString(getColumnIndex(InvoiceDatabase.InvoiceTable.Columns.COMMENT));
        String shop = getString(getColumnIndex(InvoiceDatabase.InvoiceTable.Columns.SHOP));
        long date = getLong(getColumnIndex(InvoiceDatabase.InvoiceTable.Columns.DATE));
        int isSolved = getInt(getColumnIndex(InvoiceDatabase.InvoiceTable.Columns.SOLVED));


        Invoice invoice = new Invoice(UUID.fromString(uuidString));
        invoice.setTitle(title);
        invoice.setComment(cmnt);
        invoice.setShopName(shop);
        invoice.setDate(new Date(date));
        invoice.setSolved(isSolved != 0);
        return invoice;     }
}

