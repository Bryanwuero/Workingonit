package com.tutorials.hp.mdlistviewediting.mDetail;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorials.hp.mdlistviewediting.R;
import com.tutorials.hp.mdlistviewediting.mDB.DBHelper;
import com.tutorials.hp.mdlistviewediting.mData.Activity;
import com.tutorials.hp.mdlistviewediting.mData.CRUD;
import com.tutorials.hp.mdlistviewediting.mData.ActivityCollection;

//Esta es la actividad detallada donde se describe toda la actividad y susfechas y muestro los datos
public class DetailActivity extends AppCompatActivity {

    TextView nameTxt,descTxt, startDateTxt, finishDateTxt;
    String name,desc, start, finish;
    int position;
    EditText nameEditDetailTxt,descEditTextDetail, startDateEditDetailText, finishDateEditDetailText;
    Button updateBtn,deleteBtn;
    CRUD crud;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        crud=new CRUD(ActivityCollection.getActivities());
        db = new DBHelper(this);

        nameTxt= (TextView) findViewById(R.id.nameTxtDetail);
        descTxt= (TextView) findViewById(R.id.descDetailTxt);
        startDateTxt= (TextView) findViewById(R.id.startDetailTxt);
        finishDateTxt= (TextView) findViewById(R.id.finishDetailTxt);


        //Recivo los Datos
        Intent i=this.getIntent();
        name=i.getExtras().getString("NAME_KEY");
        desc=i.getExtras().getString("DESC_KEY");
        start=i.getExtras().getString("START_KEY");
        finish=i.getExtras().getString("FINISH_KEY");
        position=i.getExtras().getInt("POS_KEY");

        //Bind
        nameTxt.setText(name);
        descTxt.setText(desc);
        startDateTxt.setText(start);
        finishDateTxt.setText(finish);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayUpdateDialog();
            }
        });


    }

    //Agrego los datos
    //Muestro el dialogo
    private void displayUpdateDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("UPDATE/DELETE DATA");
        d.setContentView(R.layout.dialog_layout);

        nameEditDetailTxt= (EditText) d.findViewById(R.id.nameEditTxt);
        descEditTextDetail= (EditText) d.findViewById(R.id.descEditTxt);
        startDateEditDetailText= (EditText) d.findViewById(R.id.startEditTxt);
        finishDateEditDetailText= (EditText) d.findViewById(R.id.finishEditTxt);
        updateBtn= (Button) d.findViewById(R.id.saveBtn);
        deleteBtn= (Button) d.findViewById(R.id.deleteBtn);

        nameEditDetailTxt.setText(name);
        descEditTextDetail.setText(desc);
        startDateEditDetailText.setText(start);
        finishDateEditDetailText.setText(finish);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity s=new Activity();

                name=nameEditDetailTxt.getText().toString();
                desc=descEditTextDetail.getText().toString();
                start=startDateEditDetailText.getText().toString();
                finish=finishDateEditDetailText.getText().toString();


                s.setName(name);
                s.setDescription(desc);
                s.setDateStart(start);
                s.setDateFinish(finish);

                if(crud.update(position,s))
                {
                    nameEditDetailTxt.setText(name);
                    descEditTextDetail.setText(desc);
                    startDateEditDetailText.setText(start);
                    finishDateEditDetailText.setText(finish);


                    nameTxt.setText(name);
                    descTxt.setText(desc);
                    startDateTxt.setText(start);
                    finishDateTxt.setText(finish);

                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(crud.delete(position))
                {
                    String name=nameEditDetailTxt.getText().toString();
                    db.deleteRecords(name);

                    //mato la actividad
                    DetailActivity.this.finish();
                }
            }
        });

        d.show();

    }

    /*public void saveRecord(View v){
        String name = nameEditDetailTxt.getText().toString();
        String desc = descEditTextDetail.getText().toString();
        String start = startDateEditDetailText.getText().toString();
        String finish = finishDateEditDetailText.getText().toString();

        db.saveRecord(name, desc, start, finish);
    }

    public void findRecord(View v){
        String name = nameEditDetailTxt.getText().toString();
        int result = db.getRecord(name);
        Toast.makeText(this, "activity found: " + result, Toast.LENGTH_SHORT).show();
    }*/

    public void deleteRecord(View v){
        String name = nameEditDetailTxt.getText().toString();
        int result = db.deleteRecords(name);
        Toast.makeText(this, "result: " + result, Toast.LENGTH_SHORT).show();
    }


}