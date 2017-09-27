package app.devmedia.com.br.appdevmedia.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class DatePickerFragment extends DialogFragment {

    protected DatePickerDialog.OnDateSetListener listener;
    protected int ano, mes, dia;

    public void setDateListener(DatePickerDialog.OnDateSetListener listener) {

        this.listener = listener;

    }

    @Override
    public void setArguments(Bundle args) {

        super.setArguments(args);
        ano = args.getInt("ano");
        mes = args.getInt("mes");
        dia = args.getInt("dia");

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new DatePickerDialog(getActivity(), listener, ano, mes, dia);

    }

}
