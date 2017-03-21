package com.bozo.greendaolearn.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bozo.greendaolearn.R;
import com.bozo.greendaolearn.adapter.MaterialAdapter;
import com.bozo.greendaolearn.db.IMaterial;
import com.bozo.greendaolearn.db.impl.MaterialImpl;
import com.bozo.greendaolearn.entities.MaterialEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MaterialListActivity extends AppCompatActivity {

    RecyclerView rlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_list);

        rlv = (RecyclerView) findViewById(R.id.rlv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MaterialListActivity.this);
        rlv.setLayoutManager(linearLayoutManager);

        Observable<List<MaterialEntity>> observable = new MaterialImpl().getMaterials();
        new CompositeDisposable().add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MaterialEntity>>() {
                    @Override
                    public void accept(@NonNull final List<MaterialEntity> materialEntities) throws Exception {
                        MaterialAdapter adapter = new MaterialAdapter(materialEntities);
                        rlv.setAdapter(adapter);
                    }
                }));
    }
}