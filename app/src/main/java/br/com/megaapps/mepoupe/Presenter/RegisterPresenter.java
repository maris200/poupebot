package br.com.megaapps.mepoupe.Presenter;

import android.util.Log;
import br.com.megaapps.mepoupe.Model.UserModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.RegisterActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mari on 15/05/17.
 */

public class RegisterPresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";

    private RegisterActivity registerActivity;
    private UserModel userModel;

    public RegisterPresenter(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
    }

    public void getDataUser(String name, String age, String email, String pass) {

        if(name.isEmpty() || age.isEmpty() || email.isEmpty()){

            if (name.isEmpty()){
                registerActivity.emptyName();
                return;
            }

            if (age.isEmpty()){
                registerActivity.emptyAge();
                return;
            }

            if (email.isEmpty()){
                registerActivity.emptyEmail();
                return;
            }

            if(pass.isEmpty()){
                registerActivity.emptyPass();
                return;
            }

        }else{
            createUser(name, age, email, pass);
        }
    }

    public void createUser(String name, String age, String email, String pass){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<UserModel> call = service.createUser(name, age, email, pass);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(response.isSuccessful()){

                    userModel = response.body();

                    if(userModel.getResultCode().equals("OK")){

                        registerActivity.cadastroOk();

                    } else if (userModel.getResultCode().equals("NO")){

                        registerActivity.cadastroNo();

                    } else{

                        registerActivity.cadastroErro();

                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

                Log.i("onFailure",t.getMessage());
            }
        });
    }

}
