package com.example.bluechip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference parentRef = database.getReference();
    DatabaseReference loop;
    Button button1,button2;
    TextView text1,text2;
    String state="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        parentRef.child("bulb").child("state").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                state=String.valueOf(dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    parentRef.child("bulb").child("state").setValue(1);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.VISIBLE);



                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm MM-dd YY");
                String currentDateandTime = sdf.format(new Date());


                text1.setText("BULB ON at "+currentDateandTime);
                text1.setVisibility(View.VISIBLE);

                text2.setVisibility(View.INVISIBLE);

                loop=parentRef.child("bulb").child("timeStamp").push();

                loop.setValue("BULB ON at "+currentDateandTime);


//                parentRef.child("bulb").child("timeStamp").push();

            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                parentRef.child("bulb").child("state").setValue(0);
                button2.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.VISIBLE);



                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm MM-dd YY");
                String currentDateandTime = sdf.format(new Date());

                text2.setText("BULB OFF at "+currentDateandTime);
                text2.setVisibility(View.VISIBLE);

                text1.setVisibility(View.INVISIBLE);

                loop=parentRef.child("bulb").child("timeStamp").push();

                loop.setValue("BULB OFF at "+currentDateandTime);


//                parentRef.child("bulb").child("timeStamp").push();

            }
        });



    }
}
