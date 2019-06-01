package com.aiandroid.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.rain.aiandroid.R;
import com.just.agentweb.AgentWeb;

public class ContentActivity extends AppCompatActivity {
private LinearLayout linearLayout;
private AgentWeb mAgentWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_content);
        linearLayout=(LinearLayout)findViewById(R.id.line);
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        initWebView(url);
    }

    private void initWebView(String url) {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }
}
