package cn.fengyu.class08project;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.fengyu.class08project.util.MyDBOpenHelper;

public class DataActivity extends AppCompatActivity {

    Button btnAdd, btnSearch, btnModify, btnDelete;

    private MyDBOpenHelper myDBOpenHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        myDBOpenHelper = new MyDBOpenHelper(DataActivity.this);
        db = myDBOpenHelper.getWritableDatabase();

        btnAdd = findViewById(R.id.sql_add);
        btnSearch = findViewById(R.id.sql_search);
        btnModify = findViewById(R.id.sql_modify);
        btnDelete = findViewById(R.id.sql_delete);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 方式一
                // ContentValues values = new ContentValues();
                // values.put("name", "张三");
                // values.put("department", "艺术学院");
                // db.insert("stu_info", "", values);

                // 方式二
                db.execSQL("INSERT INTO stu_info ('name', 'department') VALUES ('李四','艺术学院')");
                Toast.makeText(DataActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor1 = db.query("stu_info",
                        new String[]{"name,department"},
                        "name=?",
                        new String[]{"张三"},
                        null, null, null, null);

                Cursor cursor = db.rawQuery("select * from stu_info where name=?",
                        new String[]{"张三"});
                if (cursor.getCount() != 0) {
                    while (cursor.moveToNext()) {
                        String result = cursor.getString(0) + " : " +
                                cursor.getString(1);
                        int name = cursor.getColumnIndex("name");
                        int department = cursor.getColumnIndex("department");
                        result = cursor.getString(name) + " : " +
                                cursor.getString(department);
                        System.out.println(result);
                    }
                }
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 方式一
                // ContentValues values = new ContentValues();
                // values.put("department","智能科技");
                // db.update("stu_info",
                //         values,
                //         "id=?",
                //         new String[]{"8"});

                // 方式二
                db.execSQL("UPDATE stu_info SET department = '智能科技2' WHERE id = '8'");
                Toast.makeText(DataActivity.this,
                        "修改成功",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 方式一
                // db.delete("stu_info","id!=?",new String[]{"1"});

                // 方式二
                db.execSQL("DELETE FROM stu_info WHERE id != '1'");
                Toast.makeText(DataActivity.this,
                        "删除成功",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}