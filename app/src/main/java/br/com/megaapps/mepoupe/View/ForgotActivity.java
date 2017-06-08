package br.com.megaapps.mepoupe.View;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.megaapps.mepoupe.Extendables.MyFragActivity;
import br.com.megaapps.mepoupe.Presenter.ForgotPresenter;
import br.com.megaapps.mepoupe.Presenter.LoginPresenter;
import br.com.megaapps.mepoupe.R;

import static br.com.megaapps.mepoupe.R.id.editPass;

public class ForgotActivity extends MyFragActivity {

    private TextInputLayout input_layout_email;
    private EditText editEmail;
    private Button btnSend;

    private ForgotPresenter forgotPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        forgotPresenter = new ForgotPresenter(this);

        input_layout_email = (TextInputLayout)findViewById(R.id.input_layout_email);
        editEmail = (EditText)findViewById(R.id.editEmail);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forgotPresenter.getDateRecoverPass
                        (editEmail.getText().toString());

            }
        });


    }

    public void emptyEmail() {
        input_layout_email.setError("Preencha o campo email");
        editEmail.requestFocus();
    }

    public void recoverOk(){
        Toast.makeText(this, R.string.dialogOk, Toast.LENGTH_LONG).show();
    }

    public void recoverErro(){
        Toast.makeText(this, R.string.dialogError, Toast.LENGTH_SHORT).show();
    }
    public void recoverNo(){
        Toast.makeText(this, R.string.dialogNo, Toast.LENGTH_LONG).show();
    }
}
