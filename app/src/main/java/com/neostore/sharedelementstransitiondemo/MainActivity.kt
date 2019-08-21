package com.neostore.sharedelementstransitiondemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0)
            // Return here to prevent adding additional GridFragments when changing orientation.
            return
        }
        val fragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, GridFragment(), GridFragment::class.java.simpleName)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CURRENT_POSITION, currentPosition)
    }

    companion object {
        var currentPosition: Int = 0
        private val KEY_CURRENT_POSITION = "com.google.samples.gridtopager.key.currentPosition"
    }
}
