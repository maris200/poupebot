package br.com.megaapps.mepoupe.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Presenter.ReportSelectFragmentPresenter;
import br.com.megaapps.mepoupe.R;

public class ReportSelectFragment extends MyFragment {

    private MaterialBetterSpinner spinnerMonth;
    private MaterialBetterSpinner spinnerYear;
    private MaterialBetterSpinner spinnerType;
    private Button btnPie;

    private TextInputLayout input_layout_month;
    private TextInputLayout input_layout_year;
    private TextInputLayout input_layout_type;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String[] month = {
            "Janeiro",
            "Fevereiro",
            "Março",
            "Abril",
            "Maio",
            "Junho",
            "Julho",
            "Agosto",
            "Setembro",
            "Outubro",
            "Novembro",
            "Dezembro"};

    String[] year = {
            "2017",
            "2018",
            "2019",
            "2020",
            "2021",
            "2022",
            "2023",
            "2024",
            "2025",
            "2026",
            "2027",
            "2028",
            "2029",
            "2030"};

    String[] type = {
            "Receitas",
            "Despesas"};

    private String monthResult;
    private String typeResult;

    private ReportSelectFragmentPresenter reportSelectFragmentPresenter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_report_select, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reportSelectFragmentPresenter = new ReportSelectFragmentPresenter(this);

        input_layout_month = (TextInputLayout)getActivity().findViewById(R.id.input_layout_month);
        input_layout_year = (TextInputLayout)getActivity().findViewById(R.id.input_layout_year);
        input_layout_type = (TextInputLayout)getActivity().findViewById(R.id.input_layout_type);

        ArrayAdapter<String> arrayAdapter_month = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, month);

        ArrayAdapter<String> arrayAdapter_year = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, year);

        ArrayAdapter<String> arrayAdapter_type = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, type);

        spinnerMonth = (MaterialBetterSpinner) getActivity().findViewById(R.id.spinnerMonth);
        spinnerMonth.setAdapter(arrayAdapter_month);

        spinnerYear = (MaterialBetterSpinner) getActivity().findViewById(R.id.spinnerYear);
        spinnerYear.setAdapter(arrayAdapter_year);

        spinnerType = (MaterialBetterSpinner) getActivity().findViewById(R.id.spinnerType);
        spinnerType.setAdapter(arrayAdapter_type);

        btnPie = (Button) getActivity().findViewById(R.id.btnPie);

        btnPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);

                String id = pref.getString("uid", "");
                String year = spinnerYear.getText().toString();

                String type = spinnerType.getText().toString();
                String month = spinnerMonth.getText().toString();

                reportSelectFragmentPresenter.getDataSelectFragment(id, year, type, month);

            }
        });

    }
    public void emptyMonth() {
        spinnerMonth.setError("Selecione o campo mês!");
        spinnerMonth.requestFocus();
    }

    public void emptyYear() {
        spinnerYear.setError("Selecione o campo ano!");
        spinnerYear.requestFocus();

    }

    public void emptyType() {
        spinnerType.setError("Selecione o campo tipo!");
        spinnerType.requestFocus();
    }


    public void parseDataToChart(String id, String year, String type, String month) {

        HomeActivity homeactivity = (HomeActivity) getActivity();
        ReportFragment frag = new ReportFragment();
        frag.setUid(id);
        frag.setMonth(month);
        frag.setYear(year);
        frag.setType(type);
        FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
        ft.addToBackStack("chart report");
        ft.replace(R.id.frContent,frag);
        ft.commit();

    }
}
