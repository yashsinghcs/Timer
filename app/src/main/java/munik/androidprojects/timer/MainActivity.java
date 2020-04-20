package munik.androidprojects.timer;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SeekBar timerCount;
    TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerCount = (SeekBar) findViewById(R.id.timerCount);
        counter = (TextView) findViewById(R.id.counter);

    }
}
