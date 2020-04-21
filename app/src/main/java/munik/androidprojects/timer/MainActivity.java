package munik.androidprojects.timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SeekBar timerCount;
    TextView counter;
    MediaPlayer playSound;
    Button startStop;
    CountDownTimer countDownTimer;

    public void timer(View view) {
        playSound = MediaPlayer.create(this, R.raw.rooster_times_up);
        if (timerCount.getProgress() != 0) {
            if (startStop.getText().equals("START!")) {
//            Log.i("place", "start");
                timerCount.setVisibility(View.INVISIBLE);
                int counterInSec = timerCount.getProgress();
                startStop.setText("RESET!");
                countDownTimer = new CountDownTimer(counterInSec * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        updateCounter((int) (millisUntilFinished / 1000));
                    }

                    @Override
                    public void onFinish() {
                        startStop.setText("RESET!");

//                    Log.i("finish", "finshesfinish");
                        playSound.start();
                        playSound.setLooping(true);
                    }
                }.start();

            } else if (startStop.getText().equals("RESET!")) {
//            Log.i("place", "reste stop");
                playSound.setLooping(false);
                playSound.pause();
                startStop.setText("START!");
                timerCount.setVisibility(View.VISIBLE);
                counter.setText("00:00");
                timerCount.setProgress(0);
                countDownTimer.cancel();
            }
        } else {
            Toast.makeText(this, "Please set the timer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerCount = (SeekBar) findViewById(R.id.timerCount);
        counter = (TextView) findViewById(R.id.counter);

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
