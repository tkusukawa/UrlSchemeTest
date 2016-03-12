package jp.kusukawa.urlschemetest;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MyApplication extends Application{

    Class startActivity = null;

    @Override
    public void onCreate() {
        System.out.format("[hoge] onCreate::MyApplication\n");
        // ここで毎回行う起動時処理をしても良さげ
    }

    public int getBaseInfo(Activity act) {
        System.out.format("[hoge] getBaseInfo::MyApplication\n");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean initialized = preferences.getBoolean("initialized", false);
        if(initialized) {
            return 1; // 既に初期化済み
        }

        //ーーーーーーーーーーーーーーーー 以下、初回起動時の初期化処理
        startActivity = act.getClass(); //初期化後に立ち上げるアクティビティを記憶

        Intent intent = new Intent (act, InitActivity.class);
        act.startActivity(intent); // 初期化アクティビティを起動
        act.finish(); //呼び出し元のアクティビティは終了

        return 0;
    }

    public void intentStartActivity(Activity act) {
        System.out.format("[hoge] onCreate::intentStartActivity\n");

        if(startActivity == null) return;
        Intent intent = new Intent (act, startActivity);
        act.startActivity(intent);
    }
}
