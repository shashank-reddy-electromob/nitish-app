package com.nitish.paymatrixandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PayRentActivity extends AppCompatActivity {

    private ImageView image;
    private TextView text;
    private Button addproperty;
    //private ListView listView;

    private TextView notify_user;
    private ImageView navigation;

    private TextView p_name;
    private TextView p_address;
    private TextView p_pincode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_rent);

        image = findViewById(R.id.imageView);
        addproperty = findViewById(R.id.button);
        navigation = findViewById(R.id.navigation1);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),settings.class));
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayRentActivity.this,PayRentArchiveActivity.class));
            }
        });

        addproperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayRentActivity.this, AddPropertyActivity.class));
            }
        });

//        listView= findViewById(R.id.listView);
//
//        final ArrayList<Property> list= new ArrayList<>();
//        final ArrayAdapter<Property> adapter= new ArrayAdapter(this ,R.layout.list_item, list);
//        listView.setAdapter(adapter);

        notify_user=findViewById(R.id.notify);

        p_name=findViewById(R.id.property_name);
        p_address=findViewById(R.id.property_address);
        p_pincode=findViewById(R.id.property_pincode);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("ADD PROPERTY")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String property_name=document.get("property name").toString();
                                String property_address=document.get("property address").toString();
                                String property_pincode=document.get("pincode").toString();
                                if(!property_name.equals(""))
                                    notify_user.setVisibility(View.INVISIBLE);
                                p_name.setText(property_name);
                                p_address.setText(property_address);
                                p_pincode.setText(property_pincode);
                                //list.add(new Property(property_name,property_pincode,property_address));
                            }
                            //adapter.notifyDataSetChanged();
                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void openArchiveList(View v){
        Intent i=new Intent(this,PayRentArchiveActivity.class);
        startActivity(i);
    }
    public void goBack(View v){
        finish();
    }
}
