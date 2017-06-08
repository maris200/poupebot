package br.com.megaapps.mepoupe.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.getbase.floatingactionbutton.AddFloatingActionButton;

import java.util.List;

import br.com.megaapps.mepoupe.Adapter.ObjectiveAdapter;
import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Model.ObjectiveModel;
import br.com.megaapps.mepoupe.Presenter.ObjectiveFragmentPresenter;
import br.com.megaapps.mepoupe.R;

public class ObjectiveFragment extends MyFragment {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private String id;

    private ObjectiveFragmentPresenter objectiveFragmentPresenter;
    private ObjectiveAdapter objectiveAdapter;

    private ListView listViewObjects;
    private AddFloatingActionButton btnAdd;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_objective, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        id = pref.getString("uid", "");

        objectiveFragmentPresenter = new ObjectiveFragmentPresenter(this);

        listViewObjects = (ListView) getActivity().findViewById(R.id.listViewObjects);

        objectiveFragmentPresenter.getDataObjectiveView(id);

        btnAdd = (AddFloatingActionButton) getActivity().findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeactivity = (HomeActivity) getActivity();
                ObjectiveAddFragment frag = new ObjectiveAddFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("add goal");
                ft.replace(R.id.frContent,frag);
                ft.commit();

            }
        });
    }

    public void loadRecipe(List<ObjectiveModel> objetiveList) {

        objectiveAdapter = new ObjectiveAdapter(getContext(), objetiveList);
        listViewObjects.setAdapter(objectiveAdapter);

    }
}
