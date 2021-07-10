 package com.example.draw_nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

 public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
Button llogin,RRegister;
    private   DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maain);
llogin=findViewById(R.id.loglink);
RRegister=findViewById(R.id.reglink);
        MaterialToolbar mToolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);


        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.isDrawerOpen(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                int id=item.getItemId();

                switch (id) {
                    case R.id.home:
                        replacefrag(new Fragmenthome());
                        break;
                    case R.id.account:
                        replacefrag(new FragmentAccount());
                        break;
                    case R.id.Search:
                        replacefrag(new FragmentSearch());
                        break;
                    case R.id.share:
                        replacefrag(new FragmentShare());
                        break;
                    case R.id.contact:
                        replacefrag(new FragmentContact());
                        break;
                    case R.id.settings:
                        replacefrag(new Fragmentsettings());
                        break;
                    case R.id.feedback:
                        replacefrag(new FragmentFeedback());

                        break;

                }
                return true;


            }
        });
        llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefrag(new FragmentLogin());
            }
        });
RRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        replacefrag(new FragmentRegister());
    }
});
    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_main,menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                replacefrag( new FragmentLogin() );
                break;
            case R.id.register:
                replacefrag(new FragmentRegister());

                break;
            case  R.id.account:
                replacefrag(new FragmentAccount());

                break;
            case R.id.settings:
                break;
        }
         return super.onOptionsItemSelected(item);
     }

     private void replacefrag(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

     @Override
     protected void onPostResume() {
         super.onPostResume();
         int darkflag=getResources().getConfiguration().uiMode
                 & Configuration.UI_MODE_NIGHT_MASK;
         if(darkflag ==Configuration.UI_MODE_NIGHT_YES) {

         }
     }
 }
