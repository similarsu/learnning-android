package cn.st.android.learning.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import cn.st.android.learning.activity.R;
import cn.st.android.learning.util.FileUtils;
import cn.st.android.learning.util.MediaUtils;

public class SimpleCameraActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE_PREVIEW = 1;
    private static final int REQUEST_IMAGE_CAPTURE_SAVE = 2;

    private ImageView ivImage;

    private static final String TAG="FileUtils";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_camera);
        ivImage= (ImageView) findViewById(R.id.iv_image_preview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case REQUEST_IMAGE_CAPTURE_PREVIEW:{
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ivImage.setImageBitmap(imageBitmap);
                    break;
                }

            }

        }
    }

    /**
     * 获取缩略图
     * @param view
     */
    public void invokeCamera2Preview(View view) {
        //检查是否有相机
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_PREVIEW);
            }
        }else{
            Toast.makeText(this,R.string.no_found_camera,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 保存图片
     * @param view
     */
    public void invokeCamera2Save(View view) {
        //检查是否有相机
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                Toast.makeText(this,"writable : "+MediaUtils.isExternalStorageWritable(),Toast.LENGTH_LONG).show();
                File photoFile = null;
                try {
                    photoFile = FileUtils.createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    Log.e(TAG,"create file error",ex);
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile));
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_SAVE);
                }
            }
        }else{
            Toast.makeText(this,R.string.no_found_camera,Toast.LENGTH_SHORT).show();
        }
    }

}
