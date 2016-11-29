package com.fengao.zhuanpan;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView tv_show;
    private Button start;
    private ObjectAnimator animator;
    boolean playing =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        tv_show = (TextView) findViewById(R.id.tv_show);
        start = (Button) findViewById(R.id.start);
        animator = ObjectAnimator.ofFloat(iv, "rotation", 0, 360);
        animator.setInterpolator(new LinearInterpolator());//设置匀速旋转
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setDuration(1000);//控制旋转速度
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                Log.i("fengao", "onAnimationUpdate: " + value);
                tv_show.setText("转动角度： "+value);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (playing) {
                    start.setText("开始");
                    animator.pause();
                } else {
                    start.setText("停止");
                    animator.start();
                }
                playing = !playing;
            }
        });
    }
}
