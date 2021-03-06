package com.example.felix.dailybucketlist.TabbingTutorial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felix.dailybucketlist.AlarmManager.AlarmActivity;
import com.example.felix.dailybucketlist.Config;
import com.example.felix.dailybucketlist.R;

// Fragment für das Tutorial.
// Zeigt jeweiliges Bild und Text an.
public class FragmentPage extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // Holt Seitennummer.
        Bundle bundle = getArguments();
        int pageNumber = bundle.getInt(Config.TUTORIAL_INTENT_PAGE_KEY);
        String imagePath = bundle.getString(Config.TUTORIAL_INTENT_PATH_KEY);

        // Setzt Tutorial Bild und Text.
        ImageView image = getView().findViewById(R.id.imageView);
        TextView text = getView().findViewById(R.id.textView_tut);
        int id = getResources().getIdentifier(imagePath, "mipmap", getContext().getPackageName());
        image.setImageResource(id);
        text.setText(bundle.getString(Config.TUTORIAL_INTENT_TEXT_KEY));

        // Bei der letzten Tutorial Seite -> Zeigt Button.
        if(pageNumber == 5){
            Button button = getView().findViewById(R.id.btn_getStarted);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveTutorialPassed(view);
                    Intent intent = new Intent(view.getContext(), AlarmActivity.class);
                    //Mit startActivityForResult wird der AlarmActivity beim Aufruf "getCallingActivity()" die Eltern Activity übergeben
                    startActivityForResult(intent, 0);
                }
            });

        }
    }

    private void saveTutorialPassed(View view) {
        SharedPreferences settings = view.getContext().getSharedPreferences(Config.TUTORIAL_PREFERENCES, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Config.TUTORIAL_PREFERENCES_KEY, Config.TUTORIAL_PASSED);
        editor.apply();

    }


}
