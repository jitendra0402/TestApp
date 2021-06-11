package com.net.testapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;
import com.net.testapp.R;
import com.net.testapp.data.db.entity.UserEntity;
import com.net.testapp.ui.home.HomeActivity;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = MainActivity.class.getName();

    String idToken = "";
    GoogleSignInClient mGoogleSignInClient;

    SignInButton signInButton;

    TextInputEditText email, fname, password;
    MaterialRadioButton radioButtonMale, radioButtonFemale;
    MaterialButton signup;
    MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preWork();
        getIDs();
        setEvents();
        postWork();

    }

    public void preWork() {
    }

    public void getIDs() {
        signInButton = findViewById(R.id.BtnSignIn);

        email = findViewById(R.id.EditEmail);
        fname = findViewById(R.id.EditFname);
        password = findViewById(R.id.EditPassword);

        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);

        signup = findViewById(R.id.btnSignup);
    }

    public void setEvents() {
        signInButton.setOnClickListener(v -> {
            startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
        });

        radioButtonMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    radioButtonFemale.setChecked(false);
                }
            }
        });

        radioButtonFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    radioButtonMale.setChecked(false);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gender = 0;
                if (radioButtonMale.isChecked())
                    gender = 0;
                else
                    gender = 1;

                if (email.getText().toString().equalsIgnoreCase(""))
                    email.setError("Plz Enter EmailID");
                else if (fname.getText().toString().equalsIgnoreCase(""))
                    fname.setError("Plz Enter FullName");
                else if (password.getText().toString().equalsIgnoreCase(""))
                    password.setError("Plz Enter Password");
                else {
                    UserEntity entity = new UserEntity(email.getText().toString(), fname.getText().toString(), gender, password.getText().toString());
                    mainViewModel.insertUser(entity);
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        });
    }

    public void postWork() {

        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        radioButtonMale.setChecked(true);
        this.mGoogleSignInClient = GoogleSignIn.getClient((Activity) this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build());
    }


    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == RC_SIGN_IN) {
            try {
                GoogleSignInAccount result = GoogleSignIn.getSignedInAccountFromIntent(intent).getResult(ApiException.class);
                this.idToken = result.getIdToken();
                email.setText(result.getEmail());
                fname.setText(result.getDisplayName());
            } catch (ApiException e) {
                Log.e(TAG, "Google sign in failed", e);
            }
        }
    }
}