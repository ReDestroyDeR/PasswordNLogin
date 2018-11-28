package red.com.passwordinput;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ButtonListener implements View.OnClickListener {

    private MainActivity mainActivity;
    private TextView output;
    private EditText pass, login;
    private ValueAnimator valueAnimator;

    private int ye, no;
    private String corr, neCorr;

    public ButtonListener(MainActivity ma) {
        mainActivity = ma;

        // Getting references
        output = mainActivity.findViewById(R.id.output);
        pass = mainActivity.findViewById(R.id.passField);
        login = mainActivity.findViewById(R.id.loginField);

        // Initializing animations
        valueAnimator = ObjectAnimator.ofFloat(output,
                "alpha",
                 0.0f, 255.0f);
        valueAnimator.setDuration(10000);

        // Value initialization
        no = ContextCompat.getColor(mainActivity.getApplicationContext(), R.color.output_n);
        ye = ContextCompat.getColor(mainActivity.getApplicationContext(), R.color.output_y);
        corr = mainActivity.getString(R.string.output_y);
        neCorr = mainActivity.getString(R.string.output_n);
    }

    @Override
    public void onClick(View v) {
        // Dummy test
        if (login.getText().toString().matches("") ||
            pass.getText().toString().matches(""))
            return;

        if (login.getText().toString().matches(mainActivity.login) &&
                pass.getText().toString().matches(mainActivity.password)) {
            output.setText(corr);
            output.setTextColor(ye);
        }
        else {
            output.setText(neCorr);
            output.setTextColor(no);
        }

        login.setText("");
        pass.setText("");

        valueAnimator.start();
    }
}
