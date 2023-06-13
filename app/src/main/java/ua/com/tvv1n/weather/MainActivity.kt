package ua.com.tvv1n.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, WeatherFragment.newInstance())
            .commit()
    }
}