package br.com.megaapps.mepoupe.View;
import java.util.Calendar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Presenter.HomePresenter;
import br.com.megaapps.mepoupe.R;

public class HomeFragmentContent extends MyFragment {

    private TextView txtBalance;
    private TextView txtMoney;
    private TextView txtExpense;
    private TextView txtSwing;
    private LinearLayout lay_recipe;
    private LinearLayout lay_expense;
    //private TextView txtNameUser;

    private com.getbase.floatingactionbutton.FloatingActionButton floatInputMoney;
    private com.getbase.floatingactionbutton.FloatingActionButton floatExpense;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private HomePresenter homePresenter;

    //private String name;
    private String id;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_content, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        //name = pref.getString("name", "");
        id = pref.getString("uid", "");

        homePresenter = new HomePresenter(this);

        lay_recipe = (LinearLayout) getActivity().findViewById(R.id.lay_recipe);
        lay_expense = (LinearLayout) getActivity().findViewById(R.id.lay_expense);

        txtBalance = (TextView) getActivity().findViewById(R.id.txtBalance);
        txtMoney = (TextView) getActivity().findViewById(R.id.txtMoney);
        txtExpense = (TextView) getActivity().findViewById(R.id.txtExpense);
        txtSwing = (TextView) getActivity().findViewById(R.id.txtSwing);
        //txtNameUser = (TextView) getActivity().findViewById(R.id.txtNameUser);
        //txtNameUser.setText("Usuário: "+name);

        floatInputMoney = (com.getbase.floatingactionbutton.FloatingActionButton) getActivity().findViewById(R.id.floatInputMoney);
        floatExpense = (com.getbase.floatingactionbutton.FloatingActionButton) getActivity().findViewById(R.id.floatExpense);

        floatInputMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeactivity = (HomeActivity) getActivity();
                InputRecipeFragment frag = new InputRecipeFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("recipe");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });

        floatExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeactivity = (HomeActivity) getActivity();
                InputExpenseFragment frag = new InputExpenseFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("expense");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });

        lay_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeactivity = (HomeActivity) getActivity();
                RecipeViewFragment frag = new RecipeViewFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("recipe view");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });

        lay_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeactivity = (HomeActivity) getActivity();
                ExpenseViewFragment frag = new ExpenseViewFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("expense view");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });


        Calendar calendar = Calendar.getInstance();
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));

        String mouth = Integer.toString(calendar.get(Calendar.MONTH));
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        homePresenter.loadDataInput(id, mouth, year);

        txtBalance.setText("Saldo atual data: "+day+"/"+mouth+"/"+year);

    }


    public void loadData(String totalExpense, String totalRecipe, String totalMouth) {

        txtMoney.setText("R$ "+totalRecipe);
        txtExpense.setText("R$ "+totalExpense);
        txtSwing.setText("R$ "+totalMouth);

    }
}
