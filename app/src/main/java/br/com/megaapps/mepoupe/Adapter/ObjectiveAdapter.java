package br.com.megaapps.mepoupe.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.megaapps.mepoupe.Model.ObjectiveModel;
import br.com.megaapps.mepoupe.Presenter.ObjDeletePresenter;
import br.com.megaapps.mepoupe.Presenter.ObjectiveFragmentPresenter;
import br.com.megaapps.mepoupe.R;
import br.com.megaapps.mepoupe.View.HomeActivity;
import br.com.megaapps.mepoupe.View.ObjectiveAddFragment;
import br.com.megaapps.mepoupe.View.ObjectiveAddValueObjFragment;
import br.com.megaapps.mepoupe.View.ObjectiveEditFragment;
import br.com.megaapps.mepoupe.View.ObjectiveFragment;

/**
 * Created by duh on 6/1/17.
 */

public class ObjectiveAdapter extends BaseAdapter {

    private Context context;
    private List<ObjectiveModel> objetiveModel;

    private ObjDeletePresenter objDeletePresenter;

    public ObjectiveAdapter(Context context, List<ObjectiveModel> objetiveList) {

        this.context = context;
        this.objetiveModel = objetiveList;
    }

    @Override
    public int getCount() {
        return objetiveModel.size();
    }

    @Override
    public Object getItem(int position) {
        return objetiveModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v;

        v = View.inflate(context, R.layout.list_objective, null);

        TextView txtObjName = (TextView) v.findViewById(R.id.txtObjName);
        TextView txtValue = (TextView) v.findViewById(R.id.txtValue);
        ImageView imgTrophy = (ImageView) v.findViewById(R.id.imgTrophy);
        TextView txtFinish = (TextView) v.findViewById(R.id.txtFInish);
        TextView txtDelete = (TextView) v.findViewById(R.id.txtDelete);
        TextView txtAddMoney = (TextView) v.findViewById(R.id.txtAddMoney);
        TextView txtEdit = (TextView) v.findViewById(R.id.txtEdit);

        final String id = String.valueOf(objetiveModel.get(position).getId());

        txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                objDeletePresenter = new ObjDeletePresenter();

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

                builder.setMessage("Deletar ?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int idDl) {

                                objDeletePresenter.deleteObj(id);

                                HomeActivity homeactivity = (HomeActivity) context;
                                ObjectiveFragment frag = new ObjectiveFragment();
                                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                                ft.addToBackStack("goal");
                                ft.replace(R.id.frContent,frag);
                                ft.commit();

                                Toast.makeText(context, "Objetivo deletado!!!", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int idDl) {


                            }
                        });
                android.app.AlertDialog alert = builder.create();
                alert.show();
            }
        });

        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeactivity = (HomeActivity) context;
                ObjectiveEditFragment frag = new ObjectiveEditFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                frag.setIdObjective(id);
                ft.addToBackStack("editGoal");
                ft.replace(R.id.frContent,frag);
                ft.commit();
            }
        });

        txtAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity homeactivity = (HomeActivity) context;
                ObjectiveAddValueObjFragment frag = new ObjectiveAddValueObjFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                frag.setIdObjective(id);
                ft.addToBackStack("add value goal");
                ft.replace(R.id.frContent,frag);
                ft.commit();
            }
        });


        if(objetiveModel.get(position).getValue().equals(objetiveModel.get(position).getTotalValue())){
            txtFinish.setText("Objetivo alcançado!");
            txtAddMoney.setVisibility(View.GONE);
            txtEdit.setVisibility(View.GONE);
            imgTrophy.setImageResource(R.drawable.ic_trophy_laurel);

        }else{

            imgTrophy.setImageResource(R.drawable.ic_trophy_out_white);

        }

        ProgressBar mProgress = (ProgressBar) v.findViewById(R.id.progressbar);
        mProgress.setProgress((int) Double.parseDouble(objetiveModel.get(position).getValue()));
        mProgress.setMax((int) Double.parseDouble(objetiveModel.get(position).getTotalValue()));

        txtObjName.setText(objetiveModel.get(position).getNameGoal());
        txtValue.setText("Valor: R$ " + objetiveModel.get(position).getTotalValue());

        return v;
    }

}
