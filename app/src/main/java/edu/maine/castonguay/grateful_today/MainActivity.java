package edu.maine.castonguay.grateful_today;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int NEW_GRATITUDE_REQUEST = 1;

    private static ArrayList<String> gratitudeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (gratitudeList.isEmpty()){
            gratitudeList.add("Today");
            gratitudeList.add("Yesterday");
            gratitudeList.add("Tomorrow");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        WebView webview = findViewById(R.id.board);
        WebSettings ws = webview.getSettings();

        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setDomStorageEnabled(true);
        ws.setAllowContentAccess(true);
        ws.setAllowFileAccessFromFileURLs(true);
        ws.setAllowUniversalAccessFromFileURLs(true);

        webview.addJavascriptInterface(new WebAppInterface(this), "app");
        webview.loadUrl("file:///android_asset/main.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.action_new_gratitude:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clearGratitudeBoard(MenuItem item) {
        gratitudeList.clear();
        gratitudeList.add("Today");
        gratitudeList.add("Yesterday");
        gratitudeList.add("Tomorrow");
        WebView webview = findViewById(R.id.board);
        webview.reload();
    }

    public void startNewGratitudeView(MenuItem item){
        Intent newGratitudeIntent = new Intent(this, AddGratitudeActivity.class);
        startActivityForResult(newGratitudeIntent, NEW_GRATITUDE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NEW_GRATITUDE_REQUEST){
            if(resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                String gratitude = extras.getString("GRATITUDE");
                gratitudeList.add(gratitude);
            }
        }
    }

    public class WebAppInterface {
        private Context context;

        public WebAppInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public String loadData() {
            System.out.println(gratitudeList.toString());
            return gratitudeList.toString();
        }

    }
}
