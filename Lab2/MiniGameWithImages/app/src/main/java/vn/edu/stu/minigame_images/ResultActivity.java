package vn.edu.stu.minigame_images;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    ImageView imageView1, imageView2;
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
            int banRa = Integer.parseInt(intent.getStringExtra("BANRA"));
            if (banRa > 3){
                txtKetqua.setText("không có thông tin");
            }else if(banRa == 1 ){
                imageView1.setImageResource(R.drawable.keo);
                int iBanRa = kbg.indexOf(banRa);
                int iMayRa = new Random().nextInt(kbg.size());
                String mayRa = kbg.get(iMayRa);
                imageView2.setImageResource(R.drawable.bua);

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