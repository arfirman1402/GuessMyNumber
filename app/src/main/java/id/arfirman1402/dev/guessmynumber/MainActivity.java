package id.arfirman1402.dev.guessmynumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int minNumber, maxNumber;
    private TextView tvMainCount;
    private EditText etMainNumber;

    private static final String TAG = "MainActivity";
    private ArrayList<Integer> guessNumberList;
    private int marginNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMainCount = findViewById(R.id.tv_main_count);
        etMainNumber = findViewById(R.id.et_main_number);
    }

    public void playIt(View view) {
        String numberText = etMainNumber.getText().toString();
        guessNumberList = new ArrayList<>();
        if (!TextUtils.isEmpty(numberText)) {
            if (numberText.length() < String.valueOf(Integer.MAX_VALUE).length()) {
                minNumber = 0;
                maxNumber = ((int) Math.pow(10, numberText.length()));
                int myNumber = Integer.parseInt(numberText);
                int guessNumber;
                do {
                    guessNumber = getGuessNumber();
                    if (guessNumber < myNumber) minNumber = guessNumber;
                    else maxNumber = guessNumber;
                    guessNumberList.add(guessNumber);
                } while (myNumber != guessNumber);
                tvMainCount.setText(getString(R.string.guess_result, guessNumberList.size()));
                Log.d(TAG, "playIt: Number list = " + TextUtils.join(",", guessNumberList.toArray()) + ".");
                Log.d(TAG, "playIt: Margin Number Min and Max = " + marginNumber);
            } else {
                Toast.makeText(this, "Number was too long.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please input number first", Toast.LENGTH_SHORT).show();
        }
    }

    private int getGuessNumber() {
        int guessNumber;
        do {
            marginNumber = (maxNumber - minNumber);
            guessNumber = (int) (Math.random() * marginNumber) + minNumber;
        } while (guessNumberList.contains(guessNumber));
        Log.d(TAG, "playIt: minNumber = " + minNumber + ", maxNumber = " + maxNumber + ", guessNumber = " + guessNumber);
        return guessNumber;
    }
}
