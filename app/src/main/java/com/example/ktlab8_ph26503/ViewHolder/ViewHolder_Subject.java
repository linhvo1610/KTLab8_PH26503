package com.example.ktlab8_ph26503.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktlab8_ph26503.R;

public class ViewHolder_Subject extends RecyclerView.ViewHolder {
    public TextView tv_mamh_sub;
    public TextView tv_tenmh_sub;
    public TextView tv_sotiet_sub;
    public TextView tv_details_sp;
    public ImageView imgUpdateSub;
    public ImageView imgDeleteSub;
    public ViewHolder_Subject(@NonNull View itemView) {
        super(itemView);
        tv_tenmh_sub = itemView.findViewById(R.id.tv_name_ISub);
        tv_mamh_sub = itemView.findViewById(R.id.tv_mamh_ISub);
        tv_sotiet_sub = itemView.findViewById(R.id.tv_sotiet_ISub);
        imgUpdateSub = itemView.findViewById(R.id.img_updateSP);
        imgDeleteSub = itemView.findViewById(R.id.img_deleteSP);

    }
}
