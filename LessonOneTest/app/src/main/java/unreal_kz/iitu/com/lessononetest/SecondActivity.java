package unreal_kz.iitu.com.lessononetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Unreal_KZ on 01.10.2015.
 */
public class SecondActivity extends AppCompatActivity{

    private static final int LAYOUT = R.layout.second_main;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        getIntentInfo();
    }

    private void getIntentInfo() {
        Intent my_intent = getIntent();
        //String myStr = my_intent.getStringExtra("MainActivity");
        String myStr = my_intent.getExtras().getString("MainActivity");

        textView = (TextView) findViewById(R.id.secTextView);
        textView.setText("This is info from Main Activity:" + myStr);
    }
}
