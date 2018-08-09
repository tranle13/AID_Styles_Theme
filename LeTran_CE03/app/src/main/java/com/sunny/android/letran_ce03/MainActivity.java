
    // Name: Tran Le
    // AID - 1808
    // File name: MainActivity.java

package com.sunny.android.letran_ce03;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout = null;
    private RadioGroup radioGroup_1 = null;
    private RadioGroup radioGroup_2 = null;
    private TextView frameLayoutText = null;
    private EditText changeText = null;
    private Switch bold = null;
    private static final String TAG = "hello";
    private int chosenColor = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewWithOrientation();
        radioGroup_1.setOnCheckedChangeListener(radioGroupCheckedChanged);
        radioGroup_2.setOnCheckedChangeListener(radioGroupCheckedChanged);
        bold.setOnCheckedChangeListener(switchChanged);

        changeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeText.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                frameLayoutText.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                changeText.setError(null);
            }
        });
    }

    // Create function to change frame layout background color
    private RadioGroup.OnCheckedChangeListener radioGroupCheckedChanged = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (radioGroup.getId() == R.id.rdg_RadioGroup1 || radioGroup.getId() == R.id.rdg_RadioGroup1_Land) {
                if (i == R.id.rbn_BackgroundPurple || i == R.id.rbn_BackgroundPurple_Land) {
                    frameLayout.setBackgroundColor(getColor(R.color.purple));
                } else if (i == R.id.rbn_BackgroundGreen || i == R.id.rbn_BackgroundGreen_Land) {
                    frameLayout.setBackgroundColor(getColor(R.color.green));
                } else {
                    frameLayout.setBackgroundColor(getColor(R.color.black));
                }
            } else {
                if (i == R.id.rbn_TextPurple || i == R.id.rbn_TextPurple_Land) {
                    frameLayoutText.setTextAppearance(R.style.TextColor);
                    chosenColor = 0;
                } else if (i == R.id.rbn_TextGreen || i == R.id.rbn_TextGreen_Land) {
                    frameLayoutText.setTextAppearance(R.style.TextColor_Green);
                    chosenColor = 1;
                } else {
                    frameLayoutText.setTextAppearance(R.style.TextColor_Black);
                    chosenColor = 2;
                }
            }
        }
    };

    private Switch.OnCheckedChangeListener switchChanged = new Switch.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b == true) {
                switch (chosenColor) {
                    case 0:
                        Log.i(TAG, "onCheckedChanged: It's purple!");
                        frameLayoutText.setTextAppearance(R.style.TextColor_Bold);
                        break;
                    case 1:
                        frameLayoutText.setTextAppearance(R.style.TextColor_Green_Bold);
                        break;
                    case 2:
                        frameLayoutText.setTextAppearance(R.style.TextColor_Black_Bold);
                        break;
                    default:
                        Log.i(TAG, "onCheckedChanged: OH MY GOSH");
                        break;
                }
            } else {
                switch (chosenColor) {
                    case 0:
                        frameLayoutText.setTextAppearance(R.style.TextColor_Normal);
                        break;
                    case 1:
                        frameLayoutText.setTextAppearance(R.style.TextColor_Green_Normal);
                        break;
                    case 2:
                        frameLayoutText.setTextAppearance(R.style.TextColor_Black_Normal);
                        break;
                    default:
                        Log.i(TAG, "onCheckedChanged: OH HELLO GOSH");
                        break;
                }
            }
        }
    };

    // Create function to get view depending on orientation
    private void getViewWithOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            frameLayout = (FrameLayout) findViewById(R.id.frameLayout_Portrait);
            radioGroup_1 = (RadioGroup) findViewById(R.id.rdg_RadioGroup1);
            radioGroup_2 = (RadioGroup) findViewById(R.id.rdg_RadioGroup2);
            frameLayoutText = (TextView) findViewById(R.id.txt_FrameLayoutText);
            changeText = (EditText) findViewById(R.id.txt_ChangeText);
            bold = (Switch) findViewById(R.id.swt_Bold);
        } else {
            frameLayout = (FrameLayout) findViewById(R.id.frameLayout_Land);
            radioGroup_1 = (RadioGroup) findViewById(R.id.rdg_RadioGroup1_Land);
            radioGroup_2 = (RadioGroup) findViewById(R.id.rdg_RadioGroup2_Land);
            frameLayoutText = (TextView) findViewById(R.id.txt_FrameLayoutText_Land);
            changeText = (EditText) findViewById(R.id.txt_ChangeText_Land);
            bold = (Switch) findViewById(R.id.swt_Bold_Land);
        }
    }

    // Function to get view whenever orientation changes
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getViewWithOrientation();
    }
}
