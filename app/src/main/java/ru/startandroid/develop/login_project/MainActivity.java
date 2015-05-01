package ru.startandroid.develop.login_project;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.loopj.android.http.*;
import org.apache.http.Header;


import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {
	
	TextView Inet;
	EditText Name;
	EditText Password;
	Button Login;
    String url = "http://109.234.38.29/scripts/client.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        Inet = (TextView) findViewById(R.id.textView1);
        Name = (EditText) findViewById (R.id.editText1);
        Password = (EditText) findViewById (R.id.editText2);
        Login = (Button) findViewById (R.id.button1);
        
        if (isNetworkAvailable())
        {
        	Inet.setText("Интернет соединение установлено");
        }
        else
        {
			alert("Error", "Нету интернет соединения");
		}

        Login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
            // TODO Auto-generated method stub

            if (isNetworkAvailable())
            {
                try {
                    final AsyncHttpClient client = new AsyncHttpClient();

                    url = makeUrl(url, Name.getText().toString(), Password.getText().toString());
                    client.get(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] response) {//NOPMD                           
                            if (isUser(response)){
                                alert("Error","Неправильный логин/пароль");
                            } else {
                                showToast("А ты похоже юзер!");
                                final Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                startActivity(SecAct);
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                            alert("Error",new String(errorResponse));
                        }
                    });
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
            else
            {
                Inet.setText("Недоступно подключение к интернету!");
            }
			}
		});
    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    protected void showToast(final String msg) {
        final Toast toastAdd = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toastAdd.show();
    }
    private void alert(final String title, final String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    public static String makeUrl(String url, String log, String pass) {
        return url +  "?func=is_user"
                + "&log="
                + log
                +  "&pass="
                + pass;
    }
    public static boolean isUser(byte [] response) {
        final String res = new String(response);
        return !("fail".equals(res));
    }

}
