package br.com.megaapps.mepoupe.Presenter;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.HomeFragmentContent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mari on 20/05/17.
 */

public class HomePresenter {

    private static String BASE_URL = "http://poupebot.esy.es";
    private HomeFragmentContent homeFragment;
    private InputModel inputModel;

    public HomePresenter (HomeFragmentContent homeFragment){
        this.homeFragment = homeFragment;
    }

    public void loadDataInput(String id, String mouth, String year) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<InputModel> call = service.loadDataInput(id, mouth, year);
        call.enqueue(new Callback<InputModel>() {
            @Override
            public void onResponse(Call<InputModel> call, Response<InputModel> response) {

                if (response.isSuccessful()) {

                    inputModel = response.body();

                    if (inputModel.getResultCode().equals("OK")) {

                        String totalExpense = inputModel.getTotalExpense();
                        String totalRecipe = inputModel.getTotalRecipe();
                        String totalMouth = inputModel.getTotalMonth();

                        if(totalExpense.equals("0")){
                            totalExpense = "0.00";
                        }
                        if(totalRecipe.equals("0")){
                            totalRecipe = "0.00";
                        }
                        if(totalMouth.equals("0")){
                            totalMouth = "0.00";
                        }

                        homeFragment.loadData(totalExpense, totalRecipe, totalMouth);
                    }
                }

            }

            @Override
            public void onFailure(Call<InputModel> call, Throwable t) {

                Log.i("onFailure", "onFailure"+t.getMessage());

            }
        });

    }
}
