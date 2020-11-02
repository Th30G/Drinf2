package com.drinf.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTaux;

    private Button validateButton;

    private LinearLayout tauxLayout;
    private LinearLayout probatoireLayout;
    private LinearLayout recidiveLayout;

    private CheckBox checkBoxPermisProbatoire;
    private CheckBox checkBoxRecidive;

    private Formulaire formulaire;

    private boolean formulaireEnded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        formulaire = new Formulaire();
        tauxLayout = findViewById(R.id.tauxLayout);
        probatoireLayout = findViewById(R.id.probatoireLayout);
        recidiveLayout = findViewById(R.id.recidiveLayout);
        validateButton = findViewById(R.id.validateButton);
        editTextTaux = findViewById(R.id.editTextTaux);
        checkBoxPermisProbatoire = findViewById(R.id.checkBoxPermisProbatoire);
        checkBoxRecidive = findViewById(R.id.checkBoxRecidive);
        editTextTaux.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    setTaux(Float.valueOf(editTextTaux.getText().toString()));
                    hideKeyboard();
                    editTextTaux.clearFocus();
                    return true;
                }
                return false;
            }
        });
        probatoireLayout.setVisibility(View.GONE);
        recidiveLayout.setVisibility(View.GONE);
        changeButtonState(false);
    }

    public void setTaux(float taux) {
        formulaire.setTaux(taux);
        if (taux >= 0.2 && taux < 0.5) {
            probatoireLayout.setVisibility(View.VISIBLE);
            recidiveLayout.setVisibility(View.GONE);
            changeButtonState(true);
            setProbatoire(checkBoxPermisProbatoire.isChecked());
        } else if (formulaire.getTaux() >= 0.8) {
            recidiveLayout.setVisibility(View.VISIBLE);
            probatoireLayout.setVisibility(View.GONE);
            setRecidive(checkBoxRecidive.isChecked());
        }
        else {
            probatoireLayout.setVisibility(View.GONE);
            recidiveLayout.setVisibility(View.GONE);
            changeButtonState(true);
        }
    }

    public void setProbatoire(View view) {
        setTaux(Float.valueOf(editTextTaux.getText().toString()));
        setProbatoire(checkBoxPermisProbatoire.isChecked());
    }

    public void setProbatoire(boolean probatoire) {
        formulaire.setProbatoire(probatoire);
        changeButtonState(true);
    }

    public void setRecidive(View view) {
        setTaux(Float.valueOf(editTextTaux.getText().toString()));
        setRecidive(checkBoxRecidive.isChecked());
    }

    public void setRecidive(boolean recidive) {
        formulaire.setRecidive(recidive);
        changeButtonState(true);
    }

    public void changeButtonState(boolean state) {
        validateButton.setClickable(state);
        formulaireEnded = state;
        if (state) validateButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#3F51B5")));
        else validateButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5E5E5E")));
    }

    public void generateResultat(View view) {
        generateResultat();
    }

    public void generateResultat() {
        Intent intent = new Intent(this, ResultatActivity.class);
        intent.putExtra("taux", formulaire.getTaux());
        if (probatoireLayout.getVisibility() == View.VISIBLE) intent.putExtra("probatoire", formulaire.isProbatoire());
        else intent.putExtra("probatoire", false);
        if (recidiveLayout.getVisibility() == View.VISIBLE) intent.putExtra("recidive", formulaire.isRecidive());
        else intent.putExtra("recidive", false);
        startActivity(intent);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        View f = this.getCurrentFocus();
        if (null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom(f.getClass()))
            imm.hideSoftInputFromWindow(f.getWindowToken(), 0);
        else
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}