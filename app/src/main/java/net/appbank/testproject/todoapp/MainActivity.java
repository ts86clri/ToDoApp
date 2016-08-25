package net.appbank.testproject.todoapp;

import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> mAdapter;
    //EditText取得
    EditText mEditText;
    //ボタン取得
    Button mButton;
    //ListView取得
    ListView mListView;
    //取得物入れ
    String mImte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mEditText = (EditText)findViewById(R.id.edittext);
        mButton = (Button)findViewById(R.id.button);
        mListView = (ListView)findViewById(R.id.listview);
        setSupportActionBar(toolbar);
        //ArrayAdapterの生成
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //buttonにクリックイベントを設定
        mButton.setOnClickListener(new View.OnClickListener() {
            //押した時の処理
            @Override
            public void onClick(View view) {
                //空白じゃなければ
                if(mEditText.length() != 0) {
                    //データ追加
                    addStringData();
                }
            }
        });
        //Adapterのセット
        mListView.setAdapter(mAdapter);
        //List項目のタッチ処理
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListView = (ListView)parent;
                mImte = (String)mListView.getItemAtPosition(position);
                mAdapter = (ArrayAdapter<String>)mListView.getAdapter();
                mAdapter.remove(mImte);
            }
        });
    }

    //更新処理
    public void addStringData() {
        //EditTextのテキストを取得
        mAdapter.add(mEditText.getText().toString());
        //EditTextの中身の削除
        mEditText.getEditableText().clear();
    }
}
