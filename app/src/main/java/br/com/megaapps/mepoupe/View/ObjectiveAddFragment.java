package br.com.megaapps.mepoupe.View;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Locale;

import br.com.megaapps.mepoupe.Extendables.MyFragment;
import br.com.megaapps.mepoupe.Presenter.ObjectiveAddFragmentPrensenter;
import br.com.megaapps.mepoupe.R;
import br.com.megaapps.mepoupe.Util.ActivityResultBus;
import br.com.megaapps.mepoupe.Util.ActivityResultEvent;

import static android.app.Activity.RESULT_OK;

/**
 * Created by duh on 6/1/17.
 */

public class ObjectiveAddFragment extends MyFragment {

    private EditText editValue;
    private EditText editNameGoal;
    private TextView mic;
    private Button btnSave;
    private TextInputLayout input_layout_name_goal;

    private ObjectiveAddFragmentPrensenter objectiveAddFragmentPrensenter;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private final int REQ_CODE_SPEECH_OUTPUT = 143;

    private String id;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_objective_add, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        objectiveAddFragmentPrensenter =  new ObjectiveAddFragmentPrensenter(this);

        input_layout_name_goal = (TextInputLayout) getActivity().findViewById(R.id.input_layout_name_goal);

        editValue = (EditText) getActivity().findViewById(R.id.editValue);
        editNameGoal = (EditText) getActivity().findViewById(R.id.editNameGoal);
        mic = (TextView) getActivity().findViewById(R.id.mic);
        btnSave = (Button) getActivity().findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pref = getContext().getSharedPreferences("login.conf", Context.MODE_PRIVATE);
                id = pref.getString("uid", "");

                objectiveAddFragmentPrensenter.getDataObject(id, editValue.getText().toString(), editNameGoal.getText().toString());


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



    }

    public void valueEmpty() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Atenção");
        builder.setMessage("Preencha o valor");
        builder.show();
        editValue.requestFocus();

    }

    public void nameGoalEmpty() {

        input_layout_name_goal.setError("Preencha o campo nome do objetivo");
        editNameGoal.requestFocus();

    }

    public void saveOK() {

        editValue.setText("");
        editNameGoal.setText("");

        Toast.makeText(getContext(), "Seu objetivo foi incluído com sucesso!", Toast.LENGTH_SHORT).show();

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
                    editNameGoal.setText(voiceInText.get(0));

                }
                break;
            }
        }

    }
}