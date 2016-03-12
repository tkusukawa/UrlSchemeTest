package jp.kusukawa.urlschemetest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InitActivity extends AppCompatActivity {
    Activity me = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.format("[hoge] onCreate::InitActivity\n");

        me = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        findViewById(R.id.buttonInitOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.format("[hoge] onClick::InitActivity\n");

                MyApplication myApp = (MyApplication)getApplication();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(myApp);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putBoolean("initialized", true);
                editor.commit();

                ((MyApplication)getApplication()).intentStartActivity(me);
                me.finish();
            }
        });
    }
}
