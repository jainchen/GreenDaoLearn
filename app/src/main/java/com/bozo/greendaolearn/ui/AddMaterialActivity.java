package com.bozo.greendaolearn.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bozo.greendaolearn.R;
import com.bozo.greendaolearn.db.IMaterial;
import com.bozo.greendaolearn.db.impl.MaterialImpl;
import com.bozo.greendaolearn.entities.MaterialEntity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

public class AddMaterialActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etPrice, etNumber, etStore;

    private ProgressDialog progressDialog;

    private IMaterial material;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        material = new MaterialImpl();

        progressDialog = new ProgressDialog(this);

        etName = (EditText) findViewById(R.id.et_name);
        etPrice = (EditText) findViewById(R.id.et_price);
        etNumber = (EditText) findViewById(R.id.et_number);
        etStore = (EditText) findViewById(R.id.et_store);

        findViewById(R.id.btn_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = etName.getText().toString();
        double price = Double.parseDouble(etPrice.getText().toString());
        int number = Integer.parseInt(etNumber.getText().toString());
        String store = etStore.getText().toString();

        MaterialEntity materialEntity = new MaterialEntity(null, name, price, number, store);

        Observable<Long> observable = material.addRecord(materialEntity);

        progressDialog.show();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        progressDialog.dismiss();
                        Toast.makeText(AddMaterialActivity.this, "" + aLong.toString(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                }));
    }
}
