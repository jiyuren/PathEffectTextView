package com.alokvnair.patheffectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.alokvnair.patheffect.PathEffectTextView;

public class MainActivity extends AppCompatActivity {
    private PathEffectTextView mPathEffectTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPathEffectTextView = (PathEffectTextView) findViewById(R.id.path);
        mEditText = (EditText) findViewById(R.id.edit);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPathEffectTextView.init(mEditText.getText().toString());
            }
        });
    }


}
