package vn.edu.stu.book_library;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.stu.book_library.list.BookActivity;
import vn.edu.stu.book_library.list.TypeActivity;

public class HomeActivity extends AppCompatActivity {
    TextView txtAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }





    public void processBLA(View view) {
        Intent intent = new Intent(
                HomeActivity.this,
                BookActivity.class
        );
        startActivity(intent);
    }

    public void processTLA(View view) {
        Intent intent = new Intent (
                HomeActivity.this,
                TypeActivity.class
        );
        startActivity(intent);
    }

    public void processCancel(View view) {
        finish();
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
                        HomeActivity.this,
                        AboutActivity.class
                );
                startActivity(intent1);
                break;
            case R.id.mnuExit:
                finish();
                break;
            case R.id.mnuBook:
                Intent intent2 = new Intent(
                        HomeActivity.this,
                        BookActivity.class
                );
                startActivity(intent2);
                break;
            case R.id.mnuType:
                Intent intent3 = new Intent(
                        HomeActivity.this,
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