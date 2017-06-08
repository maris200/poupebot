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
import br.com.megaapps.mepoupe.Presenter.RecDeletePresenter;
import br.com.megaapps.mepoupe.R;
import br.com.megaapps.mepoupe.View.ExpenseViewFragment;
import br.com.megaapps.mepoupe.View.HomeActivity;
import br.com.megaapps.mepoupe.View.RecipeViewFragment;

/**
 * Created by duh on 31/05/17.
 */

public class RecipeAdapter extends BaseAdapter {

    private Context context;
    private List<InputModel> inputModels;

    public RecipeAdapter(Context context, List<InputModel> inputModels) {
        this.context = context;
        this.inputModels = inputModels;
    }

    private RecDeletePresenter recDeletePresenter;

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

        v = View.inflate(context, R.layout.list_recipe, null);

        Button btnCircle = (Button)v.findViewById(R.id.btnCircle);
        Button btnEdit = (Button)v.findViewById(R.id.btnEdit);

        TextView txtCategory = (TextView)v.findViewById(R.id.txtCategory);
        TextView txtValue = (TextView)v.findViewById(R.id.txtValue);
        TextView txtDescription = (TextView)v.findViewById(R.id.txtDescription);

        if(inputModels.get(position).getCategory().equals("Salário")){

            btnCircle.setBackgroundResource(R.drawable.circle_green_light);

        }
        if(inputModels.get(position).getCategory().equals("Outros")){

            btnCircle.setBackgroundResource(R.drawable.circle_purple);

        }
        if(inputModels.get(position).getCategory().equals("Sem categoria")){

            btnCircle.setBackgroundResource(R.drawable.circle_gray_light);

        }

        final String id = String.valueOf(inputModels.get(position).getId());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recDeletePresenter =  new RecDeletePresenter();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Deletar?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int idRec) {

                                recDeletePresenter.deleteRec(id);

                                HomeActivity homeactivity = (HomeActivity) context;
                                RecipeViewFragment frag = new RecipeViewFragment();
                                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                                ft.addToBackStack("recipe load");
                                ft.replace(R.id.frContent,frag);
                                ft.commit();


                                Toast.makeText(context, "Receita deletado!!!", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int idRec) {


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
