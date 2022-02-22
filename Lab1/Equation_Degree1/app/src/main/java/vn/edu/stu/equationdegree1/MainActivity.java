package vn.edu.stu.equationdegree1;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText txtA, txtB;
    Button btnFindResult;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnFindResult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(txtA.getText().toString());
                double b = Double.parseDouble(txtB.getText().toString());
                if(a == 0){
                    if(b != 0) {
                        txtResult.setText("Equation has no result");
                    }
                    else txtResult.setText("The equation has infinitely many results");
                } else txtResult.setText("Result of Equation, x= "+ (-b/a));
            }
        });
    }

    private void addControls() {
        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        btnFindResult = findViewById(R.id.btnFindResult);
        txtResult = findViewById(R.id.txtResult);
    }


}