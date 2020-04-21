package munik.androidprojects.timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SeekBar timerCount;
    TextView counter;
    MediaPlayer playSound;
    Button startStop;
    CountDownTimer countDownTimer;

    public void timer(View view) {

        if (startStop.getText().equals("START!")) {
            startStop.setText("RESET!");
            timerCount.setVisibility(View.INVISIBLE);
            int counterInSec = timerCount.getProgress();
            countDownTimer = new CountDownTimer(counterInSec * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateCounter((int) (millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    playSound.start();
                    playSound.setLooping(true);

                }
            }.start();

        } else if (startStop.getText().equals("RESET!")) {
            playSound.stop();
            startStop.setText("START!");
            timerCount.setVisibility(View.VISIBLE);
            counter.setText("00:00");
            timerCount.setProgress(0);
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerCount = (SeekBar) findViewById(R.id.timerCount);
        counter = (TextView) findViewById(R.id.counter);
        playSound = MediaPlayer.create(this, R.raw.rooster_times_up);
        startStop = (Button) findViewById(R.id.startStop);
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
