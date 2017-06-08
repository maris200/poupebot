package br.com.megaapps.mepoupe.Presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.RecipeViewFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duh on 31/05/17.
 */

public class RecipeViewPresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";

    private RecipeViewFragment recipeViewFragment;

    public RecipeViewPresenter(RecipeViewFragment recipeViewFragment) {
        this.recipeViewFragment = recipeViewFragment;
    }

    public void getDataRecipeView(String uid) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<List<InputModel>> call = service.listRecipe(uid);
        call.enqueue(new Callback<List<InputModel>>() {
            @Override
            public void onResponse(Call<List<InputModel>> call, Response<List<InputModel>> response) {

                if (response.isSuccessful()) {

                    final List<InputModel> inputList = response.body();

                    for (int i = 0; i < inputList.size(); i++) {

                        recipeViewFragment.loadRecipe(inputList);
                    }

                }
            }
            @Override
            public void onFailure(Call<List<InputModel>> call, Throwable t) {

            }
        });

    }
}
