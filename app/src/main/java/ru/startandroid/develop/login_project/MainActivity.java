/**
 * ����������� ������������
 * @author ���������� �.�.
 * @version 0.0.1


*/
package ru.startandroid.develop.login_project;

//import org.ksoap2.SoapEnvelope;
//import org.ksoap2.serialization.SoapObject;
//import org.ksoap2.serialization.*;
//import org.ksoap2.transport.*;
//import org.ksoap2.transport.ServiceConnection;
//import org.ksoap2.transport.ServiceConnectionSE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	TextView Inet;
	EditText Name;
	EditText Password;
	Button Login;
	String Nm, Psw;

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
        	Inet.setText("");
        }
        else
        {
			Inet.setText("���������� ��������� � ��������! ��������� ������� ���������� � ����������.");
		}
        //������� ������ "���� ���� � �������"
        Login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (isNetworkAvailable())
		        {
					try {
						if ((call(Nm, Psw)))
						{
							//������� � �������� ����
							
							
							Button swith = (Button)findViewById(R.id.button1);
					        swith.setOnClickListener(new OnClickListener() {
					            
					            @Override
					            public void onClick(View v) {
					                // TODO Auto-generated method stub
					                Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
					                startActivity(SecAct);
					            }
					        });							
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					
		        }
				else
				{
					Inet.setText("���������� ��������� � ��������! ��������� ������� ���������� � ����������.");
				}
				
			}
		});
        
    }
    
    //�������� ������� �������� ����������
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager 
              = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    
    private static final String NAMESPACE = "";
    private static final String URL = "";
    private static final String SOAP_ACTION = "http://tempuri.org/Add";
    private static final String METHOD_NAME = "person";
    
    
   //������ �������� �� �������� ����������������� ������. ����������� ����-����������, ������-��������  
    boolean call(String a, String b) throws Exception {
    	boolean result = true;
   /* 	do
    	{
    		

    		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

    		PropertyInfo name = new PropertyInfo();
    		name.setName("UserName");
    		name.setValue(new String(a));
    		    		
    		PropertyInfo password = new PropertyInfo();
    		password.setName("Password");
    	    password.setValue(b);
    		/*
    		request.addProperty(name);
            request.addProperty(password);
    		
    		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
    		
            
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new AndroidHttpTransport(URL);
            androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
     
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
            result = Boolean.parseBoolean(resultsRequestSOAP.getProperty("AddResult").toString());
    	
    		}
    	while (false);*/
    	return result;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
