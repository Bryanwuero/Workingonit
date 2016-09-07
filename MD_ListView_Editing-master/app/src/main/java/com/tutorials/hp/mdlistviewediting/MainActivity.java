package com.tutorials.hp.mdlistviewediting;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tutorials.hp.mdlistviewediting.mDB.DBHelper;
import com.tutorials.hp.mdlistviewediting.mData.Activity;
import com.tutorials.hp.mdlistviewediting.mData.CRUD;
import com.tutorials.hp.mdlistviewediting.mData.ActivityCollection;
import com.tutorials.hp.mdlistviewediting.mListView.CustomAdapter;

import java.util.ArrayList;

//Activity en donde le paso mi Activity y descripcion y fechas de mis actividades y obtiene los datos
public class MainActivity extends AppCompatActivity {

    ListView lv;
    EditText nameEditText,descEditText, startEditText, finishEditText;
    Button saveBtn;
    CustomAdapter adapter,adapter2;
    CRUD crud;
    DBHelper db;


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

        db = new DBHelper(this);

        ArrayList<Activity> act = db.getActivities();
        adapter2 = new CustomAdapter(this, act);

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

                String name = nameEditText.getText().toString();
                String desc = descEditText.getText().toString();
                String start = startEditText.getText().toString();
                String finish = finishEditText.getText().toString();
                db.saveRecord(name, desc, start, finish);
               // db.saveRecord("Run", "5 km", "20/09/2016", "21/09/2016");
                Log.d("creado", "creados");

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
        lv.setAdapter(adapter2);
    }

   /* public void saveRecords(View v){
        String name = nameEditText.getText().toString();
        String desc = descEditText.getText().toString();
        String start = startEditText.getText().toString();
        String finish = finishEditText.getText().toString();

        db.saveRecord(name, desc, start, finish);
        db.saveRecord("Run", "5 km", "20/09/2016", "21/09/2016");
        Log.d("creado", "creados");
    }*/

    public void findRecord(View v){
        String name = nameEditText.getText().toString();
        int result = db.getRecords(name);
        Toast.makeText(this, "activity found: " + result, Toast.LENGTH_SHORT).show();
    }

}










