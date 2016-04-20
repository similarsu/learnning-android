package cn.st.android.learning.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import cn.st.android.learning.activity.R;

public class LearnVideoActivity extends AppCompatActivity {
    private static final int REQUEST_VIDEO_CAPTURE=1;
    private VideoView vvVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_vedio);
        vvVideo= (VideoView) findViewById(R.id.vv_video);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn_vedio, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode==RESULT_OK){
           switch (requestCode){
               case REQUEST_VIDEO_CAPTURE:{
                   Uri uri=data.getData();
                   vvVideo.setVideoURI(uri);
                   break;
               }
           }
       }
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

    public void invokeVideo2Capture(View view) {
        Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
        }else{
            Toast.makeText(this,R.string.no_found_camera,Toast.LENGTH_LONG).show();
        }
    }
}
