package io.synople.csmusic.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.synople.csmusic.R
import io.synople.csmusic.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrameLayout, MainFragment.newInstance())
            .commit()
    }
}
