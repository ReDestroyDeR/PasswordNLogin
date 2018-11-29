package red.com.passwordinput;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> CREDENTIALS = new ArrayList<>(); // Надоело
                                                              // с листами тупо проще,
    private TextView outputTextView;                          // не хочу изобретать колесо

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonListener bl = new ButtonListener(this);
        findViewById(R.id.checkButton).setOnClickListener(bl);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        outputTextView = findViewById(R.id.output);

        switch (resultCode) {

            // Account registered
            case RESULT_OK:
                outputTextView.setText(getString(R.string.regAccSuc));
                outputTextView.setTextColor(ContextCompat
                        .getColor(getApplicationContext(),
                                R.color.output_y));
                CREDENTIALS.add(data.getStringExtra("newUserData"));
                break;

            // Fail Result
            default:
                outputTextView.setText(data.getStringExtra("output"));
                outputTextView.setTextColor(ContextCompat
                        .getColor(getApplicationContext(),
                                R.color.output_n));
        }
    }

}
