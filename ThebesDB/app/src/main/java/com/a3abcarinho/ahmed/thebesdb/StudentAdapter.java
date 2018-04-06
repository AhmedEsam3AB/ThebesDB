package com.a3abcarinho.ahmed.thebesdb;

/**
 * Created by ahmed on 07/10/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder>{
    private Context context;
    private List<student> listStudent;
    private SqliteDatabase mDatabase;
    public StudentAdapter(Context context, List<student> listStudent) {
        this.context = context;
        this.listStudent = listStudent;
        mDatabase = new SqliteDatabase(context);
    }
    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_layout, parent, false);
        return new StudentViewHolder(view);
    }
    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        final student singleStudent = listStudent.get(position);
        holder.name.setText(singleStudent.getName());
        holder.editStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(singleStudent);
            }
        });
        holder.deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.deleteStudent(singleStudent.getId());
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }
    @Override
    public int getItemCount() {
        return listStudent.size();
    }
    private void editTaskDialog(final student student){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_student_layout, null);
        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        final EditText codeField = (EditText)subView.findViewById(R.id.enter_code);
        final EditText gradeField = (EditText)subView.findViewById(R.id.enter_grade);
        final EditText gpaField = (EditText)subView.findViewById(R.id.enter_gpa);
        if(student != null){
            nameField.setText(student.getName());
            codeField.setText(String.valueOf(student.getCode()));
            gradeField.setText(student.getGrade());
            gpaField.setText(String.valueOf(student.getGpa()));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit student");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("EDIT STUDENT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final int code = Integer.parseInt(codeField.getText().toString());
                final String grade = gradeField.getText().toString();
                final double gpa = Double.parseDouble(gpaField.getText().toString());
                if(TextUtils.isEmpty(name) || code <= 0){
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    mDatabase.updateStudent(new student(student.getId(), name, code, grade , gpa));

                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}