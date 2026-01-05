package com.granitaistore.obddiagnostic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.granitaistore.obddiagnostic.obd.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: тут підставиш реальний BluetoothService
        val bt = BluetoothService()
        val elm = Elm327Manager(bt)
        val trip = TripManager()
        val logger = CsvLogger(this)

        startLogBtn.setOnClickListener { logger.start() }
        stopLogBtn.setOnClickListener { logger.stop() }

        lifecycleScope.launch(Dispatchers.IO) {
            elm.initOpel()

            while (true) {
                val rpmVal = elm.rpm()
                val speedVal = elm.speed()
                val tempVal = elm.coolantTemp()
                val fuelVal = elm.fuelL100km()

                trip.update(speedVal, elm.fuelLph())

                runOnUiThread {
                    rpm.text = "RPM\n$rpmVal"
                    speed.text = "SPEED\n$speedVal"
                    temp.text = "TEMP\n$tempVal°C"
                    fuel.text = "FUEL\n%.1f L/100km".format(fuelVal)
                    trip.text = "TRIP\n%.1f km | %.1f L/100km"
                        .format(trip.distance(), trip.avgFuel())
                }

                delay(1000)
            }
        }
    }
}
