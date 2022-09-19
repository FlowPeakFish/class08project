package cn.fengyu.class08project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 4.1 Toast消息提示
 * 4.2 Dialog对话框
 */
public class ToastActivity extends AppCompatActivity {

    Button button2;
    Button button3;
    Button button4;
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        //1. 在页面创建时显示一条短的Toast
        // 方式一
        // 1.创建Toast对象
        Toast toast = new Toast(ToastActivity.this);
        // 2.设置消息提示
        toast.setText("一条短的Toast");
        // 3.显示消息
        toast.show();

        //2. 通过点击按钮显示一条长的Toast
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 方式二
                Toast.makeText(ToastActivity.this, R.string.short_toast, Toast.LENGTH_LONG).show();
            }
        });

        // 3. 通过点击按钮显示居中的Toast
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast1 = Toast.makeText(ToastActivity.this,
                        R.string.short_toast,
                        Toast.LENGTH_LONG);
                toast1.setGravity(Gravity.CENTER,
                        0,
                        0);
                ImageView imageView = new ImageView(ToastActivity.this);
                imageView.setImageResource(R.drawable.shizi);
                LinearLayout toastView = (LinearLayout) toast1.getView();
                toastView.addView(imageView);
                toast1.show();
            }
        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v -> {
            // 1.创建AlertDialog对象
            AlertDialog.Builder builder = new AlertDialog.Builder(ToastActivity.this);
            // 2.设置Dialog内容
            builder.setIcon(R.drawable.shizi);
            builder.setTitle("问答");
            builder.setMessage("你是狮子吗？");
            builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ToastActivity.this, "恭喜您答对了", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("不是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ToastActivity.this, "恭喜您答对了", Toast.LENGTH_LONG).show();
                }
            });
            // 3.显示Dialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        button5 = findViewById(R.id.button6);
        button5.setOnClickListener(v -> {
            // 1.创建AlertDialog对象
            AlertDialog.Builder builder = new AlertDialog.Builder(ToastActivity.this);
            // 2.设置Dialog内容
            builder.setIcon(R.drawable.shizi);
            builder.setTitle("您的性别是什么？");
            String[] list1 = {"请选择", "男", "女", "未知"};
            builder.setSingleChoiceItems(list1,
                    0,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ToastActivity.this,
                                    "您的性别是：" + list1[which],
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ToastActivity.this, "恭喜您答对了", Toast.LENGTH_LONG).show();
                }
            });
            // 3.显示Dialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
}