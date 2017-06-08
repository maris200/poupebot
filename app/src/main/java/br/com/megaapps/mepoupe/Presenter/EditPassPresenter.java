package br.com.megaapps.mepoupe.Presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.megaapps.mepoupe.Model.UserModel;
import br.com.megaapps.mepoupe.Service.APIService;
import br.com.megaapps.mepoupe.View.EditPasswordFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by mari on 25/05/17.
 */

public class EditPassPresenter {

    private static String BASE_URL =  "http://poupebot.esy.es/";
    private EditPasswordFragment editPasswordFragment;
    private UserModel userModel;

    public EditPassPresenter (EditPasswordFragment editPasswordFragment){
        this.editPasswordFragment = editPasswordFragment;
    }

    public void getDataUpdatePass(String id, String actual, String newPass, String ConfirmPass) {


        if(actual.isEmpty() || newPass.isEmpty() || ConfirmPass.isEmpty()){

            if(actual.isEmpty()){
                editPasswordFragment.emptyActual();
                return;
            }
            if(newPass.isEmpty()){

                editPasswordFragment.emptyNewPass();
                return;
            }
            if(ConfirmPass.isEmpty()){

                editPasswordFragment.emptyConfirm();
                return;
            }

        }else if(!newPass.equals(ConfirmPass)){

            editPasswordFragment.passNotEquals();
            return;

        }else{
            updateDataUserPass(id, actual, newPass);
        }
    }

    private void updateDataUserPass(String id, String actual, String newPass) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<UserModel> call = service.updateDataUserPass(id, actual, newPass);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(response.isSuccessful()){

                    userModel = response.body();

                    if(userModel.getResultCode().equals("OK")){

                        editPasswordFragment.updatePassOK();

                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }
}
