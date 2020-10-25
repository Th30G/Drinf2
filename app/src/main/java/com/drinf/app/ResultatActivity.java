package com.drinf.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultatActivity extends AppCompatActivity {

    private Formulaire formulaire;

    private Condamnation condamnation;

    private TextView textViewAmende;
    private TextView textViewPoints;
    private TextView textViewPrison;
    private TextView textViewSuspension;
    private TextView textViewImmobilisation;
    private TextView textViewStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        formulaire = new Formulaire(extras.getDouble("taux", 0.0), extras.getBoolean("probatoire", false), extras.getBoolean("recidive", false));
        condamnation = new Condamnation(formulaire);
        condamnation.genererCondamnation();
        textViewAmende = findViewById(R.id.textViewAmende);
        textViewPoints = findViewById(R.id.textViewPoint);
        textViewPrison = findViewById(R.id.textViewPrison);
        textViewSuspension = findViewById(R.id.textViewSuspensionPermis);
        textViewImmobilisation = findViewById(R.id.textViewImmobilisation);
        textViewStage = findViewById(R.id.textViewSensibilisation);
        textViewAmende.setText(condamnation.getAmende() + " €");
        if (condamnation.getRetraitDePoint() > 1) textViewPoints.setText(condamnation.getRetraitDePoint() + " points");
        else textViewPoints.setText(condamnation.getRetraitDePoint() + " point");
        if (condamnation.getPeinePrison() > 1) textViewPrison.setText(condamnation.getPeinePrison() + " ans");
        else textViewPrison.setText(condamnation.getPeinePrison() + " an");
        if (condamnation.isSuspensionPermis()) textViewSuspension.setText("OUI");
        else textViewSuspension.setText("NON");
        if (condamnation.isImmobilisationVehicule()) textViewImmobilisation.setText("OUI");
        else textViewImmobilisation.setText("NON");
        if (condamnation.isStageSensibilisation()) textViewStage.setText("OUI");
        else textViewStage.setText("NON");
    }
}