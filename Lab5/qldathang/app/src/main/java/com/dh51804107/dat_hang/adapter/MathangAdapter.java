package com.dh51804107.dat_hang.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.dh51804107.dat_hang.R;
import com.dh51804107.dat_hang.model.Mathang;
import com.dh51804107.dat_hang.util.FormatUtil;

public class MathangAdapter extends ArrayAdapter<Mathang> {
    Activity context;
    int resource;
    List<Mathang> objects;

    public MathangAdapter(Activity context, int resource, List<Mathang> objects){
        super(context,resource,objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);
        TextView txtTen = item.findViewById(R.id.txtTen);
        TextView txtDongia = item.findViewById(R.id.txtDonGia);
        EditText txtSoLuong = item.findViewById(R.id.txtSoluong);
        final Mathang mh = this.objects.get(position);
        txtTen.setText(mh.getTen());
        txtDongia.setText(FormatUtil.formatNumber(mh.getDongia()));
        txtSoLuong.setText(mh.getSoluong() + "");
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    mh.setSoluong(Integer.parseInt(s.toString()));
                }catch (Exception ex){
                    mh.setSoluong(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        txtSoLuong.addTextChangedListener(textWatcher);
        return  item;
    }
}
