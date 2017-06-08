package br.com.megaapps.mepoupe.Presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duh on 6/5/17.
 */

public class RecDeletePresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";

    private InputModel inputModel;

    public void deleteRec(String id) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<InputModel> call = service.delRec(id);
        call.enqueue(new Callback<InputModel>() {
            @Override
            public void onResponse(Call<InputModel> call, Response<InputModel> response) {

                if (response.isSuccessful()) {
                    inputModel = response.body();

                    if (inputModel.getResultCode().equals("OK")) {


                    }
                }

            }

            @Override
            public void onFailure(Call<InputModel> call, Throwable t) {

            }
        });

    }
}
