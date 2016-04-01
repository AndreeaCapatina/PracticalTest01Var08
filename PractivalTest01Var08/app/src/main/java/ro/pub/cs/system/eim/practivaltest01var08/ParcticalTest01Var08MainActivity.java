package ro.pub.cs.system.eim.practivaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ParcticalTest01Var08MainActivity extends ActionBarActivity {

    private TextView textView = null;
    private Button topLeftButton = null;
    private Button topRightButton = null;
    private Button bottomLeftButton = null;
    private Button bottomRightButton = null;
    private Button centerButton = null;
    private Button navigateButton = null;
    private boolean firstButton = true;

    private int nrTotal = 0;
    private int nrSuccess = 0;
    private int nrFails = 0;
    private int SECONDARY_ACTIVITY_REQUEST_CODE = 0;
    private  boolean serviceStatus = false;

    private boolean alreadyRun = false;
    private MessageBroadcastReceiver messageBroadcastReceiver = null;
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("broadcast_message");
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            Log.d("RECEIVER", message);
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String text = textView.getText().toString();
            switch (view.getId()) {
                case R.id.top_left:
                    if (firstButton == true) {
                        textView.setText(Constant.TOP_LEFT);
                        firstButton = false;
                    } else {
                        String allText = textView.getText().toString();
                        textView.setText(allText + "," + Constant.TOP_LEFT);
                    }
                    break;
                case R.id.top_right:
                    if (firstButton) {
                        textView.setText("Top Right");
                        firstButton = false;
                    } else {
                        String allText = textView.getText().toString();
                        textView.setText(allText + "," + "Top Right");
                    }
                    break;
                case R.id.center:
                    if (firstButton) {
                        textView.setText("Center");
                        firstButton = false;
                    } else {
                        String allText = textView.getText().toString();
                        textView.setText(allText + "," + "Center");
                    }
                    break;
                case R.id.bottom_left:
                    if (firstButton == true) {
                        textView.setText("Bottom Left");
                        firstButton = false;
                    } else {
                        String allText = textView.getText().toString();
                        textView.setText(allText + "," + "Bottom Left");
                    }
                    break;
                case R.id.bottom_right:
                    if (firstButton == true) {
                        textView.setText("Top Right");
                        firstButton = false;
                    } else {
                        String allText = textView.getText().toString();
                        textView.setText(allText + "," + "Bottom Right");
                    }
                    break;
                case R.id.navigate_to_secondary_activity_button:
                    nrTotal++;
                    Log.d("[IncercariTotale]", String.valueOf(nrTotal));
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08SecondaryActivity.class);
                    String completeText = textView.getText().toString();
                    intent.putExtra("text", completeText);
                    textView.setText("");
                    firstButton = true;
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
            if (nrTotal > Constant.PRAG && !serviceStatus) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
                intent.putExtra("nrTotal", nrTotal);
                getApplicationContext().startService(intent);
                serviceStatus = true;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parctical_test01_var08_main);

        textView = (TextView)findViewById(R.id.text_view);

        topLeftButton = (Button)findViewById(R.id.top_left);
        topRightButton = (Button)findViewById(R.id.top_right);
        bottomLeftButton = (Button)findViewById(R.id.bottom_left);
        bottomRightButton = (Button)findViewById(R.id.bottom_right);
        centerButton = (Button)findViewById(R.id.center);
        navigateButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);


        topLeftButton.setOnClickListener(buttonClickListener);
        topRightButton.setOnClickListener(buttonClickListener);
        bottomLeftButton.setOnClickListener(buttonClickListener);
        bottomRightButton.setOnClickListener(buttonClickListener);
        centerButton.setOnClickListener(buttonClickListener);
        navigateButton.setOnClickListener(buttonClickListener);


        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("nrTotal")) {
                nrTotal = Integer.parseInt(savedInstanceState.getString("nrTotal").toString());
            } else {
                nrTotal = 0;
            }

            if (savedInstanceState.containsKey("nrSuccess")) {
                nrSuccess = Integer.parseInt(savedInstanceState.getString("nrSuccess").toString());
            } else {
                nrSuccess = 0;
            }

            if (savedInstanceState.containsKey("nrFails")) {
                nrFails = Integer.parseInt(savedInstanceState.getString("nrFails").toString());
            } else {
                nrFails = 0;
            }

        } else {
            nrTotal = 0;
            nrSuccess = 0;
            nrFails = 0;
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("nrTotal", String.valueOf(nrTotal));
        savedInstanceState.putString("nrSuccess", String.valueOf(nrSuccess));
        savedInstanceState.putString("nrFails", String.valueOf(nrFails));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("nrTotal")) {
            nrTotal = Integer.parseInt(savedInstanceState.getString("nrTotal").toString());
        } else {
            nrTotal = 0;
        }

        if (savedInstanceState.containsKey("nrSuccess")) {
            nrSuccess = Integer.parseInt(savedInstanceState.getString("nrSuccess").toString());
        } else {
            nrSuccess = 0;
        }

        if (savedInstanceState.containsKey("nrFails")) {
            nrFails = Integer.parseInt(savedInstanceState.getString("nrFails").toString());
        } else {
            nrFails = 0;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            if (resultCode == -1) {
                nrSuccess ++;
                Log.d("[Success]", String.valueOf(nrSuccess));

            } else if(resultCode == 0) {
                nrFails ++;
                Log.d("[Fails]", String.valueOf(nrFails));

            }
        }
        if (alreadyRun == false && nrTotal > Constant.PRAG) {
            alreadyRun = true;
            intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
            //intent.putExtra("", c);
            startService(intent);
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
    }

}
