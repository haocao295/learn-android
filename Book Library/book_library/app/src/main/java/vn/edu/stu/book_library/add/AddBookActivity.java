package vn.edu.stu.book_library.add;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import vn.edu.stu.book_library.list.BookActivity;
import vn.edu.stu.book_library.R;
import vn.edu.stu.book_library.db.Database;

public class AddBookActivity extends AppCompatActivity {
    Button btnSelect, btnTake, btnSave, btnCancel;
    EditText txtAddName, txtAddId, txtAddType, txtAddAuthor, txtAddAmount;
    ImageView imageAdd;
    final String DATABASE_NAME = "bookDB.db";
    final int request_take_photo = 123;
    final int request_select_photo = 321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnSelect = findViewById(R.id.btnSelect);
        btnTake = findViewById(R.id.btnTake);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        txtAddName = findViewById(R.id.txtAddName);
        txtAddId = findViewById(R.id.txtAddId);
        txtAddType = findViewById(R.id.txtAddType);
        txtAddAuthor = findViewById(R.id.txtAddAuthor);
        txtAddAmount = findViewById(R.id.txtAddAmount);
        imageAdd = findViewById(R.id.imageAdd);

    }

    private void addEvents(){
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });
        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
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

    private void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, request_take_photo);
    }
    private void selectPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, request_select_photo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == request_select_photo) {

                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imageAdd.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == request_take_photo) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageAdd.setImageBitmap(bitmap);
            }
        }
    }

    private void insert(){
        String name = txtAddName.getText().toString();
        String bookId = txtAddId.getText().toString();
        String type = txtAddType.getText().toString();
        String author = txtAddAuthor.getText().toString();
        String amount = txtAddAmount.getText().toString();
        byte[] image = getByteArrayFromImageView(imageAdd);

        ContentValues contentValues = new ContentValues();
        contentValues.put("bookId", bookId);
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("author", author);
        contentValues.put("amount", amount);
        contentValues.put("imageId", image);

        SQLiteDatabase database = Database.initDatabase(this, "bookDB.db");
        database.insert("Book", null,contentValues);
        Intent intent = new Intent(this, BookActivity.class);
        startActivity(intent);

    }

    private void Cancel(){
        Intent intent = new Intent(this, BookActivity.class);
        startActivity(intent);
    }

    private byte[] getByteArrayFromImageView(ImageView imageAdd){
        BitmapDrawable drawable = (BitmapDrawable) imageAdd.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}