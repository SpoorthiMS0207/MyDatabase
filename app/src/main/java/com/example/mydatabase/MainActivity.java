package com.example.mydatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText t1,t2,t3;
    TextView tv;
    //private  final String url="http://192.168.136.1/android_db_pool/db_insert.php";
    private  final String url="http://10.0.2.2/android_db_pool/db_insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText)findViewById(R.id.t1);
        //System.out.println(t1);
        t2=(EditText) findViewById(R.id.t2);
        //System.out.println(t2);
        t3=(EditText) findViewById(R.id.t3);
        tv=(TextView) findViewById(R.id.tv);
    }
    public void process(View view)
    {
        String n1=t1.getText().toString();
        System.out.println(n1);
        String n2=t2.getText().toString();
        System.out.println(n2);
        String n3=t3.getText().toString();
        System.out.println(n3);
        String querystring="?t1="+n1+"&t2="+n2+"&t3="+n3;
        System.out.println(querystring);
        class dbclass extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                //System.out.println(data);
                tv.setText(data);
            }

            @Override
            protected String doInBackground(String... param)
            {
                try{
                    URL url=new URL(param[0]);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    //System.out.println(br.readLine());
                    return br.readLine();

                }catch(Exception ex)
                {
                    return ex.getMessage();
                }

            }
        }
        dbclass obj=new dbclass();
        obj.execute(url+querystring);
    }

}