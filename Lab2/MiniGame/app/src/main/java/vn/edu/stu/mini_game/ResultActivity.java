package vn.edu.stu.mini_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {
    public static ArrayList<String> kbg = new ArrayList<String>(){
        {
            add("KÉO");
            add("BÚA");
            add("BAO");
        }
    };
    TextView txtBanRa, txtMayra, txtKetqua;
    Button btnTroLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        addControls();
        addEvents();
        layThongTinBanChon();
    }

    private void layThongTinBanChon() {
        Intent intent= getIntent();
        if (intent.hasExtra("BANRA")){
            String banRa = intent.getStringExtra("BANRA");
            if (banRa==" "){
                txtKetqua.setText("không có thông tin");
            }else{
                txtBanRa.setText("Bạn ra: " + banRa);
                int iBanRa = kbg.indexOf(banRa);
                int iMayRa = new Random().nextInt(kbg.size());
                String mayRa = kbg.get(iMayRa);
                txtMayra.setText("Máy ra : " + mayRa);

                int kq = iBanRa - iMayRa;
                if (kq==0) txtKetqua.setText("Kết quả : HÒA");
                else if(kq == 1 || kq ==-2){
                    txtKetqua.setText("Kết quả : BẠN THẮNG ");
                }else txtKetqua.setText("kết quả : Bạn Thua");
            }
        }else{
            txtBanRa.setText("không có thông tin");
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
        txtBanRa = findViewById(R.id.txtBanRa);
        txtMayra = findViewById(R.id.txtMayra);
        txtKetqua = findViewById(R.id.txtKetqua);
        btnTroLai = findViewById(R.id.btnTroLai);
    }
}