package br.com.megaapps.mepoupe.Presenter;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

import br.com.megaapps.mepoupe.Adapter.ObjectiveAdapter;
import br.com.megaapps.mepoupe.Model.ObjectiveModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.ObjectiveFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mari on 31/05/17.
 */

public class ObjectiveFragmentPresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";
    private ObjectiveFragment objectiveFragment;

    public ObjectiveFragmentPresenter(ObjectiveFragment objectiveFragment) {
        this.objectiveFragment = objectiveFragment;
    }

    public void getDataObjectiveView(String uid) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<List<ObjectiveModel>> call = service.listOjectives(uid);
        call.enqueue(new Callback<List<ObjectiveModel>>() {
            @Override
            public void onResponse(Call<List<ObjectiveModel>> call, Response<List<ObjectiveModel>> response) {

                if (response.isSuccessful()) {

                    final List<ObjectiveModel> objetiveList = response.body();

                    for (int i = 0; i < objetiveList.size(); i++) {

                        objectiveFragment.loadRecipe(objetiveList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ObjectiveModel>> call, Throwable t) {


            }
        });
    }

}
