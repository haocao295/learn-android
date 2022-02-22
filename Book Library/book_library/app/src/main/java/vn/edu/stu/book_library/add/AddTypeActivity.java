package vn.edu.stu.book_library.add;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.stu.book_library.R;
import vn.edu.stu.book_library.list.TypeActivity;
import vn.edu.stu.book_library.db.Database;

public class AddTypeActivity extends AppCompatActivity {
    final String DATABASE_NAME = "bookDB.db";
    Button btnSave, btnCancel;
    EditText txtAddId, txtAddType;
    String typeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();
            }
        });
    }

    private void addControls() {
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        txtAddId = findViewById(R.id.txtAddId);
        txtAddType = findViewById(R.id.txtAddType);
    }
    private void insert() {
        String type = txtAddType.getText().toString();
        String typeId = txtAddId.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("typeId", typeId);
        contentValues.put("type", type);

        SQLiteDatabase database = Database.initDatabase(this, "bookDB.db");
        database.insert("Type", "typeId = ?",contentValues);
        Intent intent = new Intent( this, TypeActivity.class);
        startActivity(intent);
    }

    private void Cancel(){
        Intent intent = new Intent( this, TypeActivity.class);
        startActivity(intent);
    }
}