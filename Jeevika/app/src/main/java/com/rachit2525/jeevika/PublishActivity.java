package com.rachit2525.jeevika;

        import android.Manifest;
        import android.app.PendingIntent;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.os.Build;
        import android.os.Bundle;
        import android.telecom.Call;
        import android.telephony.SmsManager;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.firestore.DocumentSnapshot;
        import com.google.firebase.firestore.EventListener;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.FirebaseFirestoreException;
        import com.google.firebase.firestore.QuerySnapshot;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class PublishActivity extends AppCompatActivity {

    EditText jobDetailEditText;
    EditText pPhoneEditText;
    EditText pNameEditText;
    Spinner jobAppSpinner;
    Button publishButton;

    String pphNumber;
    String jobDetails;
    String pName;
    String jobTitle;

    FirebaseFirestore db;
    private List<String> numberList = new ArrayList<>();
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        jobAppSpinner = findViewById(R.id.jobAppSpinner);

        jobDetailEditText = findViewById(R.id.jobDetailEditText);
        pPhoneEditText = findViewById(R.id.pPhoneEditText);
        pNameEditText = findViewById(R.id.pNameEditText);
        publishButton = findViewById(R.id.publishButton);


        ArrayAdapter<CharSequence> jobAdapter =
                ArrayAdapter.createFromResource(this, R.array.jobs, android.R.layout.simple_spinner_item);
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobAppSpinner.setAdapter(jobAdapter);
        jobAppSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jobTitle = adapterView.getItemAtPosition(i).toString();
                System.out.println(jobTitle+"**************************************************************************************");
//                Toast.makeText(adapterView.getContext(), jobSelected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                jobTitle = "General Helper";
            }
        });




        db = FirebaseFirestore.getInstance();



        System.out.println(jobTitle+"********1******************************************************************************");



        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){

                    }else{
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS},1);
                    }
                }
                jobDetails = jobDetailEditText.getText().toString();
                pphNumber = pPhoneEditText.getText().toString();
                pName = pNameEditText.getText().toString();

                String msg = "Job Category: " + jobTitle +"\n" + "Job Details: " + jobDetails + "\n" +
                        "Name: " + pName + "\n" + "Contact Us:" + pphNumber ;

                //Toast.makeText(PublishActivity.this, msg, Toast.LENGTH_SHORT).show();

                db.collection(jobTitle).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        String det = "Hello \n";
                        for(DocumentSnapshot snapshot : value){

                            String nos = snapshot.getString("phone");

                            try{
                                SmsManager smgr = SmsManager.getDefault();
                                smgr.sendTextMessage(nos,null,msg,null,null);
                                Toast.makeText(getApplicationContext(), "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception e){
                                Toast.makeText(getApplicationContext(), "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                            }
                            //numberList.add(snapshot.getString("phone"));
                            det = det + snapshot.getString("phone") + "\n";
                        }

                        Toast.makeText(PublishActivity.this, det, Toast.LENGTH_LONG).show();
                    }
                });

                Intent intent = new Intent(PublishActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


}
