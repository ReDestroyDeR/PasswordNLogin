package red.com.passwordinput;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class ButtonListener implements View.OnClickListener {

    private MainActivity mainActivity;
    private TextView output;
    private EditText pass, login;
    private ValueAnimator valueAnimator;

    private int ye, no;
    private String corr, neCorr;

    private FrequentCalls faq = new FrequentCalls();

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
        String loginStr = login.getText().toString();
        String passStr = pass.getText().toString();

        // Dummy test
        if (loginStr.matches(""))
            return;

        // Login & Register
        boolean onlyLoginExists = faq.checkForMatches(mainActivity.CREDENTIALS, ":",
                                                   0, loginStr);
        boolean matches = faq.checkForMatches(mainActivity.CREDENTIALS,":",
                                              new String[] {loginStr, passStr});

        if (matches) {
            output.setText(corr);
            output.setTextColor(ye);
        } else if (!onlyLoginExists) {
            Intent register = new Intent(mainActivity, RegisterActivity.class);
            register.putExtra("loginString", loginStr);
            register.putExtra("lpArray", mainActivity.CREDENTIALS);
            mainActivity.startActivityForResult(register, 105);
        } else {
            output.setText(neCorr);
            output.setTextColor(no);
        }

        login.setText("");
        pass.setText("");

        valueAnimator.start();
    }
}
