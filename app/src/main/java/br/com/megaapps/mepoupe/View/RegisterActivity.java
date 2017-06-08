package br.com.megaapps.mepoupe.View;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.megaapps.mepoupe.Extendables.MyFragActivity;
import br.com.megaapps.mepoupe.Presenter.RegisterPresenter;
import br.com.megaapps.mepoupe.R;

public class RegisterActivity extends MyFragActivity {

    private TextInputLayout input_layout_pass;
    private TextInputLayout input_layout_email;
    private TextInputLayout input_layout_name;
    private TextInputLayout input_layout_age;
    private EditText editName;
    private EditText editEmail;
    private EditText editAge;
    private EditText editPass;
    private Button btnSave;

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter = new RegisterPresenter(this);

        input_layout_pass = (TextInputLayout)findViewById(R.id.input_layout_pass);
        input_layout_email = (TextInputLayout)findViewById(R.id.input_layout_email);
        input_layout_name = (TextInputLayout)findViewById(R.id.input_layout_name);
        input_layout_age = (TextInputLayout)findViewById(R.id.input_layout_age);
        editName = (EditText)findViewById(R.id.editName);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPass = (EditText)findViewById(R.id.editPass);
        editAge = (EditText)findViewById(R.id.editAge);
        btnSave = (Button)findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerPresenter.getDataUser(
                        editName.getText().toString(),
                        editAge.getText().toString(),
                        editEmail.getText().toString(),
                        editPass.getText().toString());

            }
        });

    }
    public void emptyName() {
        input_layout_name.setError("Preencha o campo nome");
        editName.requestFocus();

    }
    public void emptyAge() {
        input_layout_pass.setError("Preencha o campo idade");
        editAge.requestFocus();
    }

    public void emptyEmail() {
        input_layout_email.setError("Preencha o campo email");
        editEmail.requestFocus();

    }
    public void emptyPass() {
        input_layout_pass.setError("Preencha o campo senha");
        editPass.requestFocus();
    }

    public void cadastroOk(){
        Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
        finish();
    }

    public void cadastroErro(){
        Toast.makeText(this, "Erro ao cadastrar usuário", Toast.LENGTH_LONG).show();
    }
    public void cadastroNo(){
        Toast.makeText(this, "Usuário não cadastrado", Toast.LENGTH_LONG).show();
    }
}

