package br.com.megaapps.mepoupe.Presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import br.com.megaapps.mepoupe.Model.ObjectiveModel;
import br.com.megaapps.mepoupe.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ObjDeletePresenter {

    private static String BASE_URL = "http://poupebot.esy.es/";

    private ObjectiveModel objectiveModel;

    public void deleteObj(String id) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<ObjectiveModel> call = service.delObj(id);
        call.enqueue(new Callback<ObjectiveModel>() {
            @Override
            public void onResponse(Call<ObjectiveModel> call, Response<ObjectiveModel> response) {

                if (response.isSuccessful()) {
                    objectiveModel = response.body();

                    if (objectiveModel.getResultCode().equals("OK")) {


                    }
                }
            }

            @Override
            public void onFailure(Call<ObjectiveModel> call, Throwable t) {

            }
        });
    }
}
