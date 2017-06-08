package br.com.megaapps.mepoupe.View;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Locale;

import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Presenter.InputExpenseFragmentPresenter;
import br.com.megaapps.mepoupe.R;
import br.com.megaapps.mepoupe.Util.ActivityResultBus;
import br.com.megaapps.mepoupe.Util.ActivityResultEvent;
import br.com.megaapps.mepoupe.Util.DatePickerFragment;

import static android.app.Activity.RESULT_OK;

public class InputExpenseFragment extends MyFragment {

    private EditText editValue;
    private EditText editDate;
    private EditText editCategory;
    private EditText editDescription;

    private TextView mic;

    private TextInputLayout input_layout_date;
    private TextInputLayout input_layout_category;
    private TextInputLayout input_layout_description;

    private LinearLayout lay_helth;
    private LinearLayout lay_food;
    private LinearLayout lay_transport;
    private LinearLayout lay_recreation;
    private LinearLayout lay_house;
    private LinearLayout lay_bills;
    private LinearLayout lay_nothing;

    private Button btnSend;

    private InputExpenseFragmentPresenter inputExpenseFragmentPresenter;

    private AlertDialog dialog;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private final int REQ_CODE_SPEECH_OUTPUT = 143;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_expense, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mic = (TextView)getActivity().findViewById(R.id.mic);

        inputExpenseFragmentPresenter = new InputExpenseFragmentPresenter(this);
        editValue = (EditText)getActivity().findViewById(R.id.editValue);
        editDate = (EditText)getActivity().findViewById(R.id.editDate);
        editCategory = (EditText)getActivity().findViewById(R.id.editCategory);
        editDescription = (EditText)getActivity().findViewById(R.id.editDescription);
        editValue.requestFocus();

        input_layout_date = (TextInputLayout)getActivity().findViewById(R.id.input_layout_date);
        input_layout_category = (TextInputLayout)getActivity().findViewById(R.id.input_layout_category);
        input_layout_description = (TextInputLayout)getActivity().findViewById(R.id.input_layout_description);

        btnSend = (Button)getActivity().findViewById(R.id.btnSend);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.FragmentManager fm = getActivity().getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(fm, "dateTwo");
            }
        });

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                            /*
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.RECORD_AUDIO}, REQ_CODE_SPEECH_OUTPUT);
                }*/
                openMic();

            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
                String id = pref.getString("uid", "");
                String value = String.valueOf(editValue.getText());
                String date = String.valueOf(editDate.getText());
                String category = String.valueOf(editCategory.getText());
                String description = String.valueOf(editDescription.getText());

                inputExpenseFragmentPresenter.getExpenseData(id, value, date, category, description);
            }
        });

        editCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_input_expense, null);
                lay_helth = (LinearLayout) mView.findViewById(R.id.lay_helth);
                lay_food = (LinearLayout) mView.findViewById(R.id.lay_food);
                lay_transport = (LinearLayout) mView.findViewById(R.id.lay_transport);
                lay_recreation = (LinearLayout) mView.findViewById(R.id.lay_recreation);
                lay_house = (LinearLayout) mView.findViewById(R.id.lay_house);
                lay_bills = (LinearLayout) mView.findViewById(R.id.lay_bills);
                lay_nothing = (LinearLayout) mView.findViewById(R.id.lay_nothing);

                lay_helth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        editCategory.setText("");
                        editCategory.setText("Saúde");
                        dialog.dismiss();

                    }
                });

                lay_food.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editCategory.setText("");
                        editCategory.setText("Alimentação");
                        dialog.dismiss();
                    }
                });

                lay_transport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editCategory.setText("");
                        editCategory.setText("Transporte");
                        dialog.dismiss();
                    }
                });

                lay_recreation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editCategory.setText("");
                        editCategory.setText("Lazer");
                        dialog.dismiss();
                    }
                });

                lay_house.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editCategory.setText("");
                        editCategory.setText("Moradia");
                        dialog.dismiss();
                    }
                });

                lay_bills.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editCategory.setText("");
                        editCategory.setText("Contas");
                        dialog.dismiss();
                    }
                });

                lay_nothing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editCategory.setText("");
                        editCategory.setText("Sem categoria");
                        dialog.dismiss();
                    }
                });

                mBuilder.setView(mView);
                dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    public void emptyValue() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Atenção");
        builder.setMessage("Preencha o valor");
        builder.show();
        editValue.requestFocus();
    }

    public void emptyDate() {

        input_layout_date.setError("Preencha o campo data");
        editDate.requestFocus();
    }

    public void emptyCategory() {

        input_layout_category.setError("Selecione a categoria");
        editCategory.requestFocus();
    }

    public void emptyDescription() {

        input_layout_description.setError("Preencha a descrição");
        editDescription.requestFocus();
    }

    public void expensiveOK(){

        editValue.setText("");
        editDate.setText("");
        editCategory.setText("");
        editDescription.setText("");
        editValue.requestFocus();

        Toast.makeText(getContext(), "Sua despesa incluída com sucesso!", Toast.LENGTH_SHORT).show();

    }

    public void expensiveError() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Atenção");
        builder.setMessage("Erro ao incluir receita");
        builder.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        ActivityResultBus.getInstance().register(mActivityResultSubscriber);
    }

    @Override
    public void onStop() {
        super.onStop();
        ActivityResultBus.getInstance().unregister(mActivityResultSubscriber);
    }

    private Object mActivityResultSubscriber = new Object() {
        @Subscribe
        public void onActivityResultReceived(ActivityResultEvent event) {
            int requestCode = event.getRequestCode();
            int resultCode = event.getResultCode();
            Intent data = event.getData();
            onActivityResult(requestCode, resultCode, data);
        }
    };

    private void openMic() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale Agora!!!");

        try{
            getActivity().startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);

        }catch (ActivityNotFoundException tim){

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case  REQ_CODE_SPEECH_OUTPUT:{

                if(resultCode == RESULT_OK && null != data){

                    ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    editDescription.setText(voiceInText.get(0));

                }
                break;
            }
        }

    }
}
