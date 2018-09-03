package com.example.felix.dailybucketlist.TabbingTutorial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felix.dailybucketlist.AlarmManager.AlarmActivity;
import com.example.felix.dailybucketlist.Config;
import com.example.felix.dailybucketlist.Main.BucketListActivity;
import com.example.felix.dailybucketlist.MainActivity;
import com.example.felix.dailybucketlist.R;

public class FragmentPage extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view;
        view = inflater.inflate(R.layout.fragment_page, container, false);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt("pageNumber");
        String imagePath = bundle.getString("imagePath");

        ImageView image = (ImageView)getView().findViewById(R.id.imageView);
        TextView text = (TextView)getView().findViewById(R.id.textView_tut);
        int id = getResources().getIdentifier(imagePath, "mipmap", getContext().getPackageName());
        image.setImageResource(id);
        text.setText(bundle.getString("tutText"));

        if(pageNumber == 3){
            Button button = getView().findViewById(R.id.btn_getStarted);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AlarmActivity.class);
                    view.getContext().startActivity(intent);}
            });

        }
    }

}
