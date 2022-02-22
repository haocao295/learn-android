package vn.edu.stu.quan_ly_nhan_vien_tuy_bien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText txtMa, txtTen, txtSDT;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        addControls();
    }

    private void addControls() {
        txtMa = findViewById(R.id.txtMa);
        txtTen = findViewById(R.id.txtTen);
        txtSDT = findViewById(R.id.txtSDT);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}