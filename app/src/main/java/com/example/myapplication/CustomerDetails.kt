package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.colan.kindercare.adapter.OnDataBindCallback
import com.colan.kindercare.ui.base.BaseActivity
import com.colan.kindercare.ui.base.BaseNavigator
import com.example.myapplication.databinding.ActivityCustomerDetailsBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.CoincapListItemsBinding
import com.example.myapplication.utils.navigateTo

class CustomerDetails : BaseActivity<ActivityCustomerDetailsBinding, MainActivityVM>(), BaseNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)
    }

    override fun getBindingVariable(): Int {
        return BR.customerActivityVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_customer_details
    }

    override fun getViewModel(): MainActivityVM {
        return ViewModelProviders.of(this).get(MainActivityVM::class.java)
    }

    override fun onClickView(v: View?) {
        when (v?.id) {

        }
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        navigateTo(this, clazz, null)
    }
}