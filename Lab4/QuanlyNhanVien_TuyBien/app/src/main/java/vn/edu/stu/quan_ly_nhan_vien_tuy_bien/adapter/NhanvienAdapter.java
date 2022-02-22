package vn.edu.stu.quan_ly_nhan_vien_tuy_bien.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.edu.stu.quan_ly_nhan_vien_tuy_bien.R;
import vn.edu.stu.quan_ly_nhan_vien_tuy_bien.model.Nhanvien;

public class NhanvienAdapter extends ArrayAdapter<Nhanvien> {

    Activity context;
    int resource;
    List<Nhanvien> object;
    public NhanvienAdapter(
            Activity context,
            int resource,
            List<Nhanvien> object
    ){
        super(context, resource, object);
        this.context = context;
        this.resource = resource;
        this.object = object;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);
        TextView txtMa = item.findViewById(R.id.txtMa);
        TextView txtTen = item.findViewById(R.id.txtTen);
        TextView txtSDT = item.findViewById(R.id.txtSDT);

        Nhanvien nv = this.object.get(position);
        txtMa.setText(nv.getMa());
        txtTen.setText(nv.getTen());
        txtSDT.setText(nv.getSdt());
        return item;
    }
}

