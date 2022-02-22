package vn.edu.stu.equation_degree2;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText txtA, txtB, txtC;
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
        btnFindResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(txtA.getText().toString());
                double b = Double.parseDouble(txtB.getText().toString());
                double c = Double.parseDouble(txtC.getText().toString());
                if (a == 0) {
                    if (b == 0) {
                        if (c == 0) {
                            txtResult.setText("the equation has infinitely many results");
                        } else {
                            txtResult.setText("the equation has no result");
                        }
                    } else {
                        txtResult.setText("the equation has a result: " + (-c / b));
                    }

                } else {
                    double del = b * b - 4 * a * c;
                    if (del < 0) {
                        txtResult.setText("the equation has no result");
                    } else if (del == 0) {
                        txtResult.setText("equation with double result x1 = x2 = " + -b / (2 * a));
                    } else {
                        txtResult.setText("the equation have 2 result: x1= " + (-b + Math.sqrt(del)) / (2 * a) + "  x2= " + (-b - Math.sqrt(del)) / (2 * a));
                    }

                }


            }
        });
    }





    private void addControls() {
        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        txtC = findViewById(R.id.txtC);
        btnFindResult = findViewById(R.id.btnFindResult);
        txtResult = findViewById(R.id.txtResult);
    }


}