package br.com.megaapps.mepoupe.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Presenter.ExpDeletePresenter;
import br.com.megaapps.mepoupe.R;
import br.com.megaapps.mepoupe.View.ExpenseViewFragment;
import br.com.megaapps.mepoupe.View.HomeActivity;
import br.com.megaapps.mepoupe.View.ObjectiveFragment;

/**
 * Created by duh on 31/05/17.
 */

public class ExpenseAdapter extends BaseAdapter {

    private Context context;
    private List<InputModel> inputModels;

    private ExpDeletePresenter expDeletePresenter;

    public ExpenseAdapter(Context context, List<InputModel> inputModels) {
        this.context = context;
        this.inputModels = inputModels;
    }

    @Override
    public int getCount() {
        return inputModels.size();
    }

    @Override
    public Object getItem(int position) {
        return inputModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = null;

        v = View.inflate(context, R.layout.list_expense, null);

        Button btnCircle = (Button)v.findViewById(R.id.btnCircle);
        Button btnEdit = (Button)v.findViewById(R.id.btnEdit);

        TextView txtCategory = (TextView)v.findViewById(R.id.txtCategory);
        TextView txtValue = (TextView)v.findViewById(R.id.txtValue);
        TextView txtDescription = (TextView)v.findViewById(R.id.txtDescription);

        if(inputModels.get(position).getCategory().equals("Saúde")){

            btnCircle.setBackgroundResource(R.drawable.circle_blue_light);

        }
        if(inputModels.get(position).getCategory().equals("Alimentação")){

            btnCircle.setBackgroundResource(R.drawable.circle_red);

        }
        if(inputModels.get(position).getCategory().equals("Transporte")){

            btnCircle.setBackgroundResource(R.drawable.circle_orange_light);

        }

        if(inputModels.get(position).getCategory().equals("Lazer")){

            btnCircle.setBackgroundResource(R.drawable.circle_purple);

        }

        if(inputModels.get(position).getCategory().equals("Moradia")){

            btnCircle.setBackgroundResource(R.drawable.circle_pink);

        }
        if(inputModels.get(position).getCategory().equals("Contas")){

            btnCircle.setBackgroundResource(R.drawable.circle_green_light);

        }

        if(inputModels.get(position).getCategory().equals("Sem categoria")){

            btnCircle.setBackgroundResource(R.drawable.circle_gray_light);

        }

        final String id = String.valueOf(inputModels.get(position).getId());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expDeletePresenter = new ExpDeletePresenter();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Deletar?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int idDl) {

                                expDeletePresenter.deleteExp(id);

                                HomeActivity homeactivity = (HomeActivity) context;
                                ExpenseViewFragment frag = new ExpenseViewFragment();
                                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                                ft.addToBackStack("goal");
                                ft.replace(R.id.frContent,frag);
                                ft.commit();

                                Toast.makeText(context, "Despesa deletada!!!", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int idDl) {


                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        txtCategory.setText(inputModels.get(position).getCategory());
        txtValue.setText("R$ "+inputModels.get(position).getValue());
        txtDescription.setText(inputModels.get(position).getDescription());

        return v;

    }
}
