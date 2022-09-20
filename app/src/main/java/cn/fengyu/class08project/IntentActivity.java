package cn.fengyu.class08project;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IntentActivity extends AppCompatActivity {

    ImageView imageView;
    Button takePhotoBtn;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        bingComponent();

        // 选择一张照片
        // Deprecated已过期
        // takePhotoBtn.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         Intent i = new Intent();
        //         i.setType("image/*");
        //         i.setAction(Intent.ACTION_GET_CONTENT);
        //         startActivityForResult(i, 100001);
        //     }
        // });

        // 选择一张照片
        // ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(
        //         new ActivityResultContracts.GetContent(),
        //         new ActivityResultCallback<Uri>() {
        //     @Override
        //     public void onActivityResult(Uri uri) {
        //         //使用content的接口
        //         ContentResolver cr = IntentActivity.this.getContentResolver();
        //         try {
        //             //获取图片
        //             Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
        //             imageView.setImageBitmap(bitmap);
        //         } catch (FileNotFoundException e) {
        //             Log.e("Exception", e.getMessage(), e);
        //         }
        //     }
        // });

        // 选择一张照片
        // takePhotoBtn.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         activityResultLauncher.launch("image/*");
        //     }
        // });
    }

    // Deprecated已过期
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        switch (requestCode) {
            case 100001:
                //使用content的接口
                ContentResolver cr = this.getContentResolver();
                try {
                    //获取图片
                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(data.getData()));
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Log.e("Exception", e.getMessage(), e);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void bingComponent() {
        imageView = findViewById(R.id.imageView);
        takePhotoBtn = findViewById(R.id.take_photo);
    }
}