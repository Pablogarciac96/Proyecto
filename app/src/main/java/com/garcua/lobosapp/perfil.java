package com.garcua.lobosapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class perfil extends Fragment {

    EditText mnombre, mapellido, mciudad;
    ListView mlv_datos;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        mnombre = (EditText) view.findViewById(R.id.et_nombre);
        mapellido = (EditText) view.findViewById(R.id.et_apellido);
        mciudad = (EditText) view.findViewById(R.id.et_dir);
        mlv_datos = (ListView) view.findViewById(R.id.lv_datos);
        initializes();


        return view;
    }

    private void initializes() {
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nombre = mnombre.getText().toString();
        String apellido = mapellido.getText().toString();
        String ciudad = mciudad.getText().toString();

        switch (item.getItemId()) {
            case R.id.icon_add: {
                if (nombre.equals("")|apellido.equals("") | ciudad.equals(""))  {
                    val();
                }
                else {
                    persona p= new persona();
                    p.setId(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(apellido);
                    p.setCiudad(ciudad);
                    databaseReference.child("persona").child(p.getId()).setValue(p);
                    Toast.makeText(getContext(), "Add", Toast.LENGTH_LONG).show();
                    clean();
                    break;
                }
            }
            case R.id.icon_save: {
                Toast.makeText(getContext(), "Save", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_delete: {
                Toast.makeText(getContext(), "Delete", Toast.LENGTH_LONG).show();
                break;
            }
            default:
                break;


        }

        return true;
    }

    private void clean() {
        mnombre.setText("");
        mapellido.setText("");
        mciudad.setText("");
    }

    private void val() {
        String nombre = mnombre.getText().toString();
        String apellido= mapellido.getText().toString();
        String ciudad=mciudad.getText().toString();

        if (nombre.equals("")) {
            mnombre.setError("Required");
        }
        else if (apellido.equals("")){
            mapellido.setError("required");
        }
        else if (ciudad.equals("")){
            mciudad.setError("required");
        }
    }
}