package com.example.ktlab8_ph26503.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ktlab8_ph26503.Adapter.Adapter_Subject;
import com.example.ktlab8_ph26503.DAO_Subject.DAO_Subject;
import com.example.ktlab8_ph26503.DTO.Subject;
import com.example.ktlab8_ph26503.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class SubjectFragment extends Fragment {

    Context context;
    private RecyclerView rcv_list_sp;
    private FloatingActionButton fab_add_sp;
    ArrayList<Subject> listsubject;
    DAO_Subject dao_subject;
    Adapter_Subject adapter_subject;
    String TAG = "zzzzzzzzzzzz";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        context = getContext();


        dao_subject = new DAO_Subject(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        rcv_list_sp = view.findViewById(R.id.rcv_list_service);
        rcv_list_sp.setLayoutManager(new LinearLayoutManager(getActivity()));
        dao_subject= new DAO_Subject(context);
        dao_subject.opend();
        listsubject = new ArrayList<>();
        listsubject = dao_subject.selectAll();
        adapter_subject = new Adapter_Subject(listsubject, dao_subject);
        rcv_list_sp.setAdapter(adapter_subject);
        fab_add_sp = view.findViewById(R.id.fab_add_sp);

        fab_add_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getActivity().getLayoutInflater();
                View viewdialog = inflater.inflate(R.layout.dialog_add_subject, null);
                dialog.setView(viewdialog);
                TextInputLayout input_name, input_mamh, input_sotiet;
                input_name = viewdialog.findViewById(R.id.input_namemh_AddSub);
                input_mamh = viewdialog.findViewById(R.id.input_subject_AddSub);
                input_sotiet = viewdialog.findViewById(R.id.input_sotiet_AddSub);

                AlertDialog alertDialog = dialog.create();

                MaterialButton btn_add_dialog = viewdialog.findViewById(R.id.btn_AddSub);

                btn_add_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = input_name.getEditText().getText().toString().trim();
                        String mamh = input_mamh.getEditText().getText().toString().trim();
                        String sotietStr = input_sotiet.getEditText().getText().toString().trim();

                        if (name.isEmpty()) {
                            input_name.setError("Không để trống tên môn học");
                            return;
                        } else {
                            input_name.setError(null);
                        }

                        if (mamh.isEmpty()) {
                            input_mamh.setError("Không để trống mã môn học");
                            return;
                        } else {
                            input_mamh.setError(null);
                        }

                        if (sotietStr.isEmpty()) {
                            input_sotiet.setError("Không để trống số tiết");
                            return;
                        } else {
                            input_sotiet.setError(null);
                        }

                        int sotiet = Integer.parseInt(sotietStr);

                        if (sotiet < 15) {
                            input_sotiet.setError("Số tiết tối thiểu phải là 15");
                            return;
                        } else {
                            input_sotiet.setError(null);
                        }

                        Subject subject = new Subject();
                        subject.setTenmh(name);
                        subject.setSotiet(sotiet);
                        subject.setMamh(mamh);

                        long ketqua = dao_subject.AddSP(subject);
                        if (ketqua > 0) {
                            listsubject.clear();
                            listsubject.addAll(dao_subject.selectAll());
                            adapter_subject.notifyDataSetChanged();

                            alertDialog.dismiss();

                            Toast.makeText(getActivity(), "Thêm mới thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Thêm mới thất bại", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertDialog.show();

                listsubject = dao_subject.selectAll();

                adapter_subject = new Adapter_Subject(listsubject, dao_subject);
                rcv_list_sp.setAdapter(adapter_subject);
            }
        });
    }


}