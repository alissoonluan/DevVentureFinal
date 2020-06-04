package com.example.venturuscatimages.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.venturuscatimages.R;
import com.example.venturuscatimages.adapter.CatAdapter;
import com.example.venturuscatimages.databinding.ActivityMainBinding;
import com.example.venturuscatimages.model.Cat;
import com.example.venturuscatimages.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private MainActivityViewModel viewModel;
    private CatAdapter catAdapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        list = new ArrayList<>();
        catAdapter = new CatAdapter(list);
        mainBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mainBinding.recyclerView.setHasFixedSize(true);
        mainBinding.recyclerView.setAdapter(catAdapter);

        mainBinding.buttonSync.setOnClickListener(v -> updateImages());

        updateImages();

        setContentView(mainBinding.getRoot());
    }

        private void updateImages() {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {

                if (mainBinding.buttonSync.getVisibility() == View.VISIBLE) {
                    mainBinding.buttonSync.setVisibility(View.GONE);
                }

                viewModel.getCatData().observe(this, cat -> {
                    if (cat != null) {
                        for (Cat.Data catData : cat.getData()) {
                            for (Cat.Images catImages : catData.getImages()) {
                                list.add(catImages.getLink());
                            }
                        }
                    }
                    mainBinding.progressBarLoading.setVisibility(View.GONE);
                    catAdapter.notifyDataSetChanged();
                });

            } else {
                mainBinding.progressBarLoading.setVisibility(View.GONE);
                mainBinding.buttonSync.setVisibility(View.VISIBLE);
                Toast.makeText(this, "No internet connection.", Toast.LENGTH_SHORT).show();
            }
        }
}