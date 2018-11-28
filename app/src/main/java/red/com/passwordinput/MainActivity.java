package red.com.passwordinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    final String login = "root";
    final String password = "4312a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonListener bl = new ButtonListener(this);
        findViewById(R.id.checkButton).setOnClickListener(bl);
    }


}
