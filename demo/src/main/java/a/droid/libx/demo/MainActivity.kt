package a.droid.libx.demo

import a.droid.libx.core.BundleX
import a.droid.libx.core.IntentX
import a.droid.libx.core.SharedPreferencesX
import a.droid.libx.ktx.commit
import a.droid.libx.ktx.get
import a.droid.libx.ktx.shared
import a.droid.libx.ktx.toast
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            12345.toast(this)
        }
        //ktx
        val spf = shared("xx")
        spf.commit {
            it.putString("xx", "hello world")
        }

        toast(spf.get<String>("xx"))

//        IntentX.on(this)
//                .className(packageName, MainActivity::class.java)
//                .extras(BundleX().putString("12", "34"))
//                .startActivity()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}