package com.example.relativecalculator;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView outputText;
    Button butC, butPlus, butMinus, butPercent, butMult, butSplit, butEqual, butPlusMinus;
    View root, rootE;
    double tempDigit;
    final String PLUS = "plus";
    final String MINUS = "minus";
    final String PERCENT = "percent";
    final String MULTIPLEX = "multiplex";
    final String SPLIT = "split";
    String optionSelected = "";
    private static final int SETTINGS_REQUEST_CODE = 323;
    private View mainRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Drawable background = Drawable.createFromPath(SettingsActivity.getImagePathFromIntent(data));

            mainRoot = findViewById(R.id.inputButtons);
            mainRoot.setBackground(background);
        }
    }

    public void init() {
        root = findViewById(R.id.root);
        rootE = findViewById(R.id.rootE);

        Button butChange = findViewById(R.id.butChange);
        butChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (root.getVisibility() == View.VISIBLE) {
                    root.setVisibility(View.GONE);
                    rootE.setVisibility(View.VISIBLE);
                } else {
                    root.setVisibility(View.VISIBLE);
                    rootE.setVisibility(View.GONE);
                }
            }
        });

        Button butSettings = findViewById(R.id.butSettings);
        butSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(toSettings,SETTINGS_REQUEST_CODE);
            }
        });

        outputText = findViewById(R.id.outputView);
        butC = findViewById(R.id.butC);
        butPlus = findViewById(R.id.butPlus);
        butMinus = findViewById(R.id.butMinus);
        butMult = findViewById(R.id.butMultiplex);
        butSplit = findViewById(R.id.butSplit);
        butEqual = findViewById(R.id.butEqual);
        butPercent = findViewById(R.id.butPercent);
        butPlusMinus = findViewById(R.id.butPlusMinus);

        butPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputText.setText(String.valueOf((
                        -1 * Double.valueOf(outputText.getText().toString()))));
            }
        });

        butPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempDigit = Double.valueOf(outputText.getText().toString());
                outputText.setText("");
                optionSelected = PLUS;
            }
        });

        butMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempDigit = Double.valueOf(outputText.getText().toString());
                outputText.setText("");
                optionSelected = MINUS;
            }
        });

        butPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempDigit = Double.valueOf(outputText.getText().toString());
                outputText.setText("");
                optionSelected = PERCENT;
            }
        });

        butMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempDigit = Double.valueOf(outputText.getText().toString());
                outputText.setText("");
                optionSelected = MULTIPLEX;
            }
        });

        butSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempDigit = Double.valueOf(outputText.getText().toString());
                outputText.setText("");
                optionSelected = SPLIT;
            }
        });

        butEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!optionSelected.equals("")) {
                    double secondDigit = Double.valueOf(outputText.getText().toString());
                    switch (optionSelected) {
                        case PLUS: {
                            outputText.setText(String.valueOf(tempDigit + secondDigit));
                            break;
                        }
                        case MINUS: {
                            outputText.setText(String.valueOf(tempDigit - secondDigit));
                            break;
                        }
                        case PERCENT: {
                            outputText.setText(String.valueOf(tempDigit / 100 * secondDigit));
                            break;
                        }
                        case MULTIPLEX: {
                            outputText.setText(String.valueOf(tempDigit * secondDigit));
                            break;
                        }
                        case SPLIT: {
                            if (secondDigit == 0) {
                                Toast.makeText(MainActivity.this,
                                        "Error, zero split unposible", Toast.LENGTH_LONG).show();
                            } else {
                                outputText.setText(String.valueOf(tempDigit / secondDigit));
                            }
                            break;
                        }
                    }
                }
            }
        });
        butC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionSelected = "";
                outputText.setText("");
            }
        });

    }

    public void btnPress(View view) {
        Button btn = (Button) view;
        outputText.append(btn.getText());
    }

}
