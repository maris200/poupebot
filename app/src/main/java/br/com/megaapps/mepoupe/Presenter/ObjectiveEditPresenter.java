package br.com.megaapps.mepoupe.Presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Model.ObjectiveModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.ObjectiveEditFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duh on 6/4/17.
 */

public class ObjectiveEditPresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";

    private ObjectiveEditFragment objectiveEditFragment;
    private ObjectiveModel objectiveModel;

    public ObjectiveEditPresenter(ObjectiveEditFragment objectiveEditFragment) {
        this.objectiveEditFragment = objectiveEditFragment;
    }

    public void getDataEditLoad(String id, String idObjective) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<ObjectiveModel> call = service.editInput(id, idObjective);
        call.enqueue(new Callback<ObjectiveModel>() {
            @Override
            public void onResponse(Call<ObjectiveModel> call, Response<ObjectiveModel> response) {

                objectiveModel = response.body();

                if (objectiveModel.getResultCode().equals("OK")) {

                    objectiveEditFragment.loadData(objectiveModel.getNameGoal(), objectiveModel.getTotalValue());

                }

            }

            @Override
            public void onFailure(Call<ObjectiveModel> call, Throwable t) {

            }
        });


    }

    public void getDataEdit(String id, String idObjective, String nameGoal, String totalValue) {

        if (nameGoal.isEmpty() || totalValue.isEmpty()) {

            if(nameGoal.isEmpty()){

                objectiveEditFragment.emptyNameGoal();
                return;

            }
            if(totalValue.isEmpty()){

                objectiveEditFragment.emptyValue();
                return;
            }

        } else {

            updateObjetive(id, idObjective, nameGoal, totalValue);
        }
    }

    private void updateObjetive(String id, String idObjective, String nameGoal, String totalValue) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<ObjectiveModel> call = service.updateDataObjective(id, idObjective, nameGoal, totalValue);
        call.enqueue(new Callback<ObjectiveModel>() {
            @Override
            public void onResponse(Call<ObjectiveModel> call, Response<ObjectiveModel> response) {

                objectiveModel = response.body();

                if (objectiveModel.getResultCode().equals("OK")) {

                    objectiveEditFragment.updateOK();
                }

            }

            @Override
            public void onFailure(Call<ObjectiveModel> call, Throwable t) {

            }
        });

    }

}
