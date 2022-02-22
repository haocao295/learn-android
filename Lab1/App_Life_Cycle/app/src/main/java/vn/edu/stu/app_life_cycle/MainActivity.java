package vn.edu.stu.app_life_cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void processFCA(View view) {
        Intent intent = new Intent(
                MainActivity.this,
                FullCover.class
        );
        startActivity(intent);
    }

    public void processPBA(View view) {
        Intent intent = new Intent(
                MainActivity.this,
                PartCover.class
        );
        startActivity(intent);
    }

    public void processCancel(View view) {
       finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(
                    MainActivity.this,
                        "onStart has been",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(
                MainActivity.this,
                "onPause has been",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(
                MainActivity.this,
                "onResume has been",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(
                MainActivity.this,
                "onStop has been",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(
                MainActivity.this,
                "onRestart has been",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(
                MainActivity.this,
                "onDestroy has been",
                Toast.LENGTH_SHORT
        ).show();
    }
}