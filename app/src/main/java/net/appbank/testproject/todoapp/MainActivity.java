package net.appbank.testproject.todoapp;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.preference.PreferenceManager;
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
    static final  int RESULT_EDITACTIVITY = 100;


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
        //読み書き可能
        mListView.setChoiceMode(mListView.CHOICE_MODE_MULTIPLE);
        //ArrayAdapterの生成
        mAdapter = new ItemAdapter(this, mList);
        //listにadapterをセット
        mListView.setAdapter(mAdapter);
        //dateの作成
        mDate = new Date(System.currentTimeMillis());
        //フォント
        mSimpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日'HH:mm");
        //遷移の準備
        mIntent = new Intent(MainActivity.this,EditActivity.class);

        //テストデータ
        mList.add(new Item("hoge", mSimpleDateFormat.format(mDate), 0));
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
                //現在のテキストをEdit_Activityに送る
                mIntent.putExtra(EditActivity.INTENT_DATA, mList.get(position).getText());
                //押されたアイテムの場所をEdit_Activityに送る
                mIntent.putExtra(EditActivity.INTENT_POSITION, position);
                //現在の背景色をEdit_Activityに送る
                mIntent.putExtra(EditActivity.INTENT_COLOR, mList.get(position).getColor());
                int requestCode = RESULT_EDITACTIVITY;
                startActivityForResult(mIntent,requestCode);

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
        mList.add(new Item(mEditText.getText().toString(), mSimpleDateFormat.format(mDate), 0));
        //更新の反映
        mAdapter.notifyDataSetChanged();
        //EditTextの中身の削除
        mEditText.getEditableText().clear();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String res;
        int pos;
        int color;
        if (resultCode == RESULT_OK && requestCode == RESULT_EDITACTIVITY && null != data) {
            //Edit_Activityから送られたテキストデータを受け取る
            res = data.getStringExtra(EditActivity.INTENT_RESULT);
            //押されたアイテムの場所のポジションを受け取る
            pos = data.getIntExtra(EditActivity.INTENT_POSITION, 0);
            //Edit_Activityから送られた背景色を受け取る
            color = data.getIntExtra(EditActivity.INTENT_COLOR, 0);
            Log.d("color","ok: " + color);
            //テキストの反映
            mList.get(pos).setText(res);
            //色の反映
            mList.get(pos).setColor(color);
            //更新
            mAdapter.notifyDataSetChanged();
        }
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
            TextView dateTextView = (TextView)convertView.findViewById(R.id.date);
            TextView textTextView = (TextView)convertView.findViewById(R.id.text);
            textTextView.setText(getItem(position).getText());
            dateTextView.setText(getItem(position).getDate());
            //押されたなら文字色を変える
            if(getItem(position).getIsCheck()) {
                dateTextView.setTextColor(getResources().getColor(R.color.textColor));
                textTextView.setTextColor(getResources().getColor(R.color.textColor));
            }else {
                dateTextView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                textTextView.setTextColor(getResources().getColor(R.color.black));
            }
            if(getItem(position).getColor() != 0) {
                //Edit_Activityから送られてきた背景色のデータが０じゃなければ色を変更
                convertView.setBackgroundColor(getItem(position).getColor());
            }
            return convertView;
        }
    }
}
