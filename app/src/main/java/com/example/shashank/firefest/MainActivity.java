package com.example.shashank.firefest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private Firebase mref;
    private String url="";
    TextView cen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(getContext());
        cen = (TextView) findViewById(R.id.cen);
        mref = new Firebase(url);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //Getting the data from snapshot
                    Log.v("warning","hello4");
                    Person person = postSnapshot.getValue(Person.class);
                    //Adding it to a string
                    String string = "News:1-: " + person.getName() +  "\n\n";


                    //Displaying it on textview
                    if(person.getName()!=null)
                        tv.setText(string);

                    if((person.getName()==null) )
                        cen.setText("No news ");

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

                Toast.makeText(getContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                System.out.println("The read failed: " + firebaseError.getMessage());
            }


        });

    }



}
