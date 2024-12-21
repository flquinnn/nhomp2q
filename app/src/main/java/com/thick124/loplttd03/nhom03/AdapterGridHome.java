package com.thick124.loplttd03.nhom03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterGridHome extends ArrayAdapter<itemGrid> {
    Context context;
    int IdLayout;
    ArrayList<itemGrid> mylist;
    DecimalFormat decimalFormat = new DecimalFormat("#");
    public AdapterGridHome(Context context, int idLayout, ArrayList<itemGrid> mylist) {
        super(context, idLayout,mylist);
        this.context = context;
        IdLayout = idLayout;
        this.mylist = mylist;
    }
    // gọi hàm getView đẻ Adapter để lấy dữ liệu và hiển thị

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater myflactor = LayoutInflater.from(context);
            convertView = myflactor.inflate(IdLayout, parent, false);
        }
//        LayoutInflater myflactor = LayoutInflater.from(context);
//        //tao view
//        convertView = myflactor.inflate(IdLayout, null);
        //lay 1 phan tu trong bang
        itemGrid myitem = mylist.get(position);
        ImageView img_item = convertView.findViewById(R.id.imageView5);
//        img_item.setImageResource(myitem.getImage());
        Glide.with(context)
                .load(myitem.getImage()) // URL ảnh
                .placeholder(R.drawable.cafe) // Ảnh hiển thị tạm khi tải
                .error(R.drawable.new_pd) // Ảnh hiển thị nếu tải thất bại
                .into(img_item);
        TextView txt_title = convertView.findViewById(R.id.title);
        txt_title.setText(myitem.getTitle());
        TextView price = convertView.findViewById(R.id.price);
        price.setText(myitem.getSotien());
        int colorRed = ContextCompat.getColor(this.getContext(), R.color.spending);
        int colorBlue = ContextCompat.getColor(this.getContext(), R.color.colorAccent);
        if(!myitem.getType()){
            price.setTextColor(colorRed);
        } else {
            price.setTextColor(colorBlue);
        }
        return convertView;
    }
}
