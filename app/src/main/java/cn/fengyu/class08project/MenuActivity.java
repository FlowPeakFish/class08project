package cn.fengyu.class08project;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.jetbrains.annotations.NotNull;

/**
 * 4.3 Menu菜单
 * 4.4 Spinner下拉列表框
 */
public class MenuActivity extends AppCompatActivity {
    Spinner spinner;
    Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 2.绑定
        spinner = findViewById(R.id.spinner1);
        // 3.设置下拉数据
        String[] classNames = {"请选择", "计科1班", "计科2班", "计科3班", "计科8班"};
        // 4.配置适配器Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MenuActivity.this,
                android.R.layout.simple_spinner_item,
                classNames);
        // 5.让控件显示数据
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView view1 = (TextView) view;
                String choseString = view1.getText().toString();
                Log.i("MenuActiviy", choseString);
                Log.i("MenuActiviy", classNames[i]);
                // Toast.makeText(MenuActivity.this,
                //         classNames[i],
                //         Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(v -> {
            // 方式一
            // TextView view1 = (TextView) spinner.getSelectedView();
            // String choseString = view1.getText().toString();
            // Toast.makeText(MenuActivity.this,
            //         choseString,
            //         Toast.LENGTH_SHORT).show();

            // 方式二
            Toast.makeText(MenuActivity.this,
                    classNames[spinner.getSelectedItemPosition()],
                    Toast.LENGTH_SHORT).show();
        });
    }

    // 添加菜单选项,让创建的菜单显示出来
    @Override
    public boolean onCreateOptionsMenu(@NonNull @NotNull Menu menu) {
        // 获取当前菜单对象, 加载菜单布局文件
        getMenuInflater().inflate(R.menu.menu1, menu);
        // 显示菜单
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull
                                         MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                Toast.makeText(MenuActivity.this,
                        "您点击了退出按钮",
                        Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.add:
                Toast.makeText(MenuActivity.this,
                        "您点击了添加按钮",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(MenuActivity.this,
                        "您点击了删除按钮",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.modify:
                Toast.makeText(MenuActivity.this,
                        "您点击了修改按钮",
                        Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}