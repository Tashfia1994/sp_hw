package com.tashfia.sp_hw;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivty extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    Button btnLogin;
    CheckBox remamberme;
    SharedPreferences sp;
    SharedPreferences.Editor editor;//for edit xml file

    private final String Sp_LOGIN ="com.tashfia.sp_hw.login";
    String password;
    String email;
    String ischeck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activty);
        txtEmail=(EditText)findViewById(R.id.txt_email);
        txtPassword=(EditText)findViewById(R.id.txt_password);
        btnLogin=(Button)findViewById(R.id.btn_login);
        remamberme=(CheckBox)findViewById(R.id.checkBox);

        sp=getSharedPreferences(Sp_LOGIN ,MODE_PRIVATE);
        editor=sp.edit();

         email=sp.getString("email","");

         password=sp.getString("password","");

         ischeck=sp.getString("check","");



        Log.d("check",ischeck);

        if (ischeck.equals("enable")) {
            txtEmail.setText(email);
            txtPassword.setText(password);
            remamberme.isChecked();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=txtEmail.getText().toString();
                password=txtPassword.getText().toString();

                editor.putString("email",email);
                editor.putString("password",password);
                if (remamberme.isChecked())
                {
                    editor.putString("check","enable");
                    editor.putString("email",email);
                    editor.putString("password",password);
                }

                else

                {
                    editor.putString("check","disable");
                    editor.putString("email","");
                    editor.putString("password","");
                }
                editor.commit();
                Toast.makeText(MainActivty.this,"Login Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
