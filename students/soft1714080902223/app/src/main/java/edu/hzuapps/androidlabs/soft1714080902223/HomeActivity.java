package edu.hzuapps.androidlabs.soft1714080902223;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;


import edu.hzuapps.androidlabs.listview.HomeListAdapter;
import edu.hzuapps.androidlabs.model.Task;
import edu.hzuapps.androidlabs.presenter.JsonService;
import edu.hzuapps.androidlabs.presenter.NetworkService;

public class HomeActivity extends AppCompatActivity {

    private ImageButton mDetailBtn;
    private ListView lv_1;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDetailBtn = findViewById(R.id.home_ibtn);
        mDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EditActivity.class);
                startActivity(intent);

            }
        });

        lv_1 = findViewById(R.id.home_list);
        lv_1.setAdapter(new HomeListAdapter(this));
        //为每个list_Item设置点击事件
        lv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                Task task = (Task)parent.getItemAtPosition(position);
                long taskId = task.getId();
                intent.putExtra("position", position);
                System.out.println(position);
                intent.putExtra("id", taskId);
                startActivity(intent);
            }
        });

        tv = findViewById(R.id.bottom_text);
        //判断是否有网
        if(NetworkService.isNetworkAvailable(this)) {
            setJson();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        //如果有网络，且json数据为空重新获取json，不给出alert影响用户体验
        if(NetworkService.isNetworkAvailable(this, false)){
            if(tv.getText().toString().equals("")){
                setJson();
            }
        }

    }

    void setJson(){
        //创建一个新线程异步获取json数据并显示
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = new JsonService().getHomeJson();
                if(jsonObject == null){
                    tv.setText("获取网络数据异常");
                    return;
                }
                try {
                    String text = jsonObject.getString("author") + "  "
                            + jsonObject.getString("code");
                    tv.setText(text);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

