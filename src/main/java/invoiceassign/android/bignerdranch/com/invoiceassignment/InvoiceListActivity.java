package invoiceassign.android.bignerdranch.com.invoiceassignment;

import android.support.v4.app.Fragment;

public class InvoiceListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new InvoiceListFragment();
    }
}
