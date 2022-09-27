package cn.fengyu.class08project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.fengyu.class08project.adapter.FruitRecyclerAdapter;
import cn.fengyu.class08project.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFruitActivity extends AppCompatActivity {
    // 1、定义
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_fruit_actitivy);
        // 2、绑定（声明）
        recyclerView = findViewById(R.id.recycler_view);

        // 3、准备数据
        // 动态数组准备数据
        List<Fruit> fruitList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Fruit pineApple = new Fruit(R.drawable.pineapple, "菠萝", "16.9元/kg");
            fruitList.add(pineApple);
            fruitList.add(new Fruit(R.drawable.mango, "芒果", "29.9元/kg"));
            Fruit.Builder builder = new Fruit.Builder();
            Fruit pomegranate = builder.imageId(R.drawable.pomegranate)
                    .name("石榴").price("15元/kg").build();
            fruitList.add(pomegranate);
        }

        // 4、配置适配器
        // 参数1：数据源
        FruitRecyclerAdapter adapter = new FruitRecyclerAdapter(fruitList);
        // 5、创建布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(RecyclerFruitActivity.this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}