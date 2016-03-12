package jp.kusukawa.urlschemetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Service1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.format("[hoge] onCreate::Service1Activity\n");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service1);

        // 起動時処理
        int initialized = ((MyApplication)getApplication()).getBaseInfo(this);
        if(initialized == 0) {
            finish(); // 初回起動時は一旦終わって、初期化後に改めてActivityを立ち上げてもらう
        }
    }
}
