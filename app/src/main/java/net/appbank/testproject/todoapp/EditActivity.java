package net.appbank.testproject.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tatsuya.sato on 2016/08/29.
 */
public class EditActivity extends AppCompatActivity {
    public final static String INTENT_COLOR = "color";
    public final static String INTENT_POSITION = "positon";
    public final static String INTENT_RESULT = "result";
    public final static String INTENT_DATA = "data";

    EditText mEditText;
    Intent mIntent;
    CheckBox mCheckBoxRed;
    CheckBox mCheckBoxBlue;
    CheckBox mCheckBoxYellow;
    CheckBox mCheckBoxWhite;
    Button mSaveButton;
    int mActionId;
    String mTextdata;
    int mPosition;
    int mColor;
    SharedPreferences mPref;
    static final  int RESULT_EDITACTIVITY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEditText = (EditText) findViewById(R.id.edittext);
        mIntent = new Intent(EditActivity.this, MainActivity.class);
        mCheckBoxWhite = (CheckBox) findViewById(R.id.white_check_Box);
        mCheckBoxRed = (CheckBox) findViewById(R.id.red_check_box);
        mCheckBoxBlue = (CheckBox) findViewById(R.id.blue_check_box);
        mCheckBoxYellow = (CheckBox) findViewById(R.id.yellow_check_box);
        mIntent = getIntent();
        mTextdata = mIntent.getStringExtra(INTENT_DATA);
        mPosition = mIntent.getIntExtra(INTENT_POSITION,0);
        mColor = mIntent.getIntExtra(INTENT_COLOR,0);
        mEditText.setText(mTextdata);
        mSaveButton = (Button) findViewById(R.id.SaveButton);
        mCheckBoxWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckBoxWhite.isChecked() == true) {
                    mCheckBoxRed.setChecked(false);
                    mCheckBoxBlue.setChecked(false);
                    mCheckBoxYellow.setChecked(false);
                    mEditText.setBackgroundColor(Color.TRANSPARENT);
                    mColor = Color.TRANSPARENT;
                }
            }
        });
        mCheckBoxRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckBoxRed.isChecked() == true) {
                    mCheckBoxWhite.setChecked(false);
                    mCheckBoxBlue.setChecked(false);
                    mCheckBoxYellow.setChecked(false);
                    mEditText.setBackgroundColor(Color.RED);
                    mColor = Color.RED;
                }
            }
        });
        mCheckBoxBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckBoxBlue.isChecked() == true) {
                    mCheckBoxRed.setChecked(false);
                    mCheckBoxWhite.setChecked(false);
                    mCheckBoxYellow.setChecked(false);
                    mEditText.setBackgroundColor(Color.BLUE);
                    mColor = Color.BLUE;
                }
            }
        });
        mCheckBoxYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckBoxYellow.isChecked() == true) {
                    mCheckBoxRed.setChecked(false);
                    mCheckBoxBlue.setChecked(false);
                    mCheckBoxWhite.setChecked(false);
                    mEditText.setBackgroundColor(Color.YELLOW);
                    mColor = Color.YELLOW;
                }
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntent.putExtra(INTENT_RESULT , mEditText.getText().toString());
                mIntent.putExtra(INTENT_COLOR, mColor);
                mIntent.putExtra(INTENT_POSITION, mPosition);
                setResult(RESULT_OK, mIntent);
                saveText();
                finish();
                Log.d("finish","ok");
            }
        });
    }

    private void saveText() {
        mPref = PreferenceManager.getDefaultSharedPreferences(this);
        mPref.edit().putString("text",mEditText.getText().toString()).commit();
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        mActionId = item.getItemId();
        boolean result = true;
        switch (mActionId){
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return  result;
    }
}
