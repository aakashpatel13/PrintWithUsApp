package com.example.printwithus;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


public class Aboutus_Fragment extends Fragment implements View.OnClickListener {
    private static View view;
    private static Animation shakeAnimation;
    private static TextView t1,t2,t3,t4,t5,t6;
    private static FragmentManager fragmentManager;
    private static LinearLayout AboutUsLayout;
    private static ImageView ivHome,ivContact,ivAbout;

    public Aboutus_Fragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =   inflater.inflate(R.layout.fragment_aboutus, container, false);
        initViews();
        // sqliteHelper = new SqliteHelper(getContext());
        setListeners();
        return view;
    }


    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        t1= (TextView) view.findViewById(R.id.txt1);
        t2 = (TextView) view.findViewById(R.id.txt2);
        t3 = (TextView) view.findViewById(R.id.txt3);
        t4 = (TextView) view.findViewById(R.id.txt4);
        t5 = (TextView) view.findViewById(R.id.txt5);
        t6 = (TextView) view.findViewById(R.id.txt6);
        ivHome = (ImageView) view.findViewById(R.id.homeimg);
        ivAbout = (ImageView) view.findViewById(R.id.aboutusimg);
        ivContact = (ImageView) view.findViewById(R.id.contactimg);

        AboutUsLayout = (LinearLayout) view.findViewById(R.id.aboutUs_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);



        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {

        ivHome.setOnClickListener((View.OnClickListener) this);
        ivContact.setOnClickListener((View.OnClickListener) this);
        ivAbout.setOnClickListener((View.OnClickListener) this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

