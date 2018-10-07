package invoiceassign.android.bignerdranch.com.invoiceassignment;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.io.File;
import java.util.Date;
import java.util.UUID;

public class InvoiceUpdateFragment extends Fragment {
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO= 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_INVOICE_ID = "invoice_id";
    private Invoice invoiceInvoice;
    private EditText invoiceTitleField;
    private RadioGroup invoiceRadioGroup;
    private ToggleButton invoiceType1, invoiceType2, invoiceType3,invoiceType4;
    private EditText invoiceShopName;
    private EditText invoiceComment;
    private Location invoiceLocation;
    private Button invoiceDateButton;
    private Button invoiceSave;
    private Button invoiceCancel;
    private CheckBox mSolvedCheckBox;
    private File invoicePhotoFile;

    public static InvoiceFragment newInstance(UUID invoiceId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_INVOICE_ID, invoiceId);
        InvoiceFragment fragment = new InvoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID invoiceId = (UUID) getArguments().getSerializable(ARG_INVOICE_ID);
        invoiceInvoice = InvoiceDetail.get(getActivity()).getInvoices(invoiceId); }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.update_invoice_fragment, container, false);
        /**
         * For the title field
         */
        invoiceTitleField = (EditText) v.findViewById(R.id.invoice_title);
        invoiceTitleField.setText(invoiceInvoice.getTitle());
        invoiceTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                invoiceInvoice.setTitle(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for the shop field
         */
        invoiceShopName = (EditText) v.findViewById(R.id.invoice_shop);
        invoiceShopName.setText(invoiceInvoice.getShopName());
        invoiceShopName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                invoiceInvoice.setShopName(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for Invoice Comment
         */
        invoiceComment = (EditText) v.findViewById(R.id.invoice_cmnt);
        invoiceComment.setText(invoiceInvoice.getComment());
        invoiceComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                invoiceInvoice.setComment(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for Invoice location
         */

/**
 * for Invoice Type
 */
        invoiceRadioGroup = (RadioGroup)v.findViewById(R.id.invoice_radio_group);

        /**
         * for Date Button
         */
        invoiceDateButton = (Button) v.findViewById(R.id.invoice_date);
        updateDate();
        invoiceDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(invoiceInvoice.getDate());
                dialog.setTargetFragment(InvoiceUpdateFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        /**
         * not needed actually
         */
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.invoice_solved);
        mSolvedCheckBox.setChecked(invoiceInvoice.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                invoiceInvoice.setSolved(isChecked);
            }        });
        /**
         *
         *for cancel button
         */
        invoiceCancel = (Button) v.findViewById(R.id.cancel_button);
        invoiceCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        /**
         * for save button
         *
         */
        invoiceSave = (Button) v.findViewById(R.id.save_button);
        invoiceSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }
    @Override    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            invoiceInvoice.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        invoiceDateButton.setText(invoiceInvoice.getDate().toString());
    }
}
