package com.example.eddy.servr.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eddy.servr.R;
import com.example.eddy.servr.ServerConnection;

import org.w3c.dom.Text;

import java.util.ArrayList;

/** November 27th, 2017
 *  Eddy Yao
 *
 *      Displays currently logged in users information, including username, email, phone, etc.. This
 *  fragment is part of the swipe layout used under the main activity, and can thus be accessed at
 *  any point by swiping between this and the stream fragment.
 */

public class ProfileFragment extends Fragment{

    // Base UI element upon which everything is built / rooted
    private View rootView;

    // Constructor - Doesn't do anything except instance the object
    public ProfileFragment(){}

    // Sets base layout element using given parameters and UI elements
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        return rootView;
    }

    // Updates user information whenever this fragment is refreshed
    @Override
    public void onResume(){
        super.onResume();
        TextView username = rootView.findViewById(R.id.username);
        TextView email = rootView.findViewById(R.id.email);
        TextView phone = rootView.findViewById(R.id.phone);
        TextView location = rootView.findViewById(R.id.location);

        username.setText(ServerConnection.user.get(1));
        email.setText(ServerConnection.user.get(3));
        phone.setText(ServerConnection.user.get(4));
        location.setText(String.format("%s, %s", ServerConnection.user.get(5), ServerConnection.user.get(6)));
    }
}
