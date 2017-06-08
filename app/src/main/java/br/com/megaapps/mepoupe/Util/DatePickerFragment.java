package br.com.megaapps.mepoupe.Util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import br.com.megaapps.mepoupe.R;

/**
 * Created by duh on 5/22/17.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        DatePickerDialog dpDialog = new DatePickerDialog(getActivity(), this, day, month, year);
        dpDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        return dpDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int day, int month, int year) {

        if (getTag()=="dateOne"){
            TextView edtDepartureDate = (TextView) getActivity().findViewById(R.id.editDateRecipe);
            edtDepartureDate.setText(day + "/" + month + "/" + year);
        }

        if (getTag()=="dateTwo"){

            TextView edtReturn = (TextView) getActivity().findViewById(R.id.editDate);
            edtReturn.setText(day + "/" + month + "/" + year);

        }
    }
}

