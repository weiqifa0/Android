package com.example.g_sensor;
import com.example.g_sensor.R;
import java.util.Calendar;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	private static final String TAG = MainActivity.class.getSimpleName();
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private TextView textviewX;
	private TextView textviewY;
	private TextView textviewZ;
	private TextView textviewF;

	Calendar mCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textviewX = (TextView) findViewById(R.id.textView1);
		textviewY = (TextView) findViewById(R.id.textView3);
		textviewZ = (TextView) findViewById(R.id.textView4);
		textviewF = (TextView) findViewById(R.id.textView2);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);// TYPE_GRAVITY
		if (null == mSensorManager) {
			Log.d(TAG, "deveice not support SensorManager");
		}
		// 参数三，检测的精准度
		mSensorManager.registerListener(this, mSensor,
				SensorManager.SENSOR_DELAY_NORMAL);// SENSOR_DELAY_GAME

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor == null) {
			return;
		}

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			float x = (int) event.values[0];
			float y = (int) event.values[1];
			float z = (int) event.values[2];
			mCalendar = Calendar.getInstance();

			textviewX.setText(String.valueOf(x));
			textviewY.setText(String.valueOf(y));
			textviewZ.setText(String.valueOf(z));
			textviewF.setText("方向传感器数据发生率变化");


		}
	}
}