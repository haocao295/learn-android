package vn.edu.stu.book_library.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.stu.book_library.R;
import vn.edu.stu.book_library.model.Book;


public class BookListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Book> bookArrayList;

    public BookListAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @Override
    public int getCount() {
        return bookArrayList.size();
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
        View item = inflater.inflate(R.layout.list_book, null);
        ImageView pic = item.findViewById(R.id.pic);
        TextView bookId = item.findViewById(R.id.idBook);
        TextView name = item.findViewById(R.id.name);
        TextView type = item.findViewById(R.id.type);

        Book book = bookArrayList.get(position);
        bookId.setText(book.bookId);
        name.setText(book.name);
        type.setText(book.type);

        Bitmap bmpic = BitmapFactory.decodeByteArray(book.image, 0, book.image.length);
        pic.setImageBitmap(bmpic);
        return item;
    }
}

