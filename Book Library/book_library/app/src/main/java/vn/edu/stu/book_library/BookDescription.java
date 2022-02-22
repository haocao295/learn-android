package vn.edu.stu.book_library;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.stu.book_library.list.BookActivity;
import vn.edu.stu.book_library.list.TypeActivity;

public class BookDescription extends AppCompatActivity {

    ImageView desImage;
    TextView desName, desType, desAmount, desAuthor, desId, txtAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_description);


        desImage = findViewById(R.id.desImage);
        desName = findViewById(R.id.desName);
        desType = findViewById(R.id.desType);
        desId = findViewById(R.id.desId);
        desAuthor = findViewById(R.id.desAuthor);
        desAmount = findViewById(R.id.desAmount);

        Intent intent = this.getIntent();

        int imageId = intent.getIntExtra("image", R.drawable.a);
        String name = intent.getStringExtra("name");
        String type = intent.getStringExtra("type");
        String bookId = intent.getStringExtra("bookId");
        String author = intent.getStringExtra("author");
        String amount = intent.getStringExtra("amount");


        desImage.setImageResource(imageId);
        desName.setText(name);
        desType.setText(type);
        desId.setText(bookId);
        desAuthor.setText(author);
        desAmount.setText(amount);


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
                        BookDescription.this,
                        AboutActivity.class
                );
                startActivity(intent1);
                break;
            case R.id.mnuExit:
                finish();
                break;
            case R.id.mnuBook:
                Intent intent2 = new Intent(
                        BookDescription.this,
                        BookActivity.class
                );
                startActivity(intent2);
                break;
            case R.id.mnuType:
                Intent intent3 = new Intent(
                        BookDescription.this,
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