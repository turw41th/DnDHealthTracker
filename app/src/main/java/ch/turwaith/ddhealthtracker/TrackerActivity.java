package ch.turwaith.ddhealthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class TrackerActivity extends AppCompatActivity {

    TextView characterNameTextView;
    TextView hpTextView;
    TextView maxHpTextView;
    TextView combinedHpTextView;

    int initialHP;

    int normalHp;
    int tempHp;


    public String getNormalHP() {
        return Integer.toString(normalHp);
    }
    public String getTempHp(){
        return Integer.toString(tempHp);
    }

    public String getCombinedHp(){
        return Integer.toString(normalHp + tempHp);
    }

    public void setHpInView(){
        String hpText;
        if (tempHp > 0){
            hpText = getCombinedHp() + " HP";
            combinedHpTextView.setVisibility(View.VISIBLE);
            combinedHpTextView.setText("(" + getNormalHP() + " regular HP + " + getTempHp() + " temporary HP)");
        } else {
            combinedHpTextView.setVisibility(View.INVISIBLE);
            hpText = getCombinedHp() + " HP";
            tempHp = 0;
        }

        hpTextView.setText(hpText);
    }

    public void resetToInitialHp(View view){
        normalHp = initialHP;
        tempHp = 0;
        setHpInView();
    }

    public void addOneToHP(View view){
        if (normalHp + 1 > initialHP){
            Snackbar hp_empty = Snackbar.make(view, "You can not heal above your maximum HP!", BaseTransientBottomBar.LENGTH_LONG);
            hp_empty.show();
            return;
        }

        normalHp += 1;
        setHpInView();
    }
    public void addFiveToHP(View view){
        if (normalHp + 5 > initialHP){
            Snackbar hp_empty = Snackbar.make(view, "You can not heal above your maximum HP!", BaseTransientBottomBar.LENGTH_LONG);
            hp_empty.show();
            return;
        }

        normalHp += 5;
        setHpInView();
    }
    public void subtractOneFromHP(View view){
        if (tempHp > 0){
            tempHp -= 1;
        } else {
            normalHp -= 1;
        }
        setHpInView();
    }
    public void subtractFiveFromHP(View view){

        for (int i = 5; i > 0 ; i--) {
            if (tempHp > 0){
                tempHp -= 1;
            } else {
                normalHp -= 1;
            }
        }
        setHpInView();
    }
    public void addOneToTempHp(View view){
        tempHp += 1;
        setHpInView();
    }
    public void subtractOneFromTempHp(View view){
        if (tempHp == 0) return;
        tempHp -= 1;
        setHpInView();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        normalHp = 0;
        tempHp = 0;

        combinedHpTextView = findViewById(R.id.textView_combined_hp);

        maxHpTextView = findViewById(R.id.textView_MaxHp);

        Intent intent = getIntent();

        characterNameTextView = findViewById(R.id.textView_characterName);
        String tempCharName = intent.getStringExtra("characterName");
        characterNameTextView.setText(tempCharName);

        hpTextView = findViewById(R.id.textView_hp_counter);
        int tempHP = Integer.parseInt(intent.getStringExtra("initialHP"));
        normalHp = tempHP;
        initialHP = tempHP;

        maxHpTextView.setText("Max HP: " + initialHP);

        setHpInView();
    }
}