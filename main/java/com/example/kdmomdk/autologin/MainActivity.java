package com.example.kdmomdk.autologin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    static Integer flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
        setContentView(R.layout.activity_main);
        //test
        final EditText account = (EditText) findViewById(R.id.editText);
        final EditText ps = (EditText) findViewById(R.id.editText2);
        final SharedPreferences user = this.getPreferences(Context.MODE_PRIVATE);
        Button login = (Button) findViewById(R.id.button);
        final SharedPreferences.Editor editor = user.edit();
        final String act = user.getString(getString(R.string.account), " ");
        final String pwd = user.getString(getString(R.string.password), " ");

        //get account and password
        if (!act.equals(" ") && !pwd.equals(" ")) {
            account.setText(act);
            ps.setText(pwd);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (account.equals(" ") || pwd.equals(" ")) {
                    editor.putString(getString(R.string.account), account.getText().toString());
                    editor.putString(getString(R.string.password), ps.getText().toString());
                    editor.apply();
                } else {
                    editor.putString(getString(R.string.account), account.getText().toString());
                    editor.putString(getString(R.string.password), ps.getText().toString());
                    editor.apply();
                }
                Post send = new Post("https://go.ruc.edu.cn/go.ruc.edu.cn",account.getText().toString(), ps.getText().toString());
            }

        });


        ImageButton visible = (ImageButton) findViewById(R.id.imageButton);
        //show or not shw the password
        visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    EditText ps = (EditText) findViewById(R.id.editText2);
                    ps.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable end = ps.getText();
                    ps.setSelection(end.length());
                    flag = 1;
                } else {
                    EditText ps = (EditText) findViewById(R.id.editText2);
                    ps.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    Editable end = ps.getText();
                    ps.setSelection(end.length());
                    flag = 0;
                }

            }
        });
    }
}


