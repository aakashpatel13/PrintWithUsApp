package com.example.printwithus;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;

public class ContactusActivity extends Fragment implements OnClickListener {
    private static View view;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    private static EditText firstname, lastname,email,query;
    private static Button submit,reset;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout ContactUSLayout;
    private static ImageView ivHome,ivContact,ivAbout;

    public ContactusActivity() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_contactus, container, false);
        initViews();
       // sqliteHelper = new SqliteHelper(getContext());
        setListeners();
        return view;
    }


    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

         firstname= (EditText) view.findViewById(R.id.FirstName);
        lastname = (EditText) view.findViewById(R.id.LastName);
        email = (EditText) view.findViewById(R.id.Email);
        query = (EditText) view.findViewById(R.id.Query);
        submit = (Button) view.findViewById(R.id.submitBtn);
        reset = (Button) view.findViewById(R.id.resetBtn);
        ivHome = (ImageView) view.findViewById(R.id.homeimg);
        ivAbout = (ImageView) view.findViewById(R.id.aboutusimg);
        ivContact = (ImageView) view.findViewById(R.id.contactimg);

        ContactUSLayout = (LinearLayout) view.findViewById(R.id.ContactUs_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);


            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        reset.setOnClickListener(this);
        submit.setOnClickListener(this);
        ivHome.setOnClickListener((View.OnClickListener) this);
        ivContact.setOnClickListener((View.OnClickListener) this);
        ivAbout.setOnClickListener((View.OnClickListener) this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.submitBtn:
               Snackbar.make(submit, "We will contact you by your email in 24 hours", Snackbar.LENGTH_LONG).show();
               break;
            case R.id.resetBtn:
                firstname.setText("");
                lastname.setText("");
                email.setText("");
                query.setText("");
                break;
            case R.id.homeimg:
                //checkValidation();
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new Home_Fragment(),
                                Utils.Home_Fragment).commit();
                break;

            case R.id.listProduct:
                //checkValidation();

              /*  fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new Product_Fragment(),
                                Utils.Product_Fragment).commit();*/
                Intent intent = new Intent(getActivity(), SplashScreenActivity.class);
                startActivity(intent);
                break;

            case R.id.contactimg:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ContactusActivity(),
                                Utils.contact_Fragment).commit();
                break;
            case R.id.aboutusimg:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new Aboutus_Fragment(),
                                Utils.Aboutus_Fragment).commit();
                break;


        }

    }

}