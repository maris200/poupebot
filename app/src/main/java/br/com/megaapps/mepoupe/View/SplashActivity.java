package br.com.megaapps.mepoupe.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Extendables.MyFragActivity;
import br.com.megaapps.mepoupe.Model.UserModel;
import br.com.megaapps.mepoupe.R;
import br.com.megaapps.mepoupe.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends MyFragActivity {

    private static String BASE_URL =  "http://poupebot.esy.es/";

    private static int SPLASH_TIME_OUT = 3000;

    private String email;
    private String password;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        email = pref.getString("email", "");
        password = pref.getString("password", "");
        
        if (email.equals("") || password.equals("")) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();

                }
            }, SPLASH_TIME_OUT);


        } else {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            APIService service = retrofit.create(APIService.class);
            Call<UserModel> call = service.login(email, password);
            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                    if (response.isSuccessful()) {

                        UserModel userModel = response.body();

                        if(userModel.getResultCode().equals("OK")) {

                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                            finish();

                        }else {

                            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();

                        }
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "Error WS Splash login: " + t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

        }

    }

}
