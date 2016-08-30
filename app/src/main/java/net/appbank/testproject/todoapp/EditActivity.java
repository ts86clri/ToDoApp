package net.appbank.testproject.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by tatsuya.sato on 2016/08/29.
 */
public class EditActivity extends AppCompatActivity {
    TextView mTextView;
    EditText mEditText;
    Button mButton;
    Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        mTextView = (TextView)findViewById(R.id.test);
        mEditText = (EditText)findViewById(R.id.edittext);
        mButton = (Button) findViewById(R.id.button);
        mIntent = new Intent(EditActivity.this,MainActivity.class);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(mIntent);
                finish();
            }
        });
    }
}
