package net.appbank.testproject.todoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
    //Listの準備
    List<Item> mList = new ArrayList<>();
    //EditText取得
    EditText mEditText;
    //ボタン取得
    Button mButton;
    //最後に入れたものの取得
    int mLast;
    //adapter取得
    ItemAdapter mAdapter;
    //ListViewの取得
    ListView mListView;
    //日時の取得
    Date mDate;
    //フォント取得
    SimpleDateFormat mSimpleDateFormat;
    //遷移の取得
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //EditText取得
        mEditText = (EditText)findViewById(R.id.edittext);
        //mButton取得
        mButton = (Button)findViewById(R.id.button);
        //mListView取得
        mListView = (ListView)findViewById(R.id.listview);
        mListView.setChoiceMode(mListView.CHOICE_MODE_MULTIPLE);
        //ArrayAdapterの生成
        mAdapter = new ItemAdapter(this, mList);
        //listにadapterをセット
        mListView.setAdapter(mAdapter);
        //dateの作成
        mDate = new Date(System.currentTimeMillis());
        //フォント
        mSimpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日'HH:mm");
        mIntent = new Intent(MainActivity.this,EditActivity.class);
        //テストデータ
        mList.add(new Item("hoge", "hoge", mSimpleDateFormat.format(mDate)));
        //リストのアイテムを押した時の処理
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //押した場所の取得
                mList.get(position).setIsCheck(!mList.get(position).getIsCheck());
                //アダプターの更新
                mAdapter.notifyDataSetChanged();
            }
        });
        //リストのアイテムを長押した時の処理
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d("log","ok");
                startActivity(mIntent);
                return false;
            }
        });

        //buttonにクリックイベントを設定
        mButton.setOnClickListener(new View.OnClickListener() {
            //押した時の処理
            @Override
            public void onClick(View view) {
                //空白じゃなければ
                if(mEditText.getText().toString().length() != 0) {
                    //日時の更新
                    mDate.setTime(System.currentTimeMillis());
                    //データ追加
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
        mList.add(new Item("test", mEditText.getText().toString(), mSimpleDateFormat.format(mDate)));
        //更新の反映
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
        //item_layoutの中身を取得
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            convertView = mInflater.inflate(R.layout.item_layout, parent, false);
            TextView nameTextView = (TextView)convertView.findViewById(R.id.name);
            TextView dateTextView = (TextView)convertView.findViewById(R.id.date);
            TextView textTextView = (TextView)convertView.findViewById(R.id.text);
            nameTextView.setText(getItem(position).getName());
            textTextView.setText(getItem(position).getText());
            dateTextView.setText(getItem(position).getDate());
            //押されたなら文字色を変える
            if(getItem(position).getIsCheck()) {
                nameTextView.setTextColor(getResources().getColor(R.color.textColor));
                dateTextView.setTextColor(getResources().getColor(R.color.textColor));
                textTextView.setTextColor(getResources().getColor(R.color.textColor));
            }else {
                nameTextView.setTextColor(getResources().getColor(R.color.black));
                dateTextView.setTextColor(getResources().getColor(R.color.black));
                textTextView.setTextColor(getResources().getColor(R.color.black));
            }
            return convertView;
        }

    }
}
