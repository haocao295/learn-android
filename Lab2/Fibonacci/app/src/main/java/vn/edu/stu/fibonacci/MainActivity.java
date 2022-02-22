package vn.edu.stu.fibonacci;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtN;
    Button btnGuiN;
    TextView txtDSFibo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGuiN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this,
                        MainActivity2.class
                );
                int n = Integer.parseInt(txtN.getText().toString());
                intent.putExtra("N",n);
                //gọi lênh mở màn hình và kiểm soát kết quả trả về
                // đổi số 1 : Intent muốn gửi đi
                //Đổi số 2 : mã đánh dấu cho gói này
                startActivityForResult(intent,1);
            }
        });
    }
    //requestCode đây là mã gửi đi yêu cầu mà ta đánh dấu lúc gửi đi
    //resultCode đây là mã đánh dấu kết quả trả về từ màn hình kia
    // data đây là gói tin mà màn hình kia trả về

    protected  void onActivityResult(int requestCode, int resultCode, Intent datta) {
        super.onActivityResult(requestCode, resultCode, datta);
        if (requestCode == 1) {
            if (resultCode == 2) {
                ArrayList<Integer> dsFibo = datta.getIntegerArrayListExtra("DSFIBO");
                String result = "";
                for (int fibo : dsFibo) {
                    result += fibo + "\n";
                }
                txtDSFibo.setText(result);
            }
        }
    }

    private void addControls() {
        txtN = findViewById(R.id.txtN);
        btnGuiN = findViewById(R.id.btnGuiN);
        txtDSFibo = findViewById(R.id.txtDSFibo);
    }
}