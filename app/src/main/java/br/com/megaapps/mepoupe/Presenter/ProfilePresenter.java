package br.com.megaapps.mepoupe.Presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.UserModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.ProfileFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mari on 20/05/17.
 */

public class ProfilePresenter {

    private static String BASE_URL = "http://poupebot.esy.es/";
    private ProfileFragment profileFragment;
    private UserModel userModel;

    public ProfilePresenter (ProfileFragment profileFragment){
        this.profileFragment = profileFragment;
    }

    public void getDataUpdate(String id, String name, String age, String email) {


        if(name.isEmpty() || email.isEmpty() || age.isEmpty()){

            if(name.isEmpty()){
                profileFragment.emptyName();
            }
            if(email.isEmpty()){

                profileFragment.emptyEmail();
            }
            if(age.isEmpty()){

                profileFragment.emptyAge();
            }

        }else{

            updateDataUser(id, name, age, email);

        }
    }

    private void updateDataUser(String id, String name, String age, String email) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<UserModel> call = service.updateDataUser(id, name, age, email);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(response.isSuccessful()){

                    userModel = response.body();

                    if(userModel.getResultCode().equals("OK")){

                        profileFragment.updateOK();

                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }


    public void getIdUser(String id) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<UserModel> call = service.loadUserData(id);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(response.isSuccessful()){

                    userModel = response.body();

                    if(userModel.getResultCode().equals("OK")){

                        String name = userModel.getName();
                        String email = userModel.getEmail();
                        String age = userModel.getAge();

                        profileFragment.load(name, email, age);

                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {


            }
        });
    }

}
