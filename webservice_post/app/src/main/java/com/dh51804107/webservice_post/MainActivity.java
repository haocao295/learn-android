package com.dh51804107.webservice_post;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dh51804107.webservice_post.model.Sinhvien;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final String SERVER = "http://192.168.100.13/wsck/api_post.php";

    EditText txtMaSv, txtTenSv;
    Button btnLuu;
    ArrayList<Sinhvien> dsSv;
    ArrayAdapter<Sinhvien> adapter;
    ListView lvSv;
    Sinhvien chon= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        addControls();
        hienthiDanhsach();
        addEvents();
    }
    private void addControls() {
        txtMaSv=findViewById (R.id.txtMaSv);
        txtTenSv=findViewById (R.id.txtTenSv);
        btnLuu=findViewById ((R.id.btnLuu));
        lvSv=findViewById (R.id.lvSv);
        dsSv = new ArrayList<> ();
        adapter = new ArrayAdapter<> (
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                dsSv
        );lvSv.setAdapter (adapter);
    }
    private void addEvents(){
        btnLuu.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (chon == null){
                    int maSv = Integer.parseInt (txtMaSv.getText ().toString ());
                    String tenSv = txtTenSv.getText ().toString ();
                    Sinhvien sv = new Sinhvien (maSv,tenSv);
                    xuliThemSv(sv);
                    txtMaSv.setText ("");
                    txtTenSv.setText ("");
                    txtMaSv.requestFocus ();
                }else{
                    String tenSv = txtTenSv.getText ().toString ();
                    chon.setTenSV (tenSv);
                    xuliCapnhatSv(chon);
                    chon=null;
                    txtMaSv.setText ("");
                    txtTenSv.setText ("");
                    txtMaSv.setEnabled (true);
                    txtMaSv.requestFocus ();
                }
            }
        });
        lvSv.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                if (position >= 0 && position <dsSv.size ()){
                    chon = dsSv.get (position);
                    txtMaSv.setText (chon.getMaSV() + "");
                    txtTenSv.setText (chon.getTenSV ());
                    txtMaSv.setEnabled (false);
                }
            }
        });
        lvSv.setOnItemLongClickListener (
                new AdapterView.OnItemLongClickListener () {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                   int position, long id) {
                        if (position >=0 && position < dsSv.size ()){
                            Sinhvien sv = dsSv.get (position);
                            xuliXoasv(sv);
                        }
                        return true;
                    }
                });
    }
    private void hienthiDanhsach(){
        RequestQueue requestQueue = Volley.newRequestQueue (
                MainActivity.this
        );
        Response.Listener<String> responseListener =
                new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response) {
                        try {
                            dsSv.clear ();
                            JSONArray jsonArray = new JSONArray (response);
                            int len = jsonArray.length ();
                            for (int i=0;i<len;i++){
                                JSONObject jsonObject = jsonArray.getJSONObject (i);
                                int ma = jsonObject.getInt ("masv");
                                String ten = jsonObject.getString ("tensv");
                                dsSv.add (new Sinhvien (ma,ten));
                            }

                            adapter.notifyDataSetChanged ();
                        }catch (Exception ex){
                        }
                    }
                };
        Response.ErrorListener errorListener = new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText (
                        MainActivity.this,error.getMessage (),Toast.LENGTH_LONG
                ).show ();
            }
        };
        Uri.Builder builder = Uri.parse (SERVER).buildUpon ();
        //builder.appendQueryParameter ("action","getall");
        String url = builder.build ().toString ();
        StringRequest request = new StringRequest (
                Request.Method.POST,
                url,
                responseListener,
                errorListener
        ){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<> ();
                params.put ("action","getall");
                return params;
            }
        };
        request.setRetryPolicy (
                new DefaultRetryPolicy (
                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        requestQueue.add (request);
    }
    private  void xuliThemSv(final Sinhvien sv){
        RequestQueue requestQueue = Volley.newRequestQueue (
                MainActivity.this
        );
        Response.Listener<String> responseListener = new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject (response);
                    boolean result = jsonObject.getBoolean ("message");
                    if (result){
                        Toast.makeText (
                                MainActivity.this,"Them thanh cong",Toast.LENGTH_SHORT
                        ).show ();
                        hienthiDanhsach ();
                    }else {
                        Toast.makeText (
                                MainActivity.this,"Them that bai",Toast.LENGTH_SHORT
                        ).show ();
                    }
                }catch (Exception ex){
                }
            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText (
                        MainActivity.this,error.getMessage (),Toast.LENGTH_LONG
                ).show ();
            }
        };
        Uri.Builder builder = Uri.parse (SERVER).buildUpon ();
        //builder.appendQueryParameter ("action","insert");
        //builder.appendQueryParameter ("masv", sv.getMaSV ()+"");
        //builder.appendQueryParameter ("tensv", sv.getTenSV ());
        String url = builder.build ().toString ();
        StringRequest request = new StringRequest (
                Request.Method.POST,
                url,
                responseListener,
                errorListener
        ){
            @Override
            protected Map<String,String>getParams()throws AuthFailureError{
                Map<String,String>params = new HashMap<> ();
                params.put ("action","insert");
                params.put ("masv",sv.getMaSV ()+"");
                params.put ("tensv",sv.getTenSV ());
                return params;
            }
        };
        request.setRetryPolicy (
                new DefaultRetryPolicy (
                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        requestQueue.add (request);
    }
    private void    xuliXoasv (Sinhvien sv){
        RequestQueue requestQueue = Volley.newRequestQueue (
                MainActivity.this
        );
        Response.Listener<String> responseListener = new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject (response);
                    boolean result = jsonObject.getBoolean ("message");
                    if (result){
                        Toast.makeText (
                                MainActivity.this,"Xoa thanh cong",Toast.LENGTH_SHORT
                        ).show ();
                        hienthiDanhsach ();
                    }else {
                        Toast.makeText (
                                MainActivity.this,"Xoa that bai",Toast.LENGTH_SHORT
                        ).show ();
                    }
                }catch (Exception ex){
                }
            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText (
                        MainActivity.this,error.getMessage (),Toast.LENGTH_LONG
                ).show ();
            }
        };
        Uri.Builder builder = Uri.parse (SERVER).buildUpon ();
        //builder.appendQueryParameter ("action","insert");
        //builder.appendQueryParameter ("masv", sv.getMaSV ()+"");
        //builder.appendQueryParameter ("tensv", sv.getTenSV ());
        String url = builder.build ().toString ();
        StringRequest request = new StringRequest (
                Request.Method.POST,
                url,
                responseListener,
                errorListener
        ){
            @Override
            protected Map<String,String>getParams()throws AuthFailureError{
                Map<String,String>params = new HashMap<> ();
                params.put ("action","delete");
                params.put ("masv",sv.getMaSV ()+"");
                params.put ("tensv",sv.getTenSV ());
                return params;
            }
        };
        request.setRetryPolicy (
                new DefaultRetryPolicy (
                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        requestQueue.add (request);

    }
    private  void xuliCapnhatSv(Sinhvien sv){
        RequestQueue requestQueue = Volley.newRequestQueue (
                MainActivity.this
        );
        Response.Listener<String> responseListener = new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject (response);
                    boolean result = jsonObject.getBoolean ("message");
                    if (result){
                        Toast.makeText (
                                MainActivity.this,"Sua thanh cong",Toast.LENGTH_SHORT
                        ).show ();
                        hienthiDanhsach ();
                    }else {
                        Toast.makeText (
                                MainActivity.this,"Sua that bai",Toast.LENGTH_SHORT
                        ).show ();
                    }
                }catch (Exception ex){
                }
            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText (
                        MainActivity.this,error.getMessage (),Toast.LENGTH_LONG
                ).show ();
            }
        };
        Uri.Builder builder = Uri.parse (SERVER).buildUpon ();
        //builder.appendQueryParameter ("action","insert");
        //builder.appendQueryParameter ("masv", sv.getMaSV ()+"");
        //builder.appendQueryParameter ("tensv", sv.getTenSV ());
        String url = builder.build ().toString ();
        StringRequest request = new StringRequest (
                Request.Method.POST,
                url,
                responseListener,
                errorListener
        ){
            @Override
            protected Map<String,String>getParams()throws AuthFailureError{
                Map<String,String>params = new HashMap<> ();
                params.put ("action","update");
                params.put ("masv",sv.getMaSV ()+"");
                params.put ("tensv",sv.getTenSV ());
                return params;
            }
        };
        request.setRetryPolicy (
                new DefaultRetryPolicy (
                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        requestQueue.add (request);
    }
}