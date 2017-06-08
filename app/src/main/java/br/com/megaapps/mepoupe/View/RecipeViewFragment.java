package br.com.megaapps.mepoupe.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.List;
import br.com.megaapps.mepoupe.Adapter.RecipeAdapter;
import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Presenter.RecipeViewPresenter;
import br.com.megaapps.mepoupe.R;

/**
 * Created by duh on 31/05/17.
 */

public class RecipeViewFragment extends MyFragment {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private String id;

    private RecipeViewPresenter recipeViewPresenter;
    private RecipeAdapter recipeAdapter;

    private ListView listRecipe;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_recipe, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        id = pref.getString("uid", "");

        recipeViewPresenter = new RecipeViewPresenter(this);

        listRecipe = (ListView) getActivity().findViewById(R.id.listRecipe);

        recipeViewPresenter.getDataRecipeView(id);

    }

    public void loadRecipe(List<InputModel> inputList) {

        recipeAdapter = new RecipeAdapter(getContext(), inputList);
        listRecipe.setAdapter(recipeAdapter);
    }
}
