package net.appbank.testproject.todoapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    TextView mTextView;
    EditText mEditText;
    Button mButton;
    Intent mIntent;
    CheckBox mCheckBoxRed;
    CheckBox mCheckBoxBlue;
    CheckBox mCheckBoxYellow;
    CheckBox mCheckBoxWhite;
    private Boolean isCheckWhite;
    private Boolean isCheckRed;
    private Boolean isCheckBlue;
    private Boolean isCheckYellow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        mTextView = (TextView)findViewById(R.id.test);
        mEditText = (EditText)findViewById(R.id.edittext);
        mButton = (Button) findViewById(R.id.button);
        mIntent = new Intent(EditActivity.this,MainActivity.class);
        mCheckBoxWhite = (CheckBox)findViewById(R.id.CheckBoxWhite);
        mCheckBoxRed = (CheckBox)findViewById(R.id.CheckBoxRed);
        mCheckBoxBlue = (CheckBox)findViewById(R.id.CheckBoxBlue);
        mCheckBoxYellow = (CheckBox)findViewById(R.id.CheckBoxYellow);

        mCheckBoxWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCheckBoxWhite.isChecked()==true) {
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

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
