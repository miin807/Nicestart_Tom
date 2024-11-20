package com.garciatomas.nicestart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class Main extends AppCompatActivity {

    private WebView myWebView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        WebView mycontext = findViewById(R.id.webView);
        registerForContextMenu(mycontext);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mySwipe);
        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        myWebView.loadUrl("https://thispersondoesnotexist.com");

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mySwipe);
        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);

    }

    // DIALOGO MODAL

    /**
     * Show alert dialog button clicked.
     *
     * @param mainActivity the main activity
     */
    public void showAlertDialogButtonClicked(Main mainActivity) {

        // setup the alert builder
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

//        //el dialogo estandar tiene título/icono pero podemos sustituirlo por un XML a medida
        builder.setTitle("Achtung!");
        builder.setMessage("Where do you go?");
        builder.setIcon(R.drawable.usericon);
        builder.setCancelable(false);

        // un XML a medida para el diálogo
//        builder.setView(getLayoutInflater().inflate(R.layout.alertdialog_view, null));

        // Positive
        builder.setPositiveButton("Log out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something like...
                Intent intent = new Intent(Main.this, Login.class);
                startActivity(intent);
                dialog.dismiss();

            }
        });

        // Negative
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something like...
                System.exit(0);

                dialog.dismiss();
            }
        });

        // Neutral
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something like...

                dialog.dismiss();
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // FUERA del Oncreate
    // construimos el Listener que lanza un Toast y desactiva a
    // continuación del Swipe la animación

    /**
     * The M on refresh listener.
     */
    protected SwipeRefreshLayout.OnRefreshListener
              mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {

        @Override
        public void onRefresh() {
//            Toast toast0 = Toast.makeText(Main.this, "Hi there! I don't exist :)", Toast.LENGTH_LONG);
//            toast0.show();

            final ConstraintLayout mLayout = findViewById(R.id.main);

            Snackbar snackbar = Snackbar
                    .make(mLayout, "Fancy a Snack while you refresh?", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Snackbar snackbar1 = Snackbar.make(mLayout, "Action is restored!", Snackbar.LENGTH_SHORT);
                                    snackbar1.show();
                                }
                            });

            snackbar.show();

            myWebView.reload();
            swipeRefreshLayout.setRefreshing(true);
            swipeRefreshLayout.setRefreshing(false);
        }
    };


    //implemento context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuinfo)
    {
        getMenuInflater().inflate(R.menu.menu_context,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if (id==R.id.item1) {
            Toast.makeText(this, "ITEM COPIADO", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if( id==R.id.item2)
        {
            Toast.makeText(this, "DOWLOAD ITEM", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_appbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();//numero segun orden de creacion en xml
        if(id==R.id.item1)
        {
            Toast.makeText(this, "Infecting", Toast.LENGTH_SHORT).show();
        }
        if(id==R.id.item2)
        {
            Toast.makeText(this, "Fixing", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, Profile.class);
            startActivity(intent);

        }
        if (id == R.id.item3) {
//            Intent intent = new Intent(Main.this, MainBab.class);
//            startActivity(intent);
        }

        if (id == R.id.item4) {
//            Intent intent = new Intent(this, MainBn.class);
//            startActivity(intent);
        }

        if (id == R.id.item5) {
            showAlertDialogButtonClicked(Main.this);
        }
        return super.onOptionsItemSelected(item);
    }
}