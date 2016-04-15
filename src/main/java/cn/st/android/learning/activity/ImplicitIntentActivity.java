package cn.st.android.learning.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

import cn.st.android.learning.util.IntentUtils;

public class ImplicitIntentActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnDial;
    private Button btnLocal;
    private Button btnWeb;
    private Button btnEmail;
    private Button btnChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);
        btnDial= (Button) findViewById(R.id.btn_dial);
        btnDial.setOnClickListener(this);
        btnLocal= (Button) findViewById(R.id.btn_local);
        btnLocal.setOnClickListener(this);
        btnWeb= (Button) findViewById(R.id.btn_web);
        btnWeb.setOnClickListener(this);
        btnEmail= (Button) findViewById(R.id.btn_email);
        btnEmail.setOnClickListener(this);
        btnChoose=(Button)findViewById(R.id.btn_choose);
        btnChoose.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intent, menu);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dial:{
                Uri uri=Uri.parse("tel:10086");
                Intent intent=new Intent(Intent.ACTION_DIAL,uri);
                if(IntentUtils.chkIntentHandlerExist(intent,this)){
                    startActivity(intent);
                }
                break;
            }
            case R.id.btn_local:{
                Uri uri=Uri.parse("geo:37.422219,-122.08364?z=14");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                if(IntentUtils.chkIntentHandlerExist(intent, this)){
                    startActivity(intent);
                }
                break;
            }
            case R.id.btn_web:{
                Uri uri=Uri.parse("https://www.baidu.com");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                if(IntentUtils.chkIntentHandlerExist(intent, this)){
                    startActivity(intent);
                }

                break;
            }

            case R.id.btn_email:{
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"821192673@qq.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"主题");
                intent.putExtra(Intent.EXTRA_TEXT, "这是一份邮件");
                if(IntentUtils.chkIntentHandlerExist(intent, this)){
                    startActivity(intent);
                }
                break;
            }

            case R.id.btn_choose:{
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"821192673@qq.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"主题");
                intent.putExtra(Intent.EXTRA_TEXT, "这是一份邮件");
                if(IntentUtils.chkIntentHandlerExist(intent, this)){
                    Intent choooseIntent=Intent.createChooser(intent,"选择邮件");
                    startActivity(choooseIntent);
                }
                break;
            }
        }
    }


}
