package com.parohy.myfridge.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.parohy.myfridge.R
import com.parohy.myfridge.api.model.Food
import com.parohy.myfridge.api.model.FoodType
import com.parohy.myfridge.api.model.Measurement
import com.parohy.myfridge.api.repo.FoodRepository
import com.parohy.myfridge.appComponent
import com.parohy.myfridge.util.DateUtil
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), ActionInterface {
    @Inject
    lateinit var foodRepository: FoodRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application.appComponent().injectMainActivity(this)
        tocActionButton.setOnClickListener {
            with(supportFragmentManager.fragments.first().childFragmentManager.fragments.first() as ActionFragment) {
                onAction(this@MainActivity)
            }
        }
    }

    override fun actionAdd() {
        foodRepository.addFood(
            Food(
                "Apple",
                FoodType.EDIBLE,
                Measurement.SOLID,
                1.0,
                DateUtil.getCurrentDate()
            )
        )
    }
}
