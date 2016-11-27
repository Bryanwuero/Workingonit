package com.moviles.workinonit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView lv;
    EditText nameEditText,descEditText, startEditText, finishEditText;
    Button saveBtn;
    //CustomAdapter adapter,adapter2;

    private FirebaseDatabase fdb;
    public DatabaseReference mDatabase;
    private DatabaseReference ref;
    public Usuario user;
    public FirebaseUser fbUser;

    public ActivityFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ActivityFragment newInstance() {
        ActivityFragment fragment = new ActivityFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity, container, false);
        lv= (ListView) v.findViewById(R.id.lv);


        fbUser = FirebaseAuth.getInstance().getCurrentUser();
        if (fbUser != null){

        }else{
        }

        fdb = FirebaseDatabase.getInstance();
        mDatabase = fdb.getReference("Users");
        ref = fdb.getReference("Users").child(fbUser.getUid()).child("actividades");
        ValueEventListener listener = new ValueEventListener() {
            ArrayList<Activity> activity;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                activity = new ArrayList<>();
                Activity actividad = new Activity();
                for (DataSnapshot usuarioSnap: dataSnapshot.getChildren()){
                    actividad = usuarioSnap.getValue(Activity.class);
                    activity.add(actividad);
                }

                CustomAdapter adapter = new CustomAdapter(getActivity(), activity);lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        };
        ref.addValueEventListener(listener);
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
