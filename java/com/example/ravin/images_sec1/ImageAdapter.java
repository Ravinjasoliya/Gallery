package com.example.ravin.images_sec1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ravin on 31/12/2016.
 */
public class ImageAdapter extends BaseAdapter
{
    Context context;
    ArrayList<String> arrayList;

    public ImageAdapter(Context context,ArrayList<String> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;

    }
    @Override
    public int getCount() {
        return arrayList.size();
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
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.gridview, null);

        ImageView imageView=(ImageView)view.findViewById(R.id.image);
        try {
           // Bitmap bitmap = BitmapFactory.decodeFile(arrayList.get(position));
        //    imageView.setImageBitmap(Bitmap.createBitmap(bitmap));
            Glide.with(context).load(arrayList.get(position)).into(imageView);

        }catch (Exception e)
        {

        }catch (OutOfMemoryError error)
        {
            Toast.makeText(context, "out of memory", Toast.LENGTH_SHORT).show();
        }



        return view;
    }
}
