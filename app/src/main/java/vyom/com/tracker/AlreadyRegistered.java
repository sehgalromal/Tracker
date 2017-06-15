package vyom.com.tracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public  class AlreadyRegistered extends AppCompatActivity {
    private static final String TAG = "AlreadyRegistered";
    private static final int RC_SIGN_IN = 9001 ;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private com.google.android.gms.common.SignInButton signInButton;
    private GoogleApiClient mGoogleApiClient;
    private EditText mphonenumber,memailaddress;
    private Button mLoginButton;
    private ProgressDialog mProgressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_registered);
        mAuth=FirebaseAuth.getInstance();

        mphonenumber=(EditText) findViewById(R.id.edi_already_phone);
        memailaddress=(EditText)findViewById(R.id.edi_email_address);
        mLoginButton=(Button)findViewById(R.id.bt_already_sign_In);


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=memailaddress.getText().toString();
                String phonenumber=mphonenumber.getText().toString();

                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(phonenumber)){

                    mProgressdialog.setTitle("Logging in");
                    mProgressdialog.setMessage("Please wait while we check your credentials..");
                    mProgressdialog.setCanceledOnTouchOutside(false);
                    mProgressdialog.show();

                    loginUser(email,phonenumber);



                }


            }
        });



    }

    private void loginUser(String email, String phonenumber) {

        mAuth.signInWithEmailAndPassword(email,phonenumber).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    mProgressdialog.dismiss();
                    Intent mainintent=new Intent(AlreadyRegistered.this,Profile.class);
                    startActivity(mainintent);
                    finish();


                }else
                {
                    mProgressdialog.hide();
                    Toast.makeText(AlreadyRegistered.this,"Cannot Sign in.Please check the credentials and try again",Toast.LENGTH_LONG).show();
                }
            }
        });
        }


    public void onStart(){
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser == null){

        }


    }

}