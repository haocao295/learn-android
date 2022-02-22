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
import vn.edu.stu.book_library.BookDescription;
import vn.edu.stu.book_library.R;
import vn.edu.stu.book_library.adapter.BookListAdapter;
import vn.edu.stu.book_library.add.AddBookActivity;
import vn.edu.stu.book_library.db.Database;
import vn.edu.stu.book_library.model.Book;


public class BookActivity extends AppCompatActivity {

        TextView txtAction;
        ListView list_book;
        ArrayList<Book> bookArrayList;
        BookListAdapter bookListAdapter;
        Button btnAdd;
        final String DATABASE_NAME = "bookDB.db";
        SQLiteDatabase database;


    int[] image = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
    String[] name = {"How to win", "Space and Dream", "Doraemon", "Deep Mind", "Donkihote"};
    String[] type = {"Self-help", "Science", "Comic", "Story", "Fiction"};
    String[] bookId = {"SHP1", "SCI1", "COM1", "STO1", "FIC1"};
    String[] amount = {"13","24","11","55","12"};
    String[] author = {"Dale", "Scorporter", "Fujiko", "None", "Smiths"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book);


        addControls();
        readData();

        list_book.setOnItemLongClickListener(new ItemLongClickRemove());




    }

    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BookActivity.this);
            alertDialogBuilder.setMessage("Bạn có chắc chắn xóa không!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // xóa sp đang nhấn giữ
                    bookArrayList.remove(position);
                    //cập nhật lại listview
                    bookListAdapter.notifyDataSetChanged();

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


    private void addControls() {
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
        list_book = findViewById(R.id.list_book);
        bookArrayList = new ArrayList<>();
        bookListAdapter = new BookListAdapter(this,bookArrayList);
        list_book.setAdapter(bookListAdapter);


       list_book.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BookActivity.this, BookDescription.class );
                intent.putExtra("image", image[position]);
                intent.putExtra("name", name[position]);
                intent.putExtra("type", type[position]);
                intent.putExtra("bookId", bookId[position]);
                intent.putExtra("author", author[position]);
                intent.putExtra("amount", amount[position]);
                startActivity(intent);


            }
        });


    }



    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM Book", null);
        bookArrayList.clear();
        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            String bookId = cursor.getString(0);
            String name = cursor.getString(1);
            String type = cursor.getString(2);
            String author = cursor.getString(3);
            int amount = cursor.getInt(4);
            byte[] image = cursor.getBlob(5);
            bookArrayList.add(new Book(bookId, name, type, "", "", image));
        }
        bookListAdapter.notifyDataSetChanged();


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
                        BookActivity.this,
                        AboutActivity.class
                );
                startActivity(intent1);
                break;
            case R.id.mnuExit:
                finish();
                break;
            case R.id.mnuBook:
                Intent intent2 = new Intent(
                        BookActivity.this,
                        BookActivity.class
                );
                startActivity(intent2);
                break;
            case R.id.mnuType:
                Intent intent3 = new Intent(
                        BookActivity.this,
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