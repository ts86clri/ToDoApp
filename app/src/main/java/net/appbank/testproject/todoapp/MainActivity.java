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
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ボタン取得
        final Button button = (Button)findViewById(R.id.button);
        //ListView取得
        final ListView listview = (ListView)findViewById(R.id.listview);
        //ArrayAdapterの生成
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        //buttonにクリックイベントを設定
        button.setOnClickListener(new View.OnClickListener() {
            //押した時の処理
            @Override
            public void onClick(View view) {
                //データ追加
                addStringData();
                Log.d("button","puhs");
            }
        });
        //Adapterのセット
        listview.setAdapter(adapter);
    }

    //更新処理
    private void addStringData() {
        //EditText取得
        final EditText editText = (EditText) findViewById(R.id.edittext);
        //EditTextのテキストを取得
        adapter.add(editText.getText().toString());
        //EditTextの中身の削除
        editText.getEditableText().clear();
    }
}
