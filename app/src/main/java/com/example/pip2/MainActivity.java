package com.example.pip2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pip2.R;

public class MainActivity extends AppCompatActivity { //클래스를 상속받아 MainActivity 클래스를 작성한다.
    private Button button; //버튼을 선언한다.
    private FrameLayout player; //player를 선언한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate 메소드의 매개변수는 앱의 이전 실행상태를 전달한다.
        super.onCreate(savedInstanceState); //부모 클래스의 onCreate 호출한다.
        setContentView(R.layout.activity_main); //메인을 띄운다.

        player = findViewById(R.id.player); //player은 id player로 찾는다.
        button = findViewById(R.id.pip); //button은 pip로 찾는다.
        button.setOnClickListener(new View.OnClickListener(){ // 클릭리스너 생성
            public void onClick(View v) { //클릭할 때
                if(Build.VERSION.SDK_INT >= 26){ //만약 sdk가 26이상이면
                    try{ // PIP 모드를 지원한다.
                        Rational rational = new Rational(player.getWidth(), player.getHeight());
                        PictureInPictureParams mParams = new PictureInPictureParams.Builder()
                                .setAspectRatio(rational).build();

                        enterPictureInPictureMode(mParams);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                } else { //26이하면
                    Toast.makeText(getApplicationContext(), "API 26가 필요합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        if(!isInPictureInPictureMode){
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.GONE);
        }
    }
}
