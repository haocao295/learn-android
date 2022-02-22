package vn.edu.stu.qlynhanvien;



import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import vn.edu.stu.qlynhanvien.R;
import vn.edu.stu.qlynhanvien.model.Congviec;
import vn.edu.stu.qlynhanvien.util.FormatUtil;

public class ThemSuaCongviecActivity extends AppCompatActivity {
    EditText txtTen;
    TextView txtNgay,txtGio;
    ImageButton btnDatePicker,btnTimePicker;
    Button btnLuu;
    Calendar calendar;
    Congviec chon;
    int resultCode = 115;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sua_congviec);
        addControls();
        getIntentData();
        addEvents();
    }

    private void addEvents() {
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonNgay();
            }
        });
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonGio();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuu();
            }
        });
    }

    private void xuLyLuu() {
        if(chon == null){
            chon = new Congviec();
        }
        chon.setTen(txtTen.getText().toString());
        chon.setHan(calendar.getTime());
        Intent intent = getIntent();
        intent.putExtra("TRA",chon);
        setResult(resultCode,intent);
        finish();
    }

    private void xuLyChonGio() {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                txtGio.setText(FormatUtil.formatTime(calendar.getTime()));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                ThemSuaCongviecActivity.this,
                listener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
        );
        timePickerDialog.show();
    }

    private void xuLyChonNgay() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DATE,dayOfMonth);
                txtNgay.setText(FormatUtil.formatDate(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ThemSuaCongviecActivity.this,
                listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );
        datePickerDialog.show();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if(intent.hasExtra("CHON")){
            chon = (Congviec) intent.getSerializableExtra("CHON");
            if(chon != null){
                txtTen.setText(chon.getTen());
                calendar.setTime(chon.getHan());
                txtNgay.setText(FormatUtil.formatDate(calendar.getTime()));
                txtGio.setText(FormatUtil.formatTime(calendar.getTime()));
            }else {
                resetView();
            }
        }else {
            resetView();
        }
    }

    private void resetView() {
        txtTen.setText("");
        txtNgay.setText("dd/MM/yyyy");
        txtGio.setText("hh:mm aa");
        calendar = Calendar.getInstance();
        chon = null;
    }

    private void addControls() {
        txtGio = findViewById(R.id.txtGio);
        txtTen = findViewById(R.id.txtTen);
        txtNgay = findViewById(R.id.txtNgay);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnTimePicker = findViewById(R.id.btnTimePicker);
        btnLuu = findViewById(R.id.btnLuu);
        calendar = Calendar.getInstance();
        chon = null;
    }
}