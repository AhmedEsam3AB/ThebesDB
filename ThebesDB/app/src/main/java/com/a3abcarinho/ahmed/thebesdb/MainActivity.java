package com.a3abcarinho.ahmed.thebesdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SqliteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_to_do);
        RecyclerView studentView = (RecyclerView)findViewById(R.id.student_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        studentView.setLayoutManager(linearLayoutManager);
        studentView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);
        List<student> allStudents = mDatabase.listStudents();
        if(allStudents.size() > 0){
            studentView.setVisibility(View.VISIBLE);
            StudentAdapter mAdapter = new StudentAdapter(this, allStudents);
            studentView.setAdapter(mAdapter);

        }else {
            studentView.setVisibility(View.GONE);
            Toast.makeText(this, "There is no student in the database. Start adding now", Toast.LENGTH_LONG).show();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTaskDialog();
            }
        });
    }
    private void addTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_student_layout, null);
        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        final EditText codeField = (EditText)subView.findViewById(R.id.enter_code);
        final EditText gradeField = (EditText)subView.findViewById(R.id.enter_grade);
        final EditText gpaField = (EditText)subView.findViewById(R.id.enter_gpa);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new student");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("ADD STUDENT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                int code = 0;
                double gpa = 0;
                final String grade = gradeField.getText().toString();


                if(TextUtils.isEmpty(name)  ){
                    Toast.makeText(MainActivity.this, "Name is Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        code = Integer.parseInt(codeField.getText().toString());

                      gpa = Double.parseDouble(gpaField.getText().toString());



                    } catch(NumberFormatException nfe) {
                        Toast.makeText(MainActivity.this, "Missing input/s. Check your input values", Toast.LENGTH_SHORT).show();
                    }
                    student newStudent = new student(name, code, grade , gpa);
                    mDatabase.addStudent(newStudent);
                    finish();
                    startActivity(getIntent());

                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDatabase != null){
            mDatabase.close();
        }
    }
}