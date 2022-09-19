package cn.fengyu.class08project;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 5.1 SharedPreference
 */
public class SharedPrefActivity extends AppCompatActivity {

    private EditText content;

    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);

        content = findViewById(R.id.content);
        save = findViewById(R.id.save);

        // 读取数据
        String string = getSharedPreferences("content", 0).getString("content", "NULL");
        Log.i("日记", string);
        content.setText(string);

        SharedPreferences preferences = getSharedPreferences("content", 0);
        // SharedPreferences.Editor edit = preferences.edit();
        SharedPreferences.Editor editor = preferences.edit();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 添加数据
                editor.putString("content", content.getText().toString());
                editor.putBoolean("isMarried", true);
                editor.putInt("age", 30);

                editor.clear();
                editor.remove("age");

                editor.commit();
                Toast.makeText(SharedPrefActivity.this,
                        "消息已保存", Toast.LENGTH_SHORT).show();

                // 读取数据
                String string = getSharedPreferences("content", 0)
                        .getString("content", "NULL");
                Log.i("日记", string);
            }
        });
    }
}