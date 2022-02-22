package vn.edu.stu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class info_student extends AppCompatActivity {
    TextView txtThongTin;
    Button btnTroLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_student);
        addControls();
        addEvents();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("SINHVIEN")){
            student sv = (student) intent.getSerializableExtra("SINHVIEN");
            txtThongTin.setText(sv.toString());
        }
    }

    private void addEvents() {
        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        txtThongTin = findViewById(R.id.txtThongTin);
        btnTroLai = findViewById(R.id.btnTroLai);
    }
}