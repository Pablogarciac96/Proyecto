package com.garcua.lobosapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class login extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText correo;
    private EditText contraseña;
    private Button entrar;

    private String email = "";
    private String password = "";
    private FirebaseAuth mAuth;

    public login() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();

        correo = (EditText) view.findViewById(R.id.editText_email);
        contraseña = (EditText) view.findViewById(R.id.editText_contraseña);
        entrar = (Button) view.findViewById(R.id.btn_entar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = correo.getText().toString();
                password = contraseña.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    loginUser(view);
                }
                else{
                        Toast.makeText(getContext(), "complete los campos", Toast.LENGTH_SHORT).show();

                    }
                }


        });
        return view;

    }

    private void loginUser(View view) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Navigation.findNavController(view).navigate(R.id.perfil);

                } else {
                    Toast.makeText(getContext(), "Error en inicio de sesion", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    //no volver a pedir login

    public void onStart(View view) {
        super.onStart();

        if (mAuth.getCurrentUser() != null) ;
        {

            Navigation.findNavController(view).navigate(R.id.perfil);

        }
    }

    protected class OnFragmentInteractionListener {
    }


}
