package cn.fengyu.class08project;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 6.1 ListView列表
 */
public class ListViewActivity extends AppCompatActivity {

    // 1、定义
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // 2、绑定（声明）
        listView = findViewById(R.id.list_view);
        // 3、准备数据
        final String[] data = {"姚睿德", "孔和雅", "方俊逸", "刘嘉佑", "苏昊英", "夏玉龙", "史和惬", "邵鹤轩", "邹才艺", "邓敏博", "黄立轩", "潘景澄", "王嘉玉", "蒋宏朗", "罗丰茂", "梁修能", "林乐家", "谢学文", "孙飞语", "金鸿文", "吕光临", "崔天骄", "毛和平", "潘高歌", "易奇思", "董鸿博", "萧智渊", "郭宜年", "谢康复", "王欣可", "丁阳州", "张俊材", "唐宏爽", "汤丰茂", "江天骄", "白思源", "吕鹏海", "马坚诚", "郭兴业", "方玉轩", "汤翰学", "顾俊英", "毛和惬", "薛德庸", "夏元驹", "毛欣怿", "赖修文", "郝文德", "夏永言", "段宜春"};
        // 4、配置适配器
        // 参数1：当前上下文
        // 参数2：当前列表项加载的布局文件
        // 参数3：数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                ListViewActivity.this,
                android.R.layout.simple_list_item_1,
                data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this,
                        "选择了：" + data[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}