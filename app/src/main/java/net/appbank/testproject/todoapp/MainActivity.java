package net.appbank.testproject.todoapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import net.appbank.testproject.todoapp.model.Item;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    List<Item> mList = new ArrayList<>();
    //EditText取得
    EditText mEditText;
    //ボタン取得
    Button mButton;
    // 取得物入れ
    String mItem;
    int mLast;
    ItemAdapter mAdapter;
    ListView mListView;
    Date mDate;
    SimpleDateFormat mSimpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mEditText = (EditText)findViewById(R.id.edittext);
        mButton = (Button)findViewById(R.id.button);
        setSupportActionBar(toolbar);
        mListView = (ListView)findViewById(R.id.listview);
        mList.add(new Item("hoge", "hoge", null));
        //ArrayAdapterの生成
        mAdapter = new ItemAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        mDate = new Date(System.currentTimeMillis());
        mSimpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日'kk'時'mm'分'ss'秒'");
        //buttonにクリックイベントを設定
        mButton.setOnClickListener(new View.OnClickListener() {
            //押した時の処理
            @Override
            public void onClick(View view) {
                //空白じゃなければ
                Log.d("button","hoge");
                if(mEditText.getText().toString().length() != 0) {
                    //データ追加
                    Log.d("button","hoge");
                    addStringData();
                    //アダプターの数を入れる
                    mLast = mAdapter.getCount();
                    //mLast(追加した一番新しいもの)の位置に移動させる
                   mListView.setSelection(mLast);
                }
            }
        });
    }
    //更新処理
    private void addStringData() {
        //EditTextのテキストを取得
        mList.add(new Item("test", mEditText.getText().toString(), mDate));
        mAdapter.notifyDataSetChanged();
        //EditTextの中身の削除
        mEditText.getEditableText().clear();
    }

    public class ItemAdapter extends ArrayAdapter<Item> {
        LayoutInflater mInflater;

        public ItemAdapter(Context context, List<Item> list) {
            super(context, 0, list);
            this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            convertView = mInflater.inflate(R.layout.item_layout, parent, false);
            TextView nameTextView = (TextView)convertView.findViewById(R.id.name);
            TextView dateTextView = (TextView)convertView.findViewById(R.id.date);
            TextView textTextView = (TextView)convertView.findViewById(R.id.text);
            nameTextView.setText(getItem(position).getName());
            textTextView.setText(getItem(position).getText());
            dateTextView.setText(mSimpleDateFormat.format(mDate));
            return convertView;
        }

    }
}
