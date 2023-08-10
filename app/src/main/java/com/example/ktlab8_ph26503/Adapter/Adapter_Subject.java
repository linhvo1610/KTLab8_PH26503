package com.example.ktlab8_ph26503.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ktlab8_ph26503.DAO_Subject.DAO_Subject;
import com.example.ktlab8_ph26503.DTO.Subject;
import com.example.ktlab8_ph26503.R;
import com.example.ktlab8_ph26503.ViewHolder.ViewHolder_Subject;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Adapter_Subject extends RecyclerView.Adapter<ViewHolder_Subject> {
    ArrayList<Subject> listsubject;
    DAO_Subject dao_subject;
    Context context;

    public Adapter_Subject( ArrayList<Subject> listsubject, DAO_Subject dao_subject) {
        this.listsubject = listsubject;
        this.dao_subject = dao_subject;
    }
    @NonNull
    @Override
    public ViewHolder_Subject onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view_of_item= inflater.inflate(R.layout.item_sub,parent,false);
        ViewHolder_Subject toDoViewHolder=new ViewHolder_Subject(view_of_item);

        return toDoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Subject holder, int position) {
        final int index = position;
        Subject list = listsubject.get(index);
        holder.tv_tenmh_sub.setText("Tên Môn Học: "+list.getTenmh());
        holder.tv_mamh_sub.setText("Mã: "+list.getMamh());
        holder.tv_sotiet_sub.setText("Số tiết: "+String.valueOf(list.getSotiet()));


        holder.imgUpdateSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(list, index);
            }
        });
        holder.imgDeleteSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete(list, index);
            }
        });

    }
    private TextInputLayout inputnamemhUPSub;
    private TextInputLayout inputsotietUpsub;
    private TextInputLayout inputmamhUPSub;
    private MaterialButton btnUPSP;

    public void dialogUpdate(Subject subject, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_subject);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);

        inputnamemhUPSub = dialog.findViewById(R.id.input_namemh_UPSub);
        inputmamhUPSub = dialog.findViewById(R.id.input_subject_UPSub);
        inputsotietUpsub = dialog.findViewById(R.id.input_sotiet_UPSub);

        btnUPSP = dialog.findViewById(R.id.btn_UPSub);

        inputnamemhUPSub.getEditText().setText(subject.getTenmh());
        inputmamhUPSub.getEditText().setText(subject.getMamh());
        inputsotietUpsub.getEditText().setText(subject.getSotiet()+"");
        btnUPSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkErrorUp()== true) {
                    subject.setTenmh(inputnamemhUPSub.getEditText().getText().toString().trim());
                    subject.setSotiet(Integer.parseInt(inputsotietUpsub.getEditText().getText().toString().trim()));
                    subject.setMamh(inputnamemhUPSub.getEditText().getText().toString().trim());

                    if (dao_subject.updateSP(subject) > 0) {
                        listsubject.set(index, subject);
                        notifyItemChanged(index);
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.show();
    }

    public void dialogDelete(Subject subject, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa môn: " + subject.getTenmh());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_subject.deleteSP(subject);
                if (res > 0) {
                    listsubject.remove(index);
                    notifyItemRemoved(index);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean checkErrorUp() {
        String name = inputnamemhUPSub.getEditText().getText().toString().trim();
        String mamh = inputmamhUPSub.getEditText().getText().toString().trim();
        String sotietStr = inputsotietUpsub.getEditText().getText().toString().trim();

        if (name.isEmpty() || mamh.isEmpty() || sotietStr.isEmpty()) {
            if (name.isEmpty()) {
                inputnamemhUPSub.setError("Tên môn học không được để trống");
            } else {
                inputnamemhUPSub.setError(null);
            }

            if (mamh.isEmpty()) {
                inputmamhUPSub.setError("Mã môn học không được để trống!");
            } else {
                inputmamhUPSub.setError(null);
            }

            if (sotietStr.isEmpty()) {
                inputsotietUpsub.setError("Số tiết không được để trống!");
            } else {
                inputsotietUpsub.setError(null);
            }

            if (!sotietStr.matches("[0-9]+")) {
                inputsotietUpsub.setError("Số tiết phải là số");
            } else {
                inputsotietUpsub.setError(null);
            }

            return false;
        } else {
            int sotiet = Integer.parseInt(sotietStr);
            if (sotiet < 15) {
                inputsotietUpsub.setError("Số tiết phải ít nhất là 15");
                return false;
            } else {
                inputsotietUpsub.setError(null);
                return true;
            }
        }
    }


    @Override
    public int getItemCount() {
        return listsubject == null ? 0 : listsubject.size();
    }
}
