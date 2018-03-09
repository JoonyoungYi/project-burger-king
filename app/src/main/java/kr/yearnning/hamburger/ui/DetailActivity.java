package kr.yearnning.hamburger.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import kr.yearnning.hamburger.R;

public class DetailActivity extends ActionBarActivity {

    private static final String ARG_ID = "arg_id";
    private static final String ARG_NAME = "arg_name";
    private static final String ARG_IMG_URL = "arg_img_url";
    private static final String ARG_PRICE = "arg_price";
    private static final String ARG_KCAL = "arg_kcal";

    public static void startActivity(Activity activity, String id, String name, String img_url, int price, int kcal) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(ARG_ID, id);
        intent.putExtra(ARG_NAME, name);
        intent.putExtra(ARG_IMG_URL, img_url);
        intent.putExtra(ARG_PRICE, price);
        intent.putExtra(ARG_KCAL, kcal);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        String name = getIntent().getExtras().getString(ARG_NAME);
        getSupportActionBar().setTitle(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
