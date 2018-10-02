package com.example.sbt.trapezoid2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final private String TAG = "TAG";
    DatabaseReference myRef;
    List<User> target = new ArrayList<>();
    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                ArrayList<User> friends = new ArrayList<User>();
//                User value = dataSnapshot.getValue( friends );
//                Log.d(TAG, "Value is: " + value.email+" : "+value.username);
                Iterable<DataSnapshot> source  = dataSnapshot.getChildren();
//                while (source)



                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
            User post = postSnapshot.getValue(User.class);
            target.add(post);
                    Log.i("TAG", post.email);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
//        Query recentPostsQuery = myRef;
//        Log.w(TAG, "COUNT: "+recentPostsQuery.toString());
//
//        myRef.child("id1").child("username").setValue("ivan");
//        myRef.child("id1").child("email").setValue("ivans mail");
//        myRef.child("id2").child("username").setValue("sergey");
//        myRef.child("id2").child("email").setValue("sergeys mail");
    }
}
