package com.garciatomas.nicestart;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Glide for loading person
        ImageView mPerson = findViewById(R.id.person);

        Glide.with(this)
//                .load(https://images.unsplash.com/photo-1527164561913-76d911aa17ff?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D)
                .load(R.drawable.person)
//                .transition(DrawableTransitionOptions.withCrossFade(2000))
//                .centerCrop()
//                .circleCrop()
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.Brunswick_green)))
                .into(mPerson);


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openSignup(View view) {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
    }

    public void openMain(View view) {
        Intent intent = new Intent(Login.this, Main.class);
        startActivity(intent);
    }
}