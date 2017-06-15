package vyom.com.tracker;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static vyom.com.tracker.R.id.imageButton;
import static vyom.com.tracker.R.id.start;

public class Profile extends AppCompatActivity {

    private static final String TAG = "AlreadyRegistered";
    private FirebaseAuth mAuth;
    private Toolbar mtoolber;
    private GoogleApiClient mGoogleApiClient;
    private TextView user_name, ph_name, email_add, sos_1;

    ImageButton about;
    ImageButton home;
    ImageButton maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        mtoolber = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(mtoolber);
        getSupportActionBar().setTitle("Tracker");

        about = (ImageButton) findViewById(R.id.btn_contact);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loader = new Intent(Profile.this, AboutUs.class);
                startActivity(loader);
            }
        });

        home = (ImageButton) findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent load = new Intent(Profile.this, MenuBar.class);
                startActivity(load);

            }
        });

        maps = (ImageButton) findViewById(R.id.btn_maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loding = new Intent(Profile.this, MapsActivity.class);
                startActivity(loding);
            }
        });


    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            sendToStart();
        }
    }

    private void sendToStart() {

        Intent start = new Intent(Profile.this, AlreadyRegistered.class);
        startActivity(start);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.action_logout){
            FirebaseAuth.getInstance().signOut();
            
        }

        return true;
    }



    }

