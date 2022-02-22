package vn.edu.stu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtMSV , txtHoTen, txtNamSinh;
    Button btnGuiThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGuiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msv = txtMSV.getText().toString();
                String hoTen = txtHoTen.getText().toString();
                int namSinh = Integer.parseInt(txtNamSinh.getText().toString());
                student sv =  new student(msv,hoTen,namSinh);
                Intent intent = new Intent(
                        MainActivity.this,
                        info_student.class
                );
                intent.putExtra("SINHVIEN",sv);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        txtMSV = findViewById(R.id.txtMSV);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtNamSinh = findViewById(R.id.txtNamSinh);
        btnGuiThongTin = findViewById(R.id.btnGuiThongTin);
    }
}