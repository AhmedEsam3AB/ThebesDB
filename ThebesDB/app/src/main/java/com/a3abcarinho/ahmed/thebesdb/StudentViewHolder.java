package com.a3abcarinho.ahmed.thebesdb;

/**
 * Created by ahmed on 07/10/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class StudentViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView deleteStudent;
    public  ImageView editStudent;
    public StudentViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.student_name);
        deleteStudent = (ImageView)itemView.findViewById(R.id.delete_student);
        editStudent = (ImageView)itemView.findViewById(R.id.edit_student);
    }
}