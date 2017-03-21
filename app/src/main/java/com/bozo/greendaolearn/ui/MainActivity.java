package com.bozo.greendaolearn.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bozo.greendaolearn.R;
import com.bozo.greendaolearn.db.impl.MaterialImpl;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CompositeDisposable disposable;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_go_add).setOnClickListener(this);
        findViewById(R.id.btn_go_list).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        disposable = new CompositeDisposable();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_add:
                Intent intent = new Intent(this, AddMaterialActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_go_list:
                Intent intent1 = new Intent(this, MaterialListActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_clear:
                Observable<Boolean> clearObservable = new MaterialImpl().clearRecord();
                progressDialog.show();
                disposable.add(clearObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(@NonNull Boolean aBoolean) throws Exception {
                                progressDialog.dismiss();
                                if (aBoolean){
                                    Toast.makeText(MainActivity.this, "清空完成", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MainActivity.this, "清空失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }));
                break;
        }
    }
}
