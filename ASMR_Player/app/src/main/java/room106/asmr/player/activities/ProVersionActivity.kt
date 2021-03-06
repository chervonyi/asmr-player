package room106.asmr.player.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import room106.asmr.player.R

class ProVersionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pro_version)
    }

    fun onClickClose(v: View) {
        finish()
        overridePendingTransition(
            R.anim.freeze,
            R.anim.slide_out_bottom
        )
    }
}