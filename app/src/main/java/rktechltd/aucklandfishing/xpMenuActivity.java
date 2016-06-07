package rktechltd.aucklandfishing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class xpMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xp_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void buttonAddXP(View view) {
        Intent intent = new Intent(this, MyFishingXPActivity.class);
        startActivity(intent);
    }

    public void buttonViewXP(View view) {
        Intent intent = new Intent(this, xpViewActivity.class);
        startActivity(intent);
    }

}
