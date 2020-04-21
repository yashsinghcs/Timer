package munik.androidprojects.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SeekBar timerCount;
    TextView counter;

    public void timer(View view) {
        int counterInSec = timerCount.getProgress();
        CountDownTimer countDownTimer = new CountDownTimer(counterInSec * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCounter((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerCount = (SeekBar) findViewById(R.id.timerCount);
        counter = (TextView) findViewById(R.id.counter);
        timerCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateCounter(progress);
//                timer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void updateCounter(int counterInSec) {
        int minutes = counterInSec / 60;
        int sec = counterInSec - minutes * 60;
        counter.setText((minutes > 9 ? Integer.toString(minutes) : "0" + Integer.toString(minutes)) + ":" + (sec > 9 ? Integer.toString(sec) : "0" + Integer.toString(sec)));

    }
}
