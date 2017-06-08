package br.com.megaapps.mepoupe.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Presenter.ProfilePresenter;
import br.com.megaapps.mepoupe.R;

public class ProfileFragment extends MyFragment {

    private TextInputLayout input_layout_name;
    private TextInputLayout input_layout_email;
    private TextInputLayout input_layout_age;

    private EditText txtName;
    private EditText txtEmail;
    private EditText txtAge;
    private TextView txtExit;

    private TextView txtChangePass;

    private Switch btnswitch;

    private Button btnSave;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private String id;

    private String email;
    private String password;

    private ProfilePresenter profilePresenter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profilePresenter = new ProfilePresenter(this);

        txtChangePass = (TextView) getActivity().findViewById(R.id.txtChangePass);

        input_layout_name = (TextInputLayout) getActivity().findViewById(R.id.input_layout_name);
        input_layout_email = (TextInputLayout) getActivity().findViewById(R.id.input_layout_email);
        input_layout_age = (TextInputLayout) getActivity().findViewById(R.id.input_layout_age);

        txtName = (EditText) getActivity().findViewById(R.id.txtName);
        txtEmail = (EditText) getActivity().findViewById(R.id.txtEmail);
        txtAge = (EditText) getActivity().findViewById(R.id.txtAge);
        txtExit = (TextView) getActivity().findViewById(R.id.txtExit);

        btnswitch = (Switch) getActivity().findViewById(R.id.btnswitch);

        btnSave = (Button) getActivity().findViewById(R.id.btnSave);

        pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        id = pref.getString("uid", "");
        email = pref.getString("email", "");
        password = pref.getString("password", "");

        txtName.setEnabled(false);
        txtEmail.setEnabled(false);
        txtAge.setEnabled(false);
        btnSave.setVisibility(View.GONE);

        btnswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    txtName.setEnabled(true);
                    txtEmail.setEnabled(true);
                    txtAge.setEnabled(true);
                    btnSave.setVisibility(View.VISIBLE);
                    txtName.requestFocus();

                    Toast.makeText(getContext(), "Habilitado para edição!", Toast.LENGTH_SHORT).show();

                }else{

                    btnSave.setVisibility(View.GONE);
                    txtName.setEnabled(false);
                    txtEmail.setEnabled(false);
                    txtAge.setEnabled(false);

                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = String.valueOf(txtName.getText());
                String age = String.valueOf(txtAge.getText());
                String email = String.valueOf(txtEmail.getText());

                profilePresenter.getDataUpdate(id, name, age, email);

                btnswitch.setChecked(false);
                txtName.setEnabled(false);
                txtEmail.setEnabled(false);
                txtAge.setEnabled(false);

            }
        });
        profilePresenter.getIdUser(id);

        txtChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeactivity = (HomeActivity) getActivity();
                EditPasswordFragment frag = new EditPasswordFragment();
                FragmentTransaction ft = homeactivity.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("pass_edit");
                ft.replace(R.id.frContent,frag);
                ft.commit();
            }
        });
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();


            }
        });
    }

    public void load(String name, String email, String age ) {

        txtName.setText(name);
        txtEmail.setText(email);
        txtAge.setText(age);
    }


    public void emptyName() {
        input_layout_name.setError("Preencha o campo nome");
        txtName.requestFocus();
    }

    public void emptyEmail() {
        input_layout_email.setError("Preencha o campo email");
        txtEmail.requestFocus();
    }

    public void emptyAge() {
        input_layout_age.setError("Preencha o campo idade");
        txtAge.requestFocus();
    }

    public void updateOK() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Atenção");
        builder.setMessage("Dados atualizados com sucesso!");
        builder.show();
    }
}
