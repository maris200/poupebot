package br.com.megaapps.mepoupe.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Presenter.EditPassPresenter;
import br.com.megaapps.mepoupe.R;

public class EditPasswordFragment extends MyFragment {

    private TextInputLayout input_layout_acpass;
    private TextInputLayout input_layout_newpass;
    private TextInputLayout input_layout_pass;

    private EditText txtAcpass;
    private EditText txtNewpass;
    private EditText txtPass;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private String id;

    private Button btnSave;

    private EditPassPresenter editPassPresenter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_password, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editPassPresenter = new EditPassPresenter(this);

        input_layout_acpass = (TextInputLayout) getActivity().findViewById(R.id.input_layout_acpass);
        input_layout_newpass = (TextInputLayout) getActivity().findViewById(R.id.input_layout_newpass);
        input_layout_pass = (TextInputLayout) getActivity().findViewById(R.id.input_layout_pass);

        txtAcpass = (EditText) getActivity().findViewById(R.id.txtAcpass);
        txtNewpass = (EditText) getActivity().findViewById(R.id.txtNewpass);
        txtPass = (EditText) getActivity().findViewById(R.id.txtPass);

        btnSave = (Button) getActivity().findViewById(R.id.btnSave);

        pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        id = pref.getString("uid", "");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String actual = String.valueOf(txtAcpass.getText());
                String newPass = String.valueOf(txtNewpass.getText());
                String pass = String.valueOf(txtPass.getText());

                editPassPresenter.getDataUpdatePass(id, actual, newPass, pass);

            }
        });
    }

    public void emptyActual() {
        input_layout_acpass.setError("Preencha sua senha atual");
        txtAcpass.requestFocus();
    }

    public void emptyNewPass() {
        input_layout_newpass.setError("Preencha com sua nova senha");
        txtNewpass.requestFocus();
    }

    public void emptyConfirm() {
        input_layout_pass.setError("Confirme sua nova senha");
        txtPass.requestFocus();
    }

    public void updatePassOK() {

        txtAcpass.setText("");
        txtNewpass.setText("");
        txtPass.setText("");

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Atenção");
        builder.setMessage("Dados atualizados com sucesso!");
        builder.show();

    }

    public void passNotEquals() {
        Toast.makeText(getContext(), "As senhas não conferem tente novamente", Toast.LENGTH_SHORT).show();
    }
}
