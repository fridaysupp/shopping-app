package com.example.fridaye_com;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import static com.example.fridaye_com.RegisterActivity.onResetPasswordFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {



    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;
public static boolean disableCloseBtn = false;
    private EditText email;
    private EditText password;
    private ImageButton closeBtn;
    private Button signInBtn;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private ProgressBar progressBar;
    private TextView forgotPassword;
    private FirebaseAuth firebaseAuth;



    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        dontHaveAnAccount = view.findViewById(R.id.tv_dont_have_account);

        parentFrameLayout = getActivity().findViewById(R.id.register_framelayout);

        email =view.findViewById(R.id.sign_in_email);
        password = view.findViewById(R.id.sign_in_password);

        closeBtn = view.findViewById(R.id.sign_in_close_btn);
        signInBtn = view.findViewById(R.id.sign_in_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        forgotPassword= view.findViewById(R.id.sign_in_forgot_password);
        progressBar = view.findViewById(R.id.sign_in_progressbar);

        if (disableCloseBtn){
            closeBtn.setVisibility( View.GONE );
        }else{
            closeBtn.setVisibility( View.VISIBLE );
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dontHaveAnAccount.setOnClickListener( v -> setFragment(new SignupFragment()) );

        forgotPassword.setOnClickListener( v -> {
            onResetPasswordFragment = true ;
            setFragment(new resetPassword());
        } );

        closeBtn.setOnClickListener( v -> mainIntent() );

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signInBtn.setOnClickListener( v -> checkEmailAndPassword() );
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(password.getText())) {
                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(255, 255, 255));
            } else {
                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.argb(50, 255, 255, 255));
            }
        } else {
            signInBtn.setEnabled(false);
            signInBtn.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
    private  void mainIntent(){
        if (disableCloseBtn){
            disableCloseBtn = false;

        }else {
           Intent signInIntent = new Intent( getActivity(), MainActivity.class );
           startActivity( signInIntent );
           getActivity().finish();
        }
        //getActivity().finish();
    }
    private void checkEmailAndPassword() {
        if(email.getText().toString().matches( emailPattern )){
            if (password.length() >= 8){

                progressBar.setVisibility( View.VISIBLE );
                signInBtn.setEnabled( false );
                signInBtn.setTextColor(Color.argb(50, 255, 255, 255));

                firebaseAuth.signInWithEmailAndPassword( email.getText().toString(),password.getText().toString() )
                            .addOnCompleteListener( task -> {
                                    if (task.isSuccessful()){
                                        mainIntent();
                                       // Intent mainIntent = new Intent(getActivity(),MainActivity.class);
                                        //startActivity( mainIntent );
                                        //getActivity().finish();
                                    }else{
                                        progressBar.setVisibility( View.INVISIBLE );
                                        signInBtn.setEnabled( true );
                                        signInBtn.setTextColor(Color.rgb( 255, 255, 255));
                                        String error=task.getException().getMessage();
                                        Toast.makeText( getActivity(),error,Toast.LENGTH_SHORT ).show();
                                    }

                            } );
            }else{
                Toast.makeText( getActivity(),"Incorrect email or password",Toast.LENGTH_SHORT ).show();
            }

        }else {
            Toast.makeText( getActivity(),"Incorrect email or password",Toast.LENGTH_SHORT ).show();
        }
    }

}