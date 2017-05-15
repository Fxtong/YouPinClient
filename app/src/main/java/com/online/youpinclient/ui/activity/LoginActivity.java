package com.online.youpinclient.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.online.youpinclient.MainActivity;
import com.online.youpinclient.R;
import com.online.youpinclient.bean.LoginBean;
import com.online.youpinclient.databinding.ActivityLoginBinding;
import com.online.youpinclient.network.RequestClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        initView();
    }
    private void initView() {
        setSupportActionBar(loginBinding.loginToolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        ActionBar actionBar=getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }

        loginBinding.loginReTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.appearance,R.anim.stealth);
            }
        });

        loginBinding.loginConfirmButton.setIndeterminateProgressMode(true);
        loginBinding.loginConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularProgressButton progressButton= (CircularProgressButton) v;
                int p=progressButton.getProgress();

                if(p==0){
                    progressButton.setProgress(50);
                    initData();
                }
                else if(p==100){
                    //登录成功跳转
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
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


    public void initData() {
        String phone=loginBinding.loginEtPhone.getText().toString().trim();
        String paw=loginBinding.loginEtPaw.getText().toString().trim();

        if (!phone.equals("")&&!paw.equals("")) {
            RequestClient.getInstance().postLogin(phone, paw)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBean value) {
                            if (value.getResult()==0) {
                                loginBinding.loginConfirmButton.setProgress(100);
                                Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_SHORT).show();
                                loginBinding.loginConfirmButton.setProgress(-1);
                            }
                        }


                        @Override
                        public void onError(Throwable e) {
                            loginBinding.loginConfirmButton.setProgress(-1);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }else {
            Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
            loginBinding.loginConfirmButton.setProgress(0);
        }

    }
}
