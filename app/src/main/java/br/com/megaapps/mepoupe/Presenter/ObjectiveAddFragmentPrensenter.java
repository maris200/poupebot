package br.com.megaapps.mepoupe.Presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.ObjectiveModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.ObjectiveAddFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duh on 6/1/17.
 */

public class ObjectiveAddFragmentPrensenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";

    private ObjectiveAddFragment objectiveAddFragment;
    private ObjectiveModel objectiveModel;

    public ObjectiveAddFragmentPrensenter(ObjectiveAddFragment objectiveAddFragment) {
        this.objectiveAddFragment = objectiveAddFragment;
    }

    public void getDataObject(String id, String value, String nameGoal) {

        if (value.isEmpty() || nameGoal.isEmpty()) {

            if (value.isEmpty()) {

                objectiveAddFragment.valueEmpty();
                return;
            }

            if (nameGoal.isEmpty()) {

                objectiveAddFragment.nameGoalEmpty();
                return;
            }

        } else {

            saveObject(id, value, nameGoal);

        }

    }

    private void saveObject(String id, String value, String nameGoal) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<ObjectiveModel> call = service.createObjective(id, value, nameGoal);
        call.enqueue(new Callback<ObjectiveModel>() {
            @Override
            public void onResponse(Call<ObjectiveModel> call, Response<ObjectiveModel> response) {

                if (response.isSuccessful()) {

                    objectiveModel = response.body();

                    if(objectiveModel.getResultCode().equals("OK")){

                        objectiveAddFragment.saveOK();
                    }

                }

            }

            @Override
            public void onFailure(Call<ObjectiveModel> call, Throwable t) {

                Log.i("teste", "ws / "+t.getMessage());

            }
        });


    }
}
