package vn.edu.stu.fibonacci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    TextView txtN;
    Button btnTaoDayFibo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControls();
        viewDataFromIntent();
        addEvents();
    }

    private void addEvents() {
        btnTaoDayFibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(txtN.getText().toString());
                ArrayList<Integer> fibos = new ArrayList<>();
                if (n > 0) fibos.add(1);
                if (n > 1) fibos.add(1);
                if (n > 2){
                    for (int i=2; i<n; i++){
                        fibos.add(fibos.get(i-1) + fibos.get(i -2));
                    }
                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("DSFIBO", fibos);

                //yêu cầu returnIntent sẽ là intent trả về cho MainActivity
                setResult(2,returnIntent);

                //đóng Activity hiện tại , trở về MainACtivity
                finish();
            }
        });
    }

    private void viewDataFromIntent() {
        Intent intent = getIntent();
        int n = intent.getIntExtra("N",0);
        txtN.setText(n + "");
    }

    private void addControls() {
        txtN = findViewById(R.id.txtN);
        btnTaoDayFibo = findViewById(R.id.btnTaoDayFibo);
    }
}