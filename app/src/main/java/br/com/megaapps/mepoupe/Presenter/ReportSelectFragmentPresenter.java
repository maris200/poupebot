package br.com.megaapps.mepoupe.Presenter;

import br.com.megaapps.mepoupe.View.ReportSelectFragment;

/**
 * Created by duh on 5/27/17.
 */

public class ReportSelectFragmentPresenter {

    private ReportSelectFragment reportSelectFragment;

    public ReportSelectFragmentPresenter(ReportSelectFragment reportSelectFragment) {
        this.reportSelectFragment = reportSelectFragment;
    }

    public void getDataSelectFragment(String id, String year, String type, String month){

        if(year.isEmpty() || type.isEmpty() || month.isEmpty()){

            if(month.isEmpty()){
                reportSelectFragment.emptyMonth();
                return;
            }
            if(year.isEmpty()){
                reportSelectFragment.emptyYear();
                return;
            }
            if(type.isEmpty()){
                reportSelectFragment.emptyType();
                return;
            }

        }

        if(type.equals("Receitas")){
            type= "input";
        }else if(type.equals("Despesas")){
            type= "output";
        }

        if(month.equals("Janeiro")){
            month = "1";
        }else if(type.equals("Fevereiro")){
            month = "2";
        }else if(month.equals("Março")){
            month = "3";
        }else if(month.equals("Abril")){
            month = "4";
        }else if(month.equals("Maio")){
            month = "5";
        }else if(month.equals("Junho")){
            month = "6";
        }else if(month.equals("Julho")){
            month = "7";
        }else if(month.equals("Agosto")){
            month = "8";
        }else if(month.equals("Setembro")){
            month = "9";
        }else if(month.equals("Outubro")){
            month = "10";
        }else if(month.equals("Novembro")){
            month = "11";
        }else if(month.equals("Dezembro")){
            month = "12";
        }

        reportSelectFragment.parseDataToChart(id, year, type, month);

    }

}
