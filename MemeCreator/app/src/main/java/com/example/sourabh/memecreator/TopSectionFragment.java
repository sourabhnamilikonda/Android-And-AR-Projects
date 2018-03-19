package com.example.sourabh.memecreator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sourabh on 17-Feb-18.
 */

public class TopSectionFragment extends Fragment {
    private static EditText topText;
    private static EditText bottomText;

    TopSectionListener activityCommander;

    public interface TopSectionListener{
        public void createMeme(String top, String bottom);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try{
            activityCommander=(TopSectionListener) activity;

        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.top_section_fragment, container, false);
        topText=view.findViewById(R.id.etTopTextInput);
        bottomText=view.findViewById(R.id.etBottomTextInput);
        final Button btn=view.findViewById(R.id.btnCreateMeme);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                btnClicked(v);
            }
        });

        return view;
    }

    public void btnClicked(View v) {
        activityCommander.createMeme(topText.getText().toString(), bottomText.getText().toString());
    }

}
