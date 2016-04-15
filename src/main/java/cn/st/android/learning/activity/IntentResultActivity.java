package cn.st.android.learning.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.st.android.learning.util.IntentUtils;

public class IntentResultActivity extends AppCompatActivity {

    private static final int PICK_CONTACT_RESULT=1;

    private EditText etPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_resutl);
        etPhoneNumber= (EditText) findViewById(R.id.et_phone_number);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intent_resutl, menu);
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


    public void chooseContact(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        if(IntentUtils.chkIntentHandlerExist(intent,this)){
            startActivityForResult(intent,PICK_CONTACT_RESULT);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_CONTACT_RESULT){
            if(resultCode==RESULT_OK){
                Uri contactUri=data.getData();
                String[] projection={ContactsContract.CommonDataKinds.Phone.NUMBER};
                Cursor cursor=getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();
                int column=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phoneNumber=cursor.getString(column);
                etPhoneNumber.setText(phoneNumber);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
