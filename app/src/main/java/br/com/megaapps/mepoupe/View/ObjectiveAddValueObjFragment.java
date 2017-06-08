package br.com.megaapps.mepoupe.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Presenter.ObjectiveAddValuePresenter;
import br.com.megaapps.mepoupe.R;

/**
 * Created by duh on 6/3/17.
 */

public class ObjectiveAddValueObjFragment extends MyFragment {

    private ObjectiveAddValuePresenter objectiveAddValuePresenter;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private String id;

    private TextView txtValueRecipe;
    private EditText editValue;

    private TextInputLayout input_layout_value_goal;

    private Button btnSave;

    private String idObjective;

    private String idObj;

    public String getIdObjective() {
        return idObjective;
    }

    public void setIdObjective(String idObjective) {
        this.idObjective = idObjective;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_objective_add_value_obj, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        objectiveAddValuePresenter = new ObjectiveAddValuePresenter(this);

        pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        id = pref.getString("uid", "");

        objectiveAddValuePresenter.loadValueRecipeUser(id);

        input_layout_value_goal = (TextInputLayout)getActivity().findViewById(R.id.input_layout_value_goal);

        txtValueRecipe = (TextView)getActivity().findViewById(R.id.txtValueRecipe);

        editValue = (EditText)getActivity().findViewById(R.id.editValue);

        btnSave = (Button)getActivity().findViewById(R.id.btnSave);

        idObj = getIdObjective();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
                String mouth = Integer.toString(calendar.get(Calendar.MONTH));
                String year = Integer.toString(calendar.get(Calendar.YEAR));

                String fullDate = year+"/"+mouth+"/"+day;

                String value = editValue.getText().toString();

                objectiveAddValuePresenter.getValueObjective(id, idObj, value, fullDate);

            }
        });

    }

    public void LoadRecipeAvailable(String totalRecipe) {

        txtValueRecipe.setText("Total Receita: R$ "+totalRecipe);
    }

    public void valueEmpty() {

        input_layout_value_goal.setError("Preencha o valor");
        editValue.requestFocus();
    }

    public void valueZero() {
        input_layout_value_goal.setError("Digita um valor maior que zero");
        editValue.requestFocus();
    }

    public void valueGreaterRecipeError() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Atenção");
        builder.setMessage("O valor digitado não pode ser maior que sua receita, ou maior do que o valor do seu objetivo");
        builder.show();
        editValue.requestFocus();

    }

    public void valueAdd() {

        HomeActivity homeactivity = (HomeActivity) getActivity();
        ObjectiveFragment frag = new ObjectiveFragment();
        FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
        ft.addToBackStack("list goal");
        ft.replace(R.id.frContent,frag);
        ft.commit();

        Toast.makeText(getContext(),"Valor adicionado com sucesso", Toast.LENGTH_SHORT).show();
    }

}
