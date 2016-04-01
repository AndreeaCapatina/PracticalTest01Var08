package ro.pub.cs.system.eim.practivaltest01var08;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var08SecondaryActivity extends ActionBarActivity {


    private TextView stringTextView = null;
    private Button verifyButton = null;
    private Button cancelButton = null;
    private int SECONDARY_ACTIVITY_REQUEST_CODE = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.verify_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_secondary);

        stringTextView = (TextView)findViewById(R.id.string_text_view);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("text")) {
            String completeText = intent.getStringExtra("text");
            stringTextView.setText(completeText);
        }

        verifyButton = (Button)findViewById(R.id.verify_button);
        verifyButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(buttonClickListener);
    }



}
