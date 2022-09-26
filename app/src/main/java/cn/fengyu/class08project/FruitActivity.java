package cn.fengyu.class08project;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.fengyu.class08project.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitActivity extends AppCompatActivity {

    // 1、定义
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        // 2、绑定（声明）
        listView = findViewById(R.id.list_view_1);

        // 3、准备数据
        // 动态数组准备数据
        List<Fruit> fruitList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Fruit pineApple = new Fruit(R.drawable.pineapple, "菠萝", "16.9元/kg");
            fruitList.add(pineApple);

            fruitList.add(new Fruit(R.drawable.mango, "芒果", "29.9元/kg"));

            // Fruit.builder()
            //         .imageId(R.drawable.pomegranate)
            //         .name("石榴")
            //         .price("15元/kg")
            //         .build();
            Fruit.Builder builder = new Fruit.Builder();
            Fruit pomegranate = builder.imageId(R.drawable.pomegranate)
                    .name("石榴").price("15元/kg").build();
            // fruitList.add(Fruit.builder()
            //         .imageId(R.drawable.pomegranate)
            //         .name("石榴")
            //         .price("15元/kg")
            //         .build());
        }

        // 4、配置适配器
        // 参数1：当前上下文
        // 参数2：当前列表项加载的布局文件
        // 参数3：数据源
        FruitAdapter adapter = new FruitAdapter(
                FruitActivity.this,
                R.layout.fruit_item,
                fruitList
        );
        listView.setAdapter(adapter);
    }
}