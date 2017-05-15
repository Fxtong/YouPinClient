package com.online.youpinclient.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.online.youpinclient.R;
import com.online.youpinclient.bean.RegisterBean;
import com.online.youpinclient.databinding.ActivityRegisterBinding;
import com.online.youpinclient.network.RequestClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding registerBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding=DataBindingUtil.setContentView(this,R.layout.activity_register);
        initView();
    }

    private void initView() {
        setSupportActionBar(registerBinding.registerToolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);

            //返回
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_undo);
        }

        registerBinding.confirmButton.setIndeterminateProgressMode(true);
        registerBinding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularProgressButton progressButton= (CircularProgressButton) v;
                int p=progressButton.getProgress();

                if(p==0){
                    progressButton.setProgress(50);
                    initData();
                }
                else if(p==100){
                    //注册成功跳转
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.appearance,R.anim.stealth);
                    finish();
                }
                else if(p==-1){
                    progressButton.setProgress(50);
                    initData();
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    public void initData() {
        String phone=registerBinding.etPhone.getText().toString().trim();
        String paw=registerBinding.etPaw.getText().toString().trim();

        if (!phone.equals("")&&!paw.equals("")) {
            RequestClient.getInstance().postRegister(phone, paw)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RegisterBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(RegisterBean value) {

                            if (value.getCade()==0) {
                                registerBinding.confirmButton.setProgress(100);
                            } else {
                                registerBinding.confirmButton.setProgress(-1);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            registerBinding.confirmButton.setProgress(-1);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }else {
            Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
            registerBinding.confirmButton.setProgress(0);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.appearance,R.anim.stealth);
    }
}
