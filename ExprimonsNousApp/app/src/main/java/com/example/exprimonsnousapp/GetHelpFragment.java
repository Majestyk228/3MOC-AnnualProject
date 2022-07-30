package com.example.exprimonsnousapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GetHelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetHelpFragment extends Fragment {

    // OBJECTS
    MaterialButton send_message_support;
    MaterialButton logoutBtn;

    EditText contactform_title;
    EditText contactform_body;

    // TOOLBAR
    Toolbar myToolbar;

    // SHARED PREFERENCE
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USER = "idUser";
    private static final String KEY_COMMUNITY = "idCommunity";

    public GetHelpFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GetHelpFragment newInstance() {
        GetHelpFragment fragment = new GetHelpFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_help, container, false);

        /*
        *
        * MaterialButton send_message_support;
            MaterialButton logoutBtn;

            EditText contactform_title;
            EditText contactform_body;
        *
        * */

        send_message_support = (MaterialButton)view.findViewById(R.id.send_message_support);
        logoutBtn = (MaterialButton)view.findViewById(R.id.logoutBtn);

        contactform_title = view.findViewById(R.id.contactform_title);
        contactform_body = view.findViewById(R.id.contactform_body);

        // LISTENING TO BUTTONS
        send_message_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Envoi du message vers le support...", Toast.LENGTH_LONG).show();
                contactform_body.setText("");
                contactform_title.setText("");
            }
        });



        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit().clear();
                editor.apply();
                //Toast.makeText(getContext(), "Déconnexion en cours...", Toast.LENGTH_LONG).show();

                ProgressDialog.show(getContext(), "",
                        "Déconnexion en cours... Veuillez patienter...", true);

                // FAKE DELAY
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // SEND USER TO LAUNCHING ACTIVITY
                        Intent nextActivity = new Intent(getActivity(), MainActivity.class);
                        startActivity(nextActivity);
                    }
                }, 1000);
            }
        });


        // RECUPERATION DE LA TOOLBAR DU PARENT, CHANGEMENT DU TITRE ET AJOUT DU BOUTON RETOUR
        myToolbar = getActivity().findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.get_help);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        // MISE EN ÉCOUTE DU BOUTON RETOUR
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // RETRAIT DU FRAGMENT CreatePostFragment
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack("GetHelpFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // DESABLING RETURN BUTTON OF TOOLBAR AND CHANGING TITLE
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        });

        return view;
    }
}