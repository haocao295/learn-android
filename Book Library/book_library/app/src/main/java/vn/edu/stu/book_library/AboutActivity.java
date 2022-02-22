package vn.edu.stu.book_library;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import vn.edu.stu.book_library.list.BookActivity;
import vn.edu.stu.book_library.list.TypeActivity;

public class AboutActivity extends AppCompatActivity implements OnMapReadyCallback {
    TextView txtAction, aboutPhone;
    private static final int Request_call =1;
    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutPhone = findViewById(R.id.aboutPhone);



         SupportMapFragment supportMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);




        addEvents();

    }

    private void addEvents() {
        aboutPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }


    private void makePhoneCall() {
        String phone = aboutPhone.getText().toString();
        if (ContextCompat.checkSelfPermission(AboutActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AboutActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, Request_call);
        } else {
            String dial = "tel:" + phone;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == Request_call) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(AboutActivity.this, "Quyền bị từ chối!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        gMap = googleMap;
        LatLng DHCNSG = new LatLng(10.7380096287,106.677825451);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DHCNSG,13));
        gMap.addMarker(new MarkerOptions().position(DHCNSG).title("Đại học công nghệ Sài Gòn"));


    }


    String name = "Cao Chiến Hào";
    String id = "DH51804107";
    String cl = "D18_TH11";
    String phone = "0948230620";
    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(phone));
    String email = "haocaochien@gmail.com";

    private View.OnClickListener next = new View.OnClickListener() {

        public void onClick(View v) {
            TextView num=(TextView)findViewById(R.id.aboutPhone);
            String phone = "tel:" + aboutPhone.getText().toString().trim();
            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phone));
            startActivity(callIntent);
        }
    };










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuAbout:
                Intent intent1 = new Intent(
                        AboutActivity.this,
                        AboutActivity.class
                );
                startActivity(intent1);
                break;
            case R.id.mnuExit:
                finish();
                break;
            case R.id.mnuBook:
                Intent intent2 = new Intent(
                        AboutActivity.this,
                        BookActivity.class
                );
                startActivity(intent2);
                break;
            case R.id.mnuType:
                Intent intent3 = new Intent(
                        AboutActivity.this,
                        TypeActivity.class
                );
                startActivity(intent3);
                break;
            default:
                txtAction.setText("");

        }
        return super.onOptionsItemSelected(item);
    }
}