package vn.edu.stu.book_library.list;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vn.edu.stu.book_library.AboutActivity;
import vn.edu.stu.book_library.R;
import vn.edu.stu.book_library.adapter.TypeListAdapter;
import vn.edu.stu.book_library.add.AddTypeActivity;
import vn.edu.stu.book_library.db.Database;
import vn.edu.stu.book_library.model.Type;

public class TypeActivity extends AppCompatActivity {
    TextView txtAction;
    ListView list_type;
    Button btnAdd;
    ArrayList<Type> typeArrayList;
    TypeListAdapter typeListAdapter;
    final String DATABASE_NAME = "bookDB.db";
    SQLiteDatabase database;
    Type tp;

    String[] type = {"Science", "Fiction", "Classic", "Comic", "History", "Romance", "Story", "Self-help"};
    String[] typeId = {"SCI", "FIC", "CLA"," COM","HIS", "ROM", "STO", "SHP"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        addControls();
        readData();

        list_type.setOnItemLongClickListener(new TypeActivity.ItemLongClickRemove());

    }


    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TypeActivity.this);
            alertDialogBuilder.setMessage("Bạn có chắc chắn xóa không!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // xóa sp đang nhấn giữ
                    typeArrayList.remove(position);
                    //cập nhật lại listview
                    typeListAdapter.notifyDataSetChanged();

                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //không làm gì
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }




    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM Type", null);
        typeArrayList.clear();
        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String typeId = cursor.getString(0);
            String type = cursor.getString(1);
            typeArrayList.add(new Type(typeId, type));
        }
        typeListAdapter.notifyDataSetChanged();
    }


    private void addControls() {
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        TypeActivity.this,
                        AddTypeActivity.class
                );
                startActivity(intent);
            }
        });
        list_type = findViewById(R.id.list_type);
        typeArrayList = new ArrayList<>();
        typeListAdapter = new TypeListAdapter(this, typeArrayList);
        list_type.setAdapter(typeListAdapter);

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuAbout:
                Intent intent1 = new Intent(
                        TypeActivity.this,
                        AboutActivity.class
                );
                startActivity(intent1);
                break;
            case R.id.mnuExit:
                finish();
                break;
            case R.id.mnuBook:
                Intent intent2 = new Intent(
                        TypeActivity.this,
                        BookActivity.class
                );
                startActivity(intent2);
                break;
            case R.id.mnuType:
                Intent intent3 = new Intent(
                        TypeActivity.this,
                        TypeActivity.class
                );
                startActivity(intent3);
                break;
            default:
                txtAction.setText("");

        }
        return super.onOptionsItemSelected(item);
    }
}