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
import br.com.megaapps.mepoupe.Adapter.ExpenseAdapter;
import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Model.InputModel;
import br.com.megaapps.mepoupe.Presenter.ExpenseViewPresenter;
import br.com.megaapps.mepoupe.R;

/**
 * Created by duh on 31/05/17.
 */

public class ExpenseViewFragment extends MyFragment {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private String id;

    private ExpenseViewPresenter expenseViewPresenter;
    private ExpenseAdapter expenseAdapter;

    private ListView listExpense;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_expense, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        id = pref.getString("uid", "");

        expenseViewPresenter = new ExpenseViewPresenter(this);

        listExpense = (ListView) getActivity().findViewById(R.id.listExpense);

        expenseViewPresenter.getDataExpenseView(id);
    }

    public void loadExpense(List<InputModel> outputList) {

        expenseAdapter = new ExpenseAdapter(getContext(), outputList);
        listExpense.setAdapter(expenseAdapter);
    }
}