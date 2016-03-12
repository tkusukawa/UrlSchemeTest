package jp.kusukawa.urlschemetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TopActivity extends AppCompatActivity {
    TopActivity me = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.format("[hoge] onCreate::TopActivity\n");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        me = this;

        MyApplication myApp = (MyApplication)getApplication();
        int initialized = myApp.getBaseInfo(this);
        if(initialized == 0) {
            finish();
        }

        View buttonService1 = findViewById(R.id.button_service1);
        buttonService1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TopActivity.this, Service1Activity.class);
                startActivity(intent);
            }
        });

        View buttonClearInitializedFlag = findViewById(R.id.button_clear_initialized_flag);
        buttonClearInitializedFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApp = (MyApplication)getApplication();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(myApp);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putBoolean("initialized", false);
                editor.commit();

                Toast.makeText(me, "初期化済みフラグを消去しました", Toast.LENGTH_LONG).show();
            }
        });
    }

}
