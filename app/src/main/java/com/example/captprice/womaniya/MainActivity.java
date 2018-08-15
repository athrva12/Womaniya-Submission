package com.example.captprice.womaniya;

import android.os.Bundle;
import android.support.annotation.NonNull;
//import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.example.captprice.womaniya.data.Child;
import com.example.captprice.womaniya.data.Data_;
import com.example.captprice.womaniya.data.RedditApi;
import com.example.captprice.womaniya.data.RedditResponse;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public String s="aww";
    private ActionBarDrawerToggle mtoggle;
    public DrawerLayout mdrawerLayout;
    private String TAG="MainActivity";
    private TextView mTextMessage;
    private RecyclerView recyclerView;
    private RedditAdapter redditAdapter;
    List<Child> childList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.card_recycler_view);
        setNavigationViewListner();
        mdrawerLayout=(DrawerLayout)findViewById(R.id.mdrawable);
        mtoggle=new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mdrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getBaseContext(),1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data(s);




//initRecyclerView();

    }
    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initRecyclerView() {
        redditAdapter = new RedditAdapter(childList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item))
        {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.aww: {
                s="aww";

                data(s);
                //do somthing
                break;

            }
            case R.id.cats:
                s="cats";
                datapics(s);
                break;
            case R.id.hmmm:
                s="hmmm";
                datasend(s);
                break;
            case R.id.images:
                s="images";
                datarecieve(s);
                break;

        }

        //close navigation drawer
        mdrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void datarecieve(String s){
        final Call<RedditResponse> jsonData = RedditApi.getRedditService().getPosted(s, "20");
        jsonData.enqueue(new Callback<RedditResponse>() {
            @Override
            public void onResponse(Call<RedditResponse> call, Response<RedditResponse> response) {
                childList = response.body().getData().getChildren();

                Log.d("", "onResponse: " + childList);

                String imageUrl = childList.get(0).getData().getUrl();
                // ImageView imageView=findViewById(R.id.mainimage);
                Log.d(TAG, "onResponse: the first image url"+imageUrl);

//                Glide.with(getBaseContext()).load(imageUrl).into(imageView);

                StringBuilder stringBuilder=new StringBuilder("");
                for (int i = 0; i <childList.size() ; i++) {
                    stringBuilder.append(i+".  "+childList.get(i).getData().getUrl()+"\n");
                }
//                TextView tv=findViewById(R.id.debugData);
//                tv.setText(stringBuilder);
                Log.d(TAG, "onResponse: list of imag url"+stringBuilder);

                redditAdapter=new RedditAdapter(childList);
                recyclerView.setAdapter(redditAdapter);
            }
            @Override
            public void onFailure(Call<RedditResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nahi huaa", Toast.LENGTH_SHORT).show();
                Log.d("main activity", "onFailure: "+t.getMessage());

            }
        });

    }

    public void datasend(String s)
    {
        final Call<RedditResponse> jsonData = RedditApi.getRedditService().getPosting(s, "20");
        jsonData.enqueue(new Callback<RedditResponse>() {
            @Override
            public void onResponse(Call<RedditResponse> call, Response<RedditResponse> response) {
                childList = response.body().getData().getChildren();

                Log.d("", "onResponse: " + childList);

                String imageUrl = childList.get(0).getData().getUrl();
                // ImageView imageView=findViewById(R.id.mainimage);
                Log.d(TAG, "onResponse: the first image url"+imageUrl);

//                Glide.with(getBaseContext()).load(imageUrl).into(imageView);

                StringBuilder stringBuilder=new StringBuilder("");
                for (int i = 0; i <childList.size() ; i++) {
                    stringBuilder.append(i+".  "+childList.get(i).getData().getUrl()+"\n");
                }
//                TextView tv=findViewById(R.id.debugData);
//                tv.setText(stringBuilder);
                Log.d(TAG, "onResponse: list of imag url"+stringBuilder);

                redditAdapter=new RedditAdapter(childList);
                recyclerView.setAdapter(redditAdapter);
            }
            @Override
            public void onFailure(Call<RedditResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nahi huaa", Toast.LENGTH_SHORT).show();
                Log.d("main activity", "onFailure: "+t.getMessage());

            }
        });

    }
    public void data(String s){
        final Call<RedditResponse> jsonData = RedditApi.getRedditService().getPostList(s, "20");
        jsonData.enqueue(new Callback<RedditResponse>() {
            @Override
            public void onResponse(Call<RedditResponse> call, Response<RedditResponse> response) {
                childList = response.body().getData().getChildren();

                Log.d("", "onResponse: " + childList);

                String imageUrl = childList.get(0).getData().getUrl();
                // ImageView imageView=findViewById(R.id.mainimage);
                Log.d(TAG, "onResponse: the first image url"+imageUrl);

//                Glide.with(getBaseContext()).load(imageUrl).into(imageView);

                StringBuilder stringBuilder=new StringBuilder("");
                for (int i = 0; i <childList.size() ; i++) {
                    stringBuilder.append(i+".  "+childList.get(i).getData().getUrl()+"\n");
                }
//                TextView tv=findViewById(R.id.debugData);
//                tv.setText(stringBuilder);
                Log.d(TAG, "onResponse: list of imag url"+stringBuilder);

                redditAdapter=new RedditAdapter(childList);
                recyclerView.setAdapter(redditAdapter);
            }
            @Override
            public void onFailure(Call<RedditResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nahi huaa", Toast.LENGTH_SHORT).show();
                Log.d("main activity", "onFailure: "+t.getMessage());

            }
        });


    }
    public void datapics(String s){
        final Call<RedditResponse> jsonData = RedditApi.getRedditService().getPost(s, "20");
        jsonData.enqueue(new Callback<RedditResponse>() {
            @Override
            public void onResponse(Call<RedditResponse> call, Response<RedditResponse> response) {
                childList = response.body().getData().getChildren();

                Log.d("", "onResponse: " + childList);

                String imageUrl = childList.get(0).getData().getUrl();
                // ImageView imageView=findViewById(R.id.mainimage);
                Log.d(TAG, "onResponse: the first image url"+imageUrl);

//                Glide.with(getBaseContext()).load(imageUrl).into(imageView);

                StringBuilder stringBuilder=new StringBuilder("");
                for (int i = 0; i <childList.size() ; i++) {
                    stringBuilder.append(i+".  "+childList.get(i).getData().getUrl()+"\n");
                }
//                TextView tv=findViewById(R.id.debugData);
//                tv.setText(stringBuilder);
                Log.d(TAG, "onResponse: list of imag url"+stringBuilder);

                redditAdapter=new RedditAdapter(childList);
                recyclerView.setAdapter(redditAdapter);
            }
            @Override
            public void onFailure(Call<RedditResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nahi huaa", Toast.LENGTH_SHORT).show();
                Log.d("main activity", "onFailure: "+t.getMessage());

            }
        });


    }
}

