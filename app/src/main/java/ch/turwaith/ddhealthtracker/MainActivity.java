package ch.turwaith.ddhealthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    EditText characterNameEditText;
    EditText maxHpEditText;

    String characterName;
    String initialHP;

    public void startTracker(View view){

        if (characterNameEditText.getText().toString().isEmpty()){
            Snackbar characterName_empty = Snackbar.make(view, "Please enter a character name", BaseTransientBottomBar.LENGTH_LONG);
            characterName_empty.show();
            return;
        }

        hideKeyboard(view);

        if (maxHpEditText.getText().toString().isEmpty()){
            Snackbar hp_empty = Snackbar.make(view, "Please enter an initial HP", BaseTransientBottomBar.LENGTH_LONG);
            hp_empty.show();
            return;
        }

        characterName = characterNameEditText.getText().toString();
        initialHP = maxHpEditText.getText().toString();

        Intent intent = new Intent(getApplicationContext(), TrackerActivity.class);
        intent.putExtra("characterName", characterName);
        intent.putExtra("initialHP", initialHP);
        startActivity(intent);
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.button_start);
        characterNameEditText = findViewById(R.id.editTextPersonName);
        maxHpEditText = findViewById(R.id.editTextMaxHp);
    }
}