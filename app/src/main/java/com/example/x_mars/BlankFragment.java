package com.example.x_mars;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class BlankFragment extends Fragment {
    RelativeLayout number1;
    Button pinNumbers[] = new Button[12];
    private String pin= "";
    public BlankFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridLayout gridLayout = getActivity().findViewById(R.id.pin_grid);

        for(int i=0;i<gridLayout.getChildCount();i++){
            String button = "pin_button_"+(i+1);
            final int resID = getActivity().getResources().getIdentifier(button,"id",getActivity().getPackageName());
            pinNumbers[i] = (Button) getActivity().findViewById(resID);
            makeRound(pinNumbers[i]);
            pinNumbers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String value=((Button)v).getText().toString();
                    boolean backspace = false;

                    try{
                        Integer.parseInt(value);
                    } catch (Exception e){
                        backspace=true;
                    }
                    if(backspace){

                        if(pin.length()<=4 && pin.length()!=0 ) {
                            int resourceID = getActivity().getResources().getIdentifier("pin_placeholder_" + pin.length(), "id", getActivity().getPackageName());
                            ImageView imageView = getActivity().findViewById(resourceID);
                            imageView.setBackground(getActivity().getDrawable(R.drawable.pin_empty_icon));
                            pin=pin.substring(0,pin.length()-1);
                        }

                    } else{
                        if(pin.length()<4) {
                            pin+=value;
                            int resourceID = getActivity().getResources().getIdentifier("pin_placeholder_" + pin.length(), "id", getActivity().getPackageName());
                            ImageView imageView = getActivity().findViewById(resourceID);
                            imageView.setBackground(getActivity().getDrawable(R.drawable.pin_full_icon));
                        }

                    }

                    if(pin.length()==4){
                        if(pin.equals("1245")){
                            Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }



    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void makeRound(final Button button){
        ViewTreeObserver vto = button.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                Log.e("TEST", "Height = " + layout.getHeight() + " Width = " + layout.getWidth());
                Integer margin_ver = (int)getActivity().getResources().getDimension(R.dimen.pin_button_margin);
                Integer margin_hor = (button.getWidth() - button.getHeight())/2 + margin_ver;
//                layout.setPadding(padding,0,padding,0);

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) button.getLayoutParams();
                layoutParams.setMargins(margin_hor,margin_ver,margin_hor,margin_ver);
                button.setLayoutParams(layoutParams);
//                button.setLayoutParams(new RelativeLayout.LayoutParams(button.getHeight(),button.getHeight()));

//                Log.e("TEST", "Height = " + layout.getHeight() + " Width = " + layout.getWidth());


                ViewTreeObserver obs = button.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
            }
        });

    }
}