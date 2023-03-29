
package com.example.codifyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.codifyy.MainFeaturesPackage.MainFeatures;
import com.example.codifyy.projectview.ProjectActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    TextView txt1,txt2;
    LottieAnimationView lottieAnimationView,loadingAnimation;
    CardView cardView1;
    TextView loadingtxt;

    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;// creating a firebase class to authenticate
    // [END declare_auth]
    private GoogleSignInClient mGoogleSignInClient; // client sign in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// ....................... Finding view by there Id's...............................................................
        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);
        lottieAnimationView = findViewById(R.id.lottieView);
        cardView1 = findViewById(R.id.introCardViewId);
        loadingtxt = findViewById(R.id.loadingtxtId);
        loadingAnimation = findViewById(R.id.loadingAnimationView);
//..............................End of View Block......................................................................
        mAuth = FirebaseAuth.getInstance(); // Instance of the firebase class



            loadGoogleSignInOption();
            loadIntroPage();


    }

    private void loadGoogleSignInOption() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("236395620195-1he663tg2iut27bg9ogu2hp8t79rl6pu.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void loadIntroPage() {

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.wipedown);
        txt1.setAnimation(animation);
        txt2.setAnimation(animation);


//   not in use this animation .........may be future reference
//        txt1.animate().translationY(-1400).setDuration(800).alpha(0).setStartDelay(3000);
//        txt2.animate().translationY(-1400).setDuration(800).alpha(0).setStartDelay(3000);
        lottieAnimationView.animate().translationY(1400).setDuration(400).alpha(0).setStartDelay(3000);
        cardView1.setAlpha(0f);

        cardView1.setTranslationX(800);

        cardView1.animate().translationX(0).alpha(1).setDuration(500).setStartDelay(3100).start();





//------------------------count downtimmer to move to next page after the particular duration
//        new CountDownTimer(3500, 1000) {
//            @Override
//            public void onTick(long l) {
//
//            }
//
//            @Override
//            public void onFinish() {
////                ActivityOptions options = ActivityOptions.makeCustomAnimation
////                        (IntroActivity.this,R.anim.fadein,R.anim.fadeout);
////                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
////                startActivity(intent,options.toBundle());
//                finish();
//
//
//            }
//        }.start();

    }


    public void goToDashBoard(View view) {
        if(!isNetworkConnected()){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("No Internet Connection")
                    .setMessage("Please Connect to the Internet")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(MainActivity.this,MainActivity.class));
                        }
                    }).show();
        }
        else {
            signIn();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                loadingAnimation.setAlpha(1f);
                loadingtxt.setAlpha(1f);
                cardView1.setVisibility(View.INVISIBLE);

                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                // Google Sign In failed, update UI appropriately
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {



                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            finish();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.this,MainFeatures.class);
                            startActivity(intent);
                        } else {
                            loadingAnimation.setAlpha(0f);
                            loadingtxt.setAlpha(0f);
                            cardView1.setVisibility(View.VISIBLE);
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Fail to Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null ){
            Thread timer = new Thread() {
                public void run(){
                    try {
                        sleep(2000);
                        finish();
                        Intent intent = new Intent(MainActivity.this,MainFeatures.class);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            timer.start();
        }
    }





    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}