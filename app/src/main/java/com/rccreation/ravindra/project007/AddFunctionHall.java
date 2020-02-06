package com.rccreation.ravindra.project007;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rccreation.ravindra.project007.ui.home.javafunctionslist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddFunctionHall extends AppCompatActivity {

    Button SubmitButton;

    ProgressDialog progressDialog;

    View view1;

    private DatabaseReference mDatabaseUser;

    Button DisplayButton;

    ImageButton imageButton;

    EditText NameEditText, PhoneNumberEditText, contacttext, stipend1;

    public static final String Firebase_Server_URL = "https://project24-d91d5.firebaseio.com/";
    Firebase firebase;
    private static final int CAMERA_REQUEST = 1;
    Uri mImageUri = null;
    String downloadurl;
    private static final int GALLERY_REQUEST = 1;
    private StorageReference mStorageref;


    DatabaseReference databaseReference;


    public static final String Database_Path = "functions";
    StorageReference mStorageRef,storage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadfunctions);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        imageButton = (ImageButton) findViewById(R.id.imageSelect);

        Firebase.setAndroidContext(AddFunctionHall.this);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        firebase = new Firebase(Firebase_Server_URL);

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);




        SubmitButton = (Button) findViewById(R.id.submit);

        NameEditText = (EditText) findViewById(R.id.name);

        PhoneNumberEditText = (EditText) findViewById(R.id.phone_number);

        contacttext = (EditText) findViewById(R.id.contacttext);

        stipend1=(EditText) findViewById(R.id.hallcontact);

        DisplayButton = (Button) findViewById(R.id.DisplayButton);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_REQUEST);

            }
        });


        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog = new ProgressDialog(AddFunctionHall.this);

                progressDialog.setMessage("Loading  please wait");

                progressDialog.show();






                javafunctionslist javafunctionslist = new javafunctionslist();

               // javafunctionslist.setImage(downloadurl);
                // Adding name into class function object.
                javafunctionslist.setName(NameEditText.getText().toString().trim());

                // Adding phone number into class function object.
                javafunctionslist.setAddress(PhoneNumberEditText.getText().toString().trim());

                javafunctionslist.setContact(contacttext.getText().toString().trim());

                javafunctionslist.setDescription(stipend1.getText().toString().trim());

                // Getting the ID from firebase database.
                String StudentRecordIDFromServer = databaseReference.push().getKey();


                databaseReference.child(StudentRecordIDFromServer).setValue(javafunctionslist);

                progressDialog.dismiss();
                // Showing Toast message after successfully data submit.
                Toast.makeText(AddFunctionHall.this, " Added successfully", Toast.LENGTH_LONG).show();






            }
        });




        DisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(AddFunctionHall.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }


}
