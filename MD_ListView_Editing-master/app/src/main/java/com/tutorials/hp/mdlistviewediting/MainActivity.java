package com.tutorials.hp.mdlistviewediting;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tutorials.hp.mdlistviewediting.mData.CRUD;
import com.tutorials.hp.mdlistviewediting.mData.ActivityCollection;
import com.tutorials.hp.mdlistviewediting.mData.Activity;
import com.tutorials.hp.mdlistviewediting.mListView.CustomAdapter;

//Actividad en donde le paso mi Actividad y descripcion y fechas de mis actividades y obtiene los datos
public class MainActivity extends AppCompatActivity {

    ListView lv;
    EditText nameEditText,descEditText, startEditText, finishEditText;
    Button saveBtn;
    CustomAdapter adapter;
    CRUD crud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        lv= (ListView) findViewById(R.id.lv);
        crud=new CRUD(ActivityCollection.getActivities());
        adapter=new CustomAdapter(this,crud.getActivities());

//        lv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog();
            }
        });

    }

    //Agrego los Datos
    //Muestro el dialogo
    private void displayDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("ADD ACTIVITY");
        d.setContentView(R.layout.dialog_layout);

        nameEditText= (EditText) d.findViewById(R.id.nameEditTxt);
        descEditText= (EditText) d.findViewById(R.id.descEditTxt);
        startEditText= (EditText) d.findViewById(R.id.startEditTxt);
        finishEditText= (EditText) d.findViewById(R.id.finishEditTxt);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity s=new Activity();
                s.setName(nameEditText.getText().toString());
                s.setDescription(descEditText.getText().toString());
                s.setDateStart(startEditText.getText().toString());
                s.setDateFinish(finishEditText.getText().toString());

                if(crud.addNew(s))
                {
                    nameEditText.setText("");
                    descEditText.setText("");
                    startEditText.setText("");
                    finishEditText.setText("");

                    lv.setAdapter(adapter);

                }
            }
        });

        d.show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        lv.setAdapter(adapter);
    }
}










