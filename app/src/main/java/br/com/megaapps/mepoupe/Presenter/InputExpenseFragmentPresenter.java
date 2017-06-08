package br.com.megaapps.mepoupe.Presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.InputExpenseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duh on 5/22/17.
 */

public class InputExpenseFragmentPresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";
    private InputExpenseFragment inputExpenseFragment;
    private InputModel inputModel;

    public InputExpenseFragmentPresenter(InputExpenseFragment inputExpenseFragment) {
        this.inputExpenseFragment = inputExpenseFragment;
    }

    public void getExpenseData(String id, String value, String date, String category, String description) {

        if (value.isEmpty() || date.isEmpty() || category.isEmpty() || description.isEmpty()) {

            if (value.isEmpty()) {
                inputExpenseFragment.emptyValue();
                return;
            }
            if (date.isEmpty()) {
                inputExpenseFragment.emptyDate();
                return;
            }
            if (category.isEmpty()) {
                inputExpenseFragment.emptyCategory();
                return;
            }
            if (description.isEmpty()) {
                inputExpenseFragment.emptyDescription();
                return;
            }

        } else {

            createExpense(id, value, date, category, description);

        }
    }

    private void createExpense(String id, String value, String date, String category, String description) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<InputModel> call = service.createInputExpense(id, value, date, category, description);
        call.enqueue(new Callback<InputModel>() {
            @Override
            public void onResponse(Call<InputModel> call, Response<InputModel> response) {

                if (response.isSuccessful()) {

                    inputModel = response.body();

                    if (inputModel.getResultCode().equals("OK")) {

                        inputExpenseFragment.expensiveOK();
                    }else{
                        inputExpenseFragment.expensiveError();
                    }
                }
            }

            @Override
            public void onFailure(Call<InputModel> call, Throwable t) {

            }
        });
    }


}
