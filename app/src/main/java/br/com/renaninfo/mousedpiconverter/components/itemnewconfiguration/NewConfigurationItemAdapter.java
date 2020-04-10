package br.com.renaninfo.mousedpiconverter.components.itemnewconfiguration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.renaninfo.mousedpiconverter.R;

public class NewConfigurationItemAdapter extends ArrayAdapter<NewConfigurationItemModel> implements View.OnClickListener{

    private ArrayList<NewConfigurationItemModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView tvDPI;
        TextView tvSensitivity;
    }

    public NewConfigurationItemAdapter(ArrayList<NewConfigurationItemModel> data, Context context) {
        super(context, R.layout.item_new_configuration, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        /*
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        NewConfigurationItemModel dataModel=(NewConfigurationItemModel)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
        */
        Toast.makeText(mContext, "Bla bla", Toast.LENGTH_SHORT).show();

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NewConfigurationItemModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_new_configuration, parent, false);
            viewHolder.tvDPI = (TextView) convertView.findViewById(R.id.tvDPI);
            viewHolder.tvSensitivity = (TextView) convertView.findViewById(R.id.tvSensitivity);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.tvDPI.setText(dataModel.getDpi());
        viewHolder.tvSensitivity.setText(dataModel.getSensitivity());

        // Return the completed view to render on screen
        return convertView;
    }
}
