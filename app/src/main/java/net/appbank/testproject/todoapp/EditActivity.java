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
    EditText mEditText;
    Intent mIntent;
    CheckBox mCheckBoxRed;
    CheckBox mCheckBoxBlue;
    CheckBox mCheckBoxYellow;
    CheckBox mCheckBoxWhite;
    Button mSaveButton;
    int ActionId;
    String data;
    int mPosition;
    static final  int RESULT_EDITACTIVITY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEditText = (EditText) findViewById(R.id.edittext);
        mIntent = new Intent(EditActivity.this, MainActivity.class);
        mCheckBoxWhite = (CheckBox) findViewById(R.id.CheckBoxWhite);
        mCheckBoxRed = (CheckBox) findViewById(R.id.CheckBoxRed);
        mCheckBoxBlue = (CheckBox) findViewById(R.id.CheckBoxBlue);
        mCheckBoxYellow = (CheckBox) findViewById(R.id.CheckBoxYellow);
        mIntent = getIntent();
        data = mIntent.getStringExtra("data");
        mPosition = mIntent.getIntExtra("position",0);
        mEditText.setText(data);
        mSaveButton = (Button) findViewById(R.id.SaveButton);
        mCheckBoxWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckBoxWhite.isChecked() == true) {
                    mCheckBoxRed.setChecked(false);
                    mCheckBoxBlue.setChecked(false);
                    mCheckBoxYellow.setChecked(false);
                    mEditText.setBackgroundColor(Color.WHITE);
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
                }
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntent.putExtra("RESULT", mEditText.getText().toString());
                mIntent.putExtra("position", mPosition);
                setResult(RESULT_OK, mIntent);
                finish();
            }
        });
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        ActionId = item.getItemId();
        boolean result = true;
        switch (ActionId){
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return  result;
    }


}
