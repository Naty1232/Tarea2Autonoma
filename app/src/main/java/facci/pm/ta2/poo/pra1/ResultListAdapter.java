package facci.pm.ta2.poo.pra1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import facci.pm.ta2.poo.datalevel.DataObject;


public class ResultListAdapter extends BaseAdapter {

    private Context mContext;
    Cursor cursor;
    public Activity mActivity;
    public ArrayList<DataObject> m_array;

    public ResultListAdapter(Context context,Cursor cur)
    {
        super();
        mContext=context;
        cursor=cur;
    }

    public int getCount()
    {
        return  m_array.size();
    }

    public View getView(int position,  View view, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        RelativeLayout vp = (RelativeLayout)inflater.inflate(R.layout.activity_results_row, null);
        DataObject object = m_array.get(position);
        TextView title = (TextView)vp.findViewById(R.id.title);
        title.setText((String) object.get("name"));
        ImageView  thumbnail=  (ImageView)vp.findViewById(R.id.thumbnail);
        thumbnail.setImageBitmap((Bitmap) object.get("image"));
        TextView price = (TextView)vp.findViewById(R.id.price);
        price.setText((String) object.get("price"));
        view = vp;
        return view;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
}
