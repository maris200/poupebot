package br.com.megaapps.mepoupe.Presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.UserModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mari on 15/05/17.
 */

public class LoginPresenter {

    private static String BASE_URL = "http://poupebot.esy.es";

    private LoginActivity loginActivity;
    private UserModel userModel;

    public LoginPresenter(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;

    }

    public void getDateLogin(String email, String pass) {

        if (email.isEmpty() || pass.isEmpty()) {

            if (email.isEmpty()) {

                loginActivity.emptyEmail();
                return;

            }

            if (pass.isEmpty()) {

                loginActivity.emptyPass();
                return;
            }


        } else {

            login(email, pass);

        }
    }

    private void login(String email, String pass) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<UserModel> call = service.login(email, pass);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if (response.isSuccessful()) {

                    userModel = response.body();

                    if (userModel.getResultCode().equals("OK")) {

                        String id = userModel.getId();
                        String name = userModel.getName();

                        loginActivity.cadastroOk(id, name);

                    } else if (userModel.getResultCode().equals("NO")) {

                        loginActivity.cadastroNo();

                    } else {

                        loginActivity.cadastroErro();

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
