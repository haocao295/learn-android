package vn.edu.stu.qlynhanvien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vn.edu.stu.qlynhanvien.R;
import vn.edu.stu.qlynhanvien.model.Congviec;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabThem;
    ArrayAdapter<Congviec> adapter;
    ListView lvCongviec;
    Congviec chon;
    int requestCode = 113,resultCode = 115;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        fabThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this,ThemSuaCongviecActivity.class
                );
                startActivityForResult(intent,requestCode);
            }
        });
        lvCongviec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position >= 0 && position < adapter.getCount()){
                    Intent intent = new Intent(MainActivity.this,ThemSuaCongviecActivity.class);
                    chon = adapter.getItem(position);
                    intent.putExtra("CHON",chon);
                    startActivityForResult(intent,requestCode);
                }
            }
        });
        lvCongviec.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_date_picker);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chưa?");
                builder.setNegativeButton("Chưa chắc", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Chắc rồi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(adapter.getItem(position));
                        adapter.notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    private void addControls() {
        fabThem = findViewById(R.id.fabThem);
        adapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1
        );
        lvCongviec = findViewById(R.id.lvCongviec);
        lvCongviec.setAdapter(adapter);
        chon = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == this.requestCode){
            if(resultCode == this.resultCode){
                if(data.hasExtra("TRA")){
                    Congviec tra = (Congviec) data.getSerializableExtra("TRA");
                    if(chon == null){
                        adapter.add(tra);
                    }else {
                        chon.setTen(tra.getTen());
                        chon.setHan(tra.getHan());
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }
        chon = null;
    }
}