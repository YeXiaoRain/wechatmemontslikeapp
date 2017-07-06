package com.example.cromarmot.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {
    private String shareUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = this.getIntent().getExtras();
        this.shareUrl = bundle.getString("ShareUrl");

        TextView tv= (TextView) findViewById(R.id.sharepage_display_shareurl);
        tv.setText(shareUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent intent =new Intent(ShareActivity.this,FriendCircleActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("ShareUrl", shareUrl);
            intent.putExtras(bundle);
            setResult( RESULT_OK, intent );
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
