package br.com.megaapps.mepoupe.Presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.ObjectiveAddValueObjFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duh on 6/3/17.
 */

public class ObjectiveAddValuePresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";
    private InputModel inputModel;

    private ObjectiveAddValueObjFragment objectiveAddValueObjFragment;

    public ObjectiveAddValuePresenter(ObjectiveAddValueObjFragment objectiveAddValueObjFragment) {
        this.objectiveAddValueObjFragment = objectiveAddValueObjFragment;
    }

    public void loadValueRecipeUser(String id) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<InputModel> call = service.loadTotalInput(id);
        call.enqueue(new Callback<InputModel>() {
            @Override
            public void onResponse(Call<InputModel> call, Response<InputModel> response) {

                if (response.isSuccessful()) {

                    inputModel = response.body();

                    if (inputModel.getResultCode().equals("OK")) {

                        String totalRecipe = inputModel.getTotalRecipe();
                        objectiveAddValueObjFragment.LoadRecipeAvailable(totalRecipe);

                    }
                }

            }

            @Override
            public void onFailure(Call<InputModel> call, Throwable t) {

                Log.i("onFailure","onFailure"+t.getMessage());
            }
        });

    }



    public void getValueObjective(String id, String idObjective, String value, String date) {

       if(value.isEmpty()) {
           objectiveAddValueObjFragment.valueEmpty();
           return;

       }else if(value.equals("0")) {
           objectiveAddValueObjFragment.valueZero();
           return;
       }else{
           saveValueObjective(id, idObjective, value, date);
       }

    }

    private void saveValueObjective(String id, String idObjective, String value, String date) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<InputModel> call = service.saveValueObjective(id, idObjective, value, date);
        call.enqueue(new Callback<InputModel>() {
            @Override
            public void onResponse(Call<InputModel> call, Response<InputModel> response) {

                if (response.isSuccessful()) {

                    inputModel = response.body();

                    if(inputModel.getResultCode().equals("ERRORONE")){

                        objectiveAddValueObjFragment.valueGreaterRecipeError();

                    }

                    if(inputModel.getResultCode().equals("ERRORTWO")){

                        objectiveAddValueObjFragment.valueGreaterRecipeError();

                    }

                    if(inputModel.getResultCode().equals("OK")){

                        objectiveAddValueObjFragment.valueAdd();

                    }


                }

            }

            @Override
            public void onFailure(Call<InputModel> call, Throwable t) {

            }
        });
    }


}
