package com.example.sourabh.memecreator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by sourabh on 17-Feb-18.
 */

public class BottomSectionFragment extends Fragment {
    private static TextView tt;
    private static TextView bt;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.bottom_picture_fragment, container, false);
        tt=view.findViewById(R.id.tvTopText);
        bt=view.findViewById(R.id.tvBottomText);

        return view;
    }

    public void setMemeText(String topText, String bottomText){
        tt.setText(topText);
        bt.setText(bottomText);
    }
}
