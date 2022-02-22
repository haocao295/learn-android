package vn.edu.stu.book_library.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.stu.book_library.db.Database;
import vn.edu.stu.book_library.R;
import vn.edu.stu.book_library.model.Type;

public class TypeListAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Type> typeArrayList;
    Type tp;

    public TypeListAdapter(Activity context, ArrayList<Type> typeArrayList) {
        this.context = context;
        this.typeArrayList = typeArrayList;
    }

    @Override
    public int getCount() {
        return typeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.list_type, null);
        TextView type = item.findViewById(R.id.nametype);
        TextView typeId = item.findViewById(R.id.idType);

        Type typ = typeArrayList.get(position);
        type.setText(typ.type);
        typeId.setText(typ.typeId);

        return item;
    }


    public void remove(String typeId) {
        SQLiteDatabase database = Database.initDatabase(context, "bookDB.db");
        database.delete("Type","typeId = ?", new String[]{typeId} );
        typeArrayList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM Type",null);
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String type = cursor.getString(1);

            typeArrayList.add(new Type(typeId, type));
        }
        notifyDataSetChanged();
    }


}
