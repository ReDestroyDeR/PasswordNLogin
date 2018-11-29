package red.com.passwordinput;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class RegisterListener implements View.OnClickListener {

    private RegisterActivity registerActivity;
    private EditText pass, login;

    private Intent forResult;

    private ArrayList<String> lpList;

    private FrequentCalls faq = new FrequentCalls();

    public RegisterListener(RegisterActivity ra) {
        registerActivity = ra;

        // Getting references
        pass = registerActivity.findViewById(R.id.passRegField);
        login = registerActivity.findViewById(R.id.loginRegField);
        Intent caller = registerActivity.getIntent();

        // Initializing values
        forResult = new Intent();
        lpList = caller.getStringArrayListExtra("lpArray");

        login.setText(caller.getStringExtra("loginString"));
    }

    @Override
    public void onClick(View v) {
        String loginStr = login.getText().toString();
        String passStr = pass.getText().toString();

        // Dummy test
        if (loginStr.matches("") || passStr.matches(""))
            return;

        boolean loginExists = faq.checkForMatches(lpList, ":", 0, loginStr);
        if (loginExists) {
            forResult.putExtra("output",
                    "User with this name already exists, returned to login screen");
            registerActivity.setResult(Activity.RESULT_CANCELED, forResult);
            registerActivity.finish();
            return;
        }

        // For Security Measures
        if (passStr.length() < 4) {
            pass.setText("");
            pass.setHint("Insecure!");
            return;
        }

        forResult.putExtra("newUserData", loginStr + ":" + passStr);
        registerActivity.setResult(Activity.RESULT_OK, forResult);
        registerActivity.finish();
    }
}