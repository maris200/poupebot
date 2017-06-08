package br.com.megaapps.mepoupe.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import br.com.megaapps.mepoupe.Extendables.MyFragActivity;
import br.com.megaapps.mepoupe.Presenter.LoginPresenter;
import br.com.megaapps.mepoupe.R;

public class LoginActivity extends MyFragActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private TextInputLayout input_layout_pass;
    private TextInputLayout input_layout_email;
    private EditText editEmail;
    private EditText editPass;
    private TextView txtAdd;
    private TextView txtRecover;
    private Button btnEnter;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);

        txtAdd = (TextView)findViewById(R.id.txtAdd);
        txtRecover = (TextView)findViewById(R.id.txtRecover);

        input_layout_pass = (TextInputLayout)findViewById(R.id.input_layout_pass);
        input_layout_email = (TextInputLayout)findViewById(R.id.input_layout_email);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPass = (EditText)findViewById(R.id.editPass);
        btnEnter = (Button)findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginPresenter.getDateLogin(editEmail.getText().toString(), editPass.getText().toString());
            }
        });

        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        txtRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, ForgotActivity.class));

            }
        });


    }

    public void emptyEmail() {
        input_layout_email.setError("Preencha o campo email");
        editEmail.requestFocus();

    }
    public void emptyPass() {
        input_layout_pass.setError("Preencha o campo senha");
        editPass.requestFocus();
    }

    public void cadastroOk(String id, String name){

        pref = getApplicationContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("email", editEmail.getText().toString());
        editor.putString("password", editPass.getText().toString());
        editor.putString("uid", id);
        editor.putString("name", name);
        editor.apply();

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));

    }

    public void cadastroErro(){
        Toast.makeText(this, "Erro ao efetuar o login", Toast.LENGTH_SHORT).show();
    }
    public void cadastroNo(){
        Toast.makeText(this, "Utilize outro email", Toast.LENGTH_SHORT).show();
    }
}

