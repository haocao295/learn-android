package vn.edu.stu.minigame_images;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageButton btnkeo, btnBua, btnGiay, btnNghiChoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnkeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyRa(v);
            }
        });
        btnBua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyRa(v);
            }
        });
        btnGiay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyRa(v);
            }
        });
        btnNghiChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void xulyRa (View v){
        String banRa = ((Button) v).getText().toString().toUpperCase();
        Intent intent = new Intent(
                MainActivity.this,
                ResultActivity.class
        );
        intent.putExtra("BANRA",banRa);
        startActivity(intent);
    }

    private void addControls() {

        btnkeo = findViewById(R.id.btnkeo);
        btnBua = findViewById(R.id.btnBua);
        btnGiay = findViewById(R.id.btnGiay);
        btnNghiChoi = findViewById(R.id.btnNghiChoi);

    }
}