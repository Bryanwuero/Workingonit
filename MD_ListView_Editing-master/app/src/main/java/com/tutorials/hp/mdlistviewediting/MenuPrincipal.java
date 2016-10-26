package com.tutorials.hp.mdlistviewediting;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tutorials.hp.mdlistviewediting.mData.Activity;
import com.tutorials.hp.mdlistviewediting.mData.CRUD;
import com.tutorials.hp.mdlistviewediting.mData.Usuario;
import com.tutorials.hp.mdlistviewediting.mListView.CustomAdapter;
import com.tutorials.hp.mdlistviewediting.mListView.ususss;

import java.util.ArrayList;
import java.util.Map;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseDatabase fdb;
    public DatabaseReference mDatabase;
    private DatabaseReference ref;
    public Usuario user;
    public FirebaseUser fbUser;
    private String actualUser;
    private View header;
    private TextView firstName, email;

    ListView lv;
    EditText nameEditText,descEditText, startEditText, finishEditText;
    Button saveBtn;
    CustomAdapter adapter,adapter2;
    CRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fdb = FirebaseDatabase.getInstance();
        ref = fdb.getReference("Users");
        fbUser = FirebaseAuth.getInstance().getCurrentUser();
        if (fbUser != null){

        }else{
            finish();
        }
        Log.d("Usario", fbUser.getUid());
        Log.d("Usario", "Eeeeeeeeeee");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d=new Dialog(MenuPrincipal.this);
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

                        Activity activity = new Activity(name,desc,start,finish);
                        mDatabase.child(fbUser.getUid()).child("actividades").child(name).setValue(activity);

                        if(crud.addNew(s))
                        {
                            nameEditText.setText("");
                            descEditText.setText("");
                            startEditText.setText("");
                            finishEditText.setText("");
                        }
                    }
                });
                d.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        PerfilFragment pf = PerfilFragment.newInstance("we are", "not using these");
        addFragment(pf);

        Intent intent = getIntent();
        actualUser = intent.getStringExtra("usuario");
        header = navigationView.getHeaderView(0);

        firstName = (TextView)header.findViewById(R.id.nameDrawer);
        email = (TextView)header.findViewById(R.id.textViewDrawer);

        ref.child(actualUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ususss usuarios = dataSnapshot.getValue(ususss.class);
                Log.d("Usuario", usuarios.getNombre());
                Usuario user = new Usuario();
                firstName.setText(usuarios.getNombre());
                email.setText(usuarios.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ActivityFragment f = ActivityFragment.newInstance();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction t = manager.beginTransaction();
        t.add(R.id.content_principal, f,"Visual");
        t.commit();
    }

    private void addFragment(Fragment fragment){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.content_principal, fragment, "theFragment");
        transaction.commit();
    }

    private void removeFragment(){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment f = fm.findFragmentByTag("theFragment");
        transaction.remove(f);
        transaction.commit();
    }

    private void swapFragment(Fragment fragment){
        removeFragment();
        addFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            PerfilFragment pf = PerfilFragment.newInstance("we are", "not using these");
            swapFragment(pf);
        } else if (id == R.id.nav_slideshow) {
            ProgressBarFragment pbf = ProgressBarFragment.newInstance("once again", "not used");
            swapFragment(pbf);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
