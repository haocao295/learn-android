package vn.edu.stu.quan_ly_lop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] arrDSLop;
    ArrayAdapter<String> adapter;
    ListView lvDSLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }

    private void addControl() {
        arrDSLop = getResources().getStringArray(R.array.arr_ds_lop);
        adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_single_choice,
                arrDSLop
        );
        lvDSLop = findViewById(R.id.lvDSLop);
        lvDSLop.setAdapter(adapter);
    }

    private void addEvent() {
        lvDSLop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        MainActivity.this,
                        "Ban nhan 1 cai tai "+ arrDSLop[position],
                        Toast.LENGTH_LONG
                ).show();
            }
        });
        lvDSLop.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        MainActivity.this,
                        "Ban nhan lau tai "+ arrDSLop[position],
                        Toast.LENGTH_LONG
                ).show();
                return true;
            }
        });
    }
}