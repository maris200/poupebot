package br.com.megaapps.mepoupe.Service;

import java.util.List;

import br.com.megaapps.mepoupe.Model.ChartModel;
import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Model.ObjectiveModel;
import br.com.megaapps.mepoupe.Model.UserModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by mari on 15/05/17.
 */

public interface APIService {

    @FormUrlEncoded
    @POST("/ws_mepoupe/insert_user.php")
    Call<UserModel> createUser(
            @Field("name") String name,
            @Field("age") String age,
            @Field("email") String email,
            @Field("pass") String pass);

    @FormUrlEncoded
    @POST("/ws_mepoupe/login.php")
    Call<UserModel> login(
            @Field("email") String email,
            @Field("pass") String pass);

    @FormUrlEncoded
    @POST("/ws_mepoupe/forgot_password.php")
    Call<UserModel> recover(
            @Field("email") String email);

    @FormUrlEncoded
    @POST("/ws_mepoupe/insert_recipe.php")
    Call<InputModel> createInput(
            @Field("id") String id,
            @Field("value") String value,
            @Field("date") String date,
            @Field("category") String category,
            @Field("description") String description);

    @FormUrlEncoded
    @POST("/ws_mepoupe/insert_expense.php")
    Call<InputModel> createInputExpense(
            @Field("id") String id,
            @Field("value") String value,
            @Field("date") String date,
            @Field("category") String category,
            @Field("description") String description);

    @FormUrlEncoded
    @POST("/ws_mepoupe/load_data_profile.php")
    Call<UserModel> loadUserData(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("/ws_mepoupe/update_data_profile.php")
    Call<UserModel> updateDataUser(
            @Field("id") String id,
            @Field("name") String name,
            @Field("age") String age,
            @Field("email") String email);

    @FormUrlEncoded
    @POST("/ws_mepoupe/update_data_profile_password.php")
    Call<UserModel> updateDataUserPass(
            @Field("id") String id,
            @Field("actual") String actual,
            @Field("newPass") String newPass);


    @FormUrlEncoded
    @POST("/ws_mepoupe/load_data_input.php")
    Call<InputModel> loadDataInput(
            @Field("id") String id,
            @Field("month") String mouth,
            @Field("year") String year);

    @FormUrlEncoded
    @POST("/ws_mepoupe/load_chart.php")
    Call<List<ChartModel>> createChart(
            @Field("id") String id,
            @Field("month") String month,
            @Field("year") String year,
            @Field("type") String type);

    @FormUrlEncoded
    @POST("/ws_mepoupe/list_recipe.php")
    Call<List<InputModel>> listRecipe(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("/ws_mepoupe/list_expense.php")
    Call<List<InputModel>> listExpense(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("/ws_mepoupe/insert_objective.php")
    Call<ObjectiveModel> createObjective(
            @Field("id") String id,
            @Field("value") String value,
            @Field("nameGoal") String nameGoal);

    @FormUrlEncoded
    @POST("/ws_mepoupe/list_objective.php")
    Call<List<ObjectiveModel>> listOjectives(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("/ws_mepoupe/load_objective_recipe.php")
    Call<InputModel> loadTotalInput(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("/ws_mepoupe/insert_value_objective.php")
    Call<InputModel> saveValueObjective(
            @Field("id") String id,
            @Field("idObjective") String idObjective,
            @Field("value") String value,
            @Field("date") String date);

    @FormUrlEncoded
    @POST("/ws_mepoupe/load_data_edit_objective.php")
    Call<ObjectiveModel> editInput(
            @Field("id") String id,
            @Field("idObjective") String idObjective);

    @FormUrlEncoded
    @POST("/ws_mepoupe/update_data_objective.php")
    Call<ObjectiveModel> updateDataObjective(
            @Field("id") String id,
            @Field("idObjective") String idObjective,
            @Field("nameGoal") String nameGoal,
            @Field("totalValue") String totalValue);

    @FormUrlEncoded
    @POST("/ws_mepoupe/delete_objective.php")
    Call<ObjectiveModel> delObj(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("/ws_mepoupe/delete_expense.php")
    Call<InputModel> delExp(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("/ws_mepoupe/delete_recipe.php")
    Call<InputModel> delRec(
            @Field("id") String id);
}