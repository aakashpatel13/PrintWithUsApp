package com.example.printwithus;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Home_Fragment extends Fragment implements View.OnClickListener {


    private static View view;
    //private  WebView myWebView;
    private static ImageView ivHome,ivContact,ivAbout,ivListProdcut;
    private static LinearLayout HomeLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;


    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        initViews();

        String myYouTubeVideoUrl ="https://www.youtube.com/embed/RW1HJdW5XLs";
        String dataUrl =
                "<html>" +
                        "<body>" +
                        "<iframe width=\"1000\" height=\"310\" src=\""+myYouTubeVideoUrl+"\" frameborder=\"0\" allowfullscreen/>" +
                        "</body>" +
                        "</html>";

        WebView myWebView = (WebView) view.findViewById(R.id.webview_y);
        WebSettings webSettings = myWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.loadData(dataUrl, "text/html", "utf-8");
        //sqliteHelper = new SqliteHelper(getContext());
        setListeners();
        return view;

    }
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        ivHome = (ImageView) view.findViewById(R.id.homeimg);
        ivAbout = (ImageView) view.findViewById(R.id.aboutusimg);
        ivContact = (ImageView) view.findViewById(R.id.contactimg);
        ivListProdcut=(ImageView) view.findViewById(R.id.listProduct);
         HomeLayout= (LinearLayout) view.findViewById(R.id.home_layout);
      /*  myWebView=(WebView)view.findViewById(R.id.webview);
        //setContentView(myWebView);
        myWebView.loadUrl("https://www.youtube.com/embed/RW1HJdW5XLs");
*/
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
    private void setListeners() {
        ivHome.setOnClickListener((View.OnClickListener) this);
        ivContact.setOnClickListener((View.OnClickListener) this);
        ivAbout.setOnClickListener((View.OnClickListener) this);
        ivListProdcut.setOnClickListener((View.OnClickListener)this);

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
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new Aboutus_Fragment(),
                                Utils.Aboutus_Fragment).commit();
                break;
        }

    }
}