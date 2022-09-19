package cn.fengyu.class08project;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;

/**
 * 5.2 文件存储
 * Ctrl + Alt + M
 */
public class FileActivity extends AppCompatActivity {

    private EditText content;

    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        // 绑定
        initBindingView();
        // 读取数据
        // readContent();
        // 设置监听器
        initListener();
        // 外部存储路径
        Environment.getExternalStorageDirectory();
        // 外部缓存目录
        getExternalCacheDir();
    }

    private void initListener() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeContent();
            }
        });
    }

    private void writeContent() {
        // 输出文件
        try {
            // 内部存储
            // FileOutputStream fos = openFileOutput("content.txt", 0);
            // fos.write(content.getText().toString().getBytes());
            // fos.close();
            // Toast.makeText(FileActivity.this,
            //         "日志已保存", Toast.LENGTH_SHORT).show();

            // 外部存储
            String path =
                    Environment.getExternalStorageDirectory() + "/" +
                            "content.txt";
            File file = new File(path);
            System.out.println(path);
            if (!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = openFileOutput( "content.txt", 0);
            fos.write(content.getText().toString().getBytes());
            fos.close();
            Toast.makeText(FileActivity.this,
                    "日志已保存", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("FileActivity", "文件输出错误");
            throw new RuntimeException(e);
        }
    }

    private void readContent() {
        try {
            FileInputStream fis = openFileInput("content.txt");
            byte[] arr = new byte[fis.available()];
            fis.read(arr);
            content.setText(new String(arr));
            fis.close();
        } catch (IOException e) {
            Log.e("FileActivity", "文件输入错误");
            throw new RuntimeException(e);
        }
    }

    private void initBindingView() {
        content = findViewById(R.id.content);
        save = findViewById(R.id.save);
    }
}