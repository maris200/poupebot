package br.com.megaapps.mepoupe.Presenter;

import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import br.com.megaapps.mepoupe.Model.ChartModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.ReportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by mari on 20/05/17.
 */

public class ReportPresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";
    private ReportFragment reportFragment;

    public ReportPresenter(ReportFragment reportFragment) {
        this.reportFragment = reportFragment;
    }

    public void getDataChart(String uid, String mouth, String year, final String type) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<List<ChartModel>> call = service.createChart(uid, mouth, year, type);
        call.enqueue(new Callback<List<ChartModel>>() {
            @Override
            public void onResponse(Call<List<ChartModel>> call, Response<List<ChartModel>> response) {

                if (response.isSuccessful()) {

                    final List<ChartModel> chartDataList = response.body();

                    List<PieEntry> pieEntries = new ArrayList<>();

                    for (int i = 0; i < chartDataList.size(); i++) {

                        pieEntries.add(new PieEntry(chartDataList.get(i).getValue(), chartDataList.get(i).getCategory()));

                    }

                    if (chartDataList.isEmpty()){

                        reportFragment.emptyChart(type);

                    }else{
                        reportFragment.loadChart(pieEntries);

                    }

                }

            }

            @Override
            public void onFailure(Call<List<ChartModel>> call, Throwable t) {

            }
        });


    }
}
