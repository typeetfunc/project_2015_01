package ru.startandroid.develop.login_project;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends Activity {
    Menu menu;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    
     // �������� ��������� �������� ListView
        ListView lv = (ListView)findViewById(R.id.listView1);    
        
	// ���������� ������ ���� String - ������� ����.
	final String[] mainmenu = new String[] {
	    "1. Состав ГАК", "2. Опсиание ДП", "3. Руководители", "4. Организации", "5. Защита дипломов", "6. Выводы", "Отчет", "Загрузить", "Сохранить","Выход"
	  };
	
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, mainmenu);

	lv.setAdapter(adapter);
	
	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
				long id) {
			Toast.makeText(getApplicationContext(), "itemClick: position = " +
	                position + ", id = " + id + ", " + parent.getAdapter().getItem(position),
	                Toast.LENGTH_SHORT).show();
		}
	});
	
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        this.menu = menu;

        return super.onCreateOptionsMenu(menu);

    }
}
