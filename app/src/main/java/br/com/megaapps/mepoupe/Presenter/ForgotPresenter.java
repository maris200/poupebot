package br.com.megaapps.mepoupe.Presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.UserModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.ForgotActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by mari on 20/05/17.
 */

public class ForgotPresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";

    private ForgotActivity forgotActivity;
    private UserModel userModel;

    public ForgotPresenter(ForgotActivity forgotActivity) {
        this.forgotActivity = forgotActivity;

    }

    public void getDateRecoverPass(String email) {

        if (email.isEmpty()){
            forgotActivity.emptyEmail();
            return;

        }else {

            recover(email);

        }
}

    private void recover(String email) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<UserModel> call = service.recover(email);
        call.enqueue(new Callback<UserModel>(){
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if (response.isSuccessful()) {

                    userModel = response.body();

                    if (userModel.getResultCode().equals("OK")) {

                        forgotActivity.recoverOk();

                    } else if (userModel.getResultCode().equals("NO")) {

                        forgotActivity.recoverNo();

                    } else {

                        forgotActivity.recoverErro();

                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.i("onFailure", t.getMessage());
            }
        });
    }
}
