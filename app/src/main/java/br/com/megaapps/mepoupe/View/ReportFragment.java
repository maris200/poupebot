package br.com.megaapps.mepoupe.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;

import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Model.ChartModel;
import br.com.megaapps.mepoupe.Presenter.ReportPresenter;
import br.com.megaapps.mepoupe.R;

/**
 * Created by duh on 5/25/17.
 */

public class ReportFragment  extends MyFragment {

    private String uid;
    private String month;
    private String year;
    private String type;
    private String name;
    private TextView txtTittle;
    private TextView txtEmptyChart;

    private PieChart pieChart;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private ReportPresenter reportPresenter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reportPresenter = new ReportPresenter(this);
        reportPresenter.getDataChart(getUid(), getMonth(), getYear(), getType());

        txtTittle = (TextView)getActivity().findViewById(R.id.txtTittle);
        txtEmptyChart = (TextView)getActivity().findViewById(R.id.txtEmptyChart);
        pieChart = (PieChart)getActivity().findViewById(R.id.pieChart);

    }

    public void loadChart(List<PieEntry> pieEntries) {


        if(getType().equals("input")){

            name="receitas";
            txtTittle.setText("Gráfico de receitas "+"Mês : "+getMonth()+"/"+" Ano : "+getYear());

        }else if(getType().equals("output")){

            name="despesas";
            txtTittle.setText("Gráfico de despesas "+"Mês : "+getMonth()+"/"+" Ano : "+getYear());
        }


        PieDataSet dataSet = new PieDataSet(pieEntries, name);
        dataSet.setValueTextSize(15);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data =  new PieData(dataSet);

        pieChart.setData(data);
        pieChart.invalidate();

    }

    public void emptyChart(String type){

        if(type.equals("input")){

            txtTittle.setText("Gráfico de receitas");

        }else if(type.equals("output")){


            txtTittle.setText("Gráfico de despesas");
        }

        txtEmptyChart.setText("Você não possui dados gerar o gráfico para o mês ou ano informado");
        txtEmptyChart.setVisibility(View.VISIBLE);
        pieChart.setVisibility(View.GONE);
    }

}
