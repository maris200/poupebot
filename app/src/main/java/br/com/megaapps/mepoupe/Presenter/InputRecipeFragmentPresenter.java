package br.com.megaapps.mepoupe.Presenter;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.InputRecipeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duh on 5/21/17.
 */

public class InputRecipeFragmentPresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";

    private InputRecipeFragment inputRecipeFragment;
    private InputModel inputModel;

    public InputRecipeFragmentPresenter(InputRecipeFragment inputRecipeFragment) {
        this.inputRecipeFragment = inputRecipeFragment;
    }

    public void getRecipeData(String id, String value, String date, String category, String description) {

        if (value.isEmpty() || date.isEmpty() || category.isEmpty() || description.isEmpty()) {

            if (value.isEmpty()) {
                inputRecipeFragment.emptyValue();
                return;
            }
            if (date.isEmpty()) {
                inputRecipeFragment.emptyDate();
                return;
            }
            if (category.isEmpty()) {
                inputRecipeFragment.emptyCategory();
                return;
            }
            if (description.isEmpty()) {
                inputRecipeFragment.emptyDescription();
                return;
            }

        } else {

            createRecipe(id, value, date, category, description);
        }
    }

    private void createRecipe(String id, String value, String date, String category, String description) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<InputModel> call = service.createInput(id, value, date, category, description);
        call.enqueue(new Callback<InputModel>() {
            @Override
            public void onResponse(Call<InputModel> call, Response<InputModel> response) {

                if (response.isSuccessful()) {

                    inputModel = response.body();

                    if (inputModel.getResultCode().equals("OK")) {

                        inputRecipeFragment.recipeOk();

                    }else{

                        inputRecipeFragment.recipeError();
                    }
                }

                Log.d("onResponse","OK");

            }

            @Override
            public void onFailure(Call<InputModel> call, Throwable t) {

                    Log.d("onFailure", t.getMessage());

            }
        });
    }

}
