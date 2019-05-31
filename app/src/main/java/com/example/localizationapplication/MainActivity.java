package com.example.localizationapplication;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Button changelang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        changelang=findViewById(R.id.button3);
        changelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showchangelanuagedialog();
            }

            private void showchangelanuagedialog() {
            final  String[] listitems={"English","Espanol","हिंदी"};
            final AlertDialog.Builder mbuilder=new AlertDialog.Builder(MainActivity.this);
            mbuilder.setTitle("Change Language");
            mbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i)
                {
                    if(i==0)
                    {
                        setLocale("En");
                        recreate();
                    }

                    else if(i==1)
                    {
                        setLocal("Es");
                        recreate();
                    }

                    else if(i==2)
                    {
                        setLocal("Hin");
                        recreate();
                    }

             dialog.dismiss();
            }
            });
            AlertDialog mdialog=mbuilder.create();
            mdialog.show();

            }
        });
    }

    private void setLocal(String lang) {
        Locale locale=new Locale(lang);
        locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }

    private void setLocale(String en) {
        SharedPreferences preferences=getSharedPreferences("setting", Activity.MODE_PRIVATE);
        String language=preferences.getString("My_Lang","");
        setLocal(language);
    }
}
