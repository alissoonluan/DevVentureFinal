package com.example.venturuscatimages.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.venturuscatimages.model.Cat;
import com.example.venturuscatimages.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    private RetrofitConfig retrofitConfig = new RetrofitConfig();
    private MutableLiveData<Cat> catData = new MutableLiveData<>();

    public MutableLiveData<Cat> getCatData(){
        Call<Cat> cat = retrofitConfig.getFetchPhotosAPI().getCatImages("cats","jpg");
        cat.enqueue(new Callback<Cat>() {
            @Override
            public void onResponse(Call<Cat> call, Response<Cat> response) {
                if (response.body() != null) {
                    catData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Cat> call, Throwable t) {
                Log.e(TAG,"Error fetching data in API...\nError: "+t.getMessage());
            }
        });
        return catData;
    }

}
