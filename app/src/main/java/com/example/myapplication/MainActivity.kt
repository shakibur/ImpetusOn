package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.colan.kindercare.adapter.BaseRecyclerViewAdapter
import com.colan.kindercare.adapter.OnDataBindCallback
import com.colan.kindercare.ui.base.BaseActivity
import com.colan.kindercare.ui.base.BaseNavigator
import com.example.myapplication.data.remote.data.Status
import com.example.myapplication.data.remote.data.response.apiResponse.NewApiData
import com.example.myapplication.data.remote.data.response.apiResponse.NewApiDataItem
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.CoincapListItemsBinding
import com.example.myapplication.utils.navigateTo
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>(), BaseNavigator,
    OnDataBindCallback<CoincapListItemsBinding> {

    private var baseRecyclerAdapter: BaseRecyclerViewAdapter<NewApiDataItem, CoincapListItemsBinding>? =
        null

    var mFilterList = ObservableArrayList<NewApiDataItem>()
    var mDataList = ObservableArrayList<NewApiDataItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel?.setNavigator(this)
        //observerResponse()
        fetch()
        setUpRecyclerview()
        getViewDataBinding().editText.addTextChangedListener {
            editFilter(getViewDataBinding().editText.text.toString())
        }
    }

    override fun getBindingVariable(): Int {
        return BR.mainActivityVM
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
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

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }

    fun observerResponse() {
        mViewModel?.mShowProgressBar?.observeEvent(this, Observer {
            when {
                it -> showLoadingIndicator()
                else -> dismissLoadingIndicator()
            }
        })
        mViewModel?.getUserProfileResponse?.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    mViewModel?.mShowProgressBar?.value = false
                    mViewModel?.getUserProfileJob?.cancel()

                    if (it.data?.data != null) {
                        //setUpRecyclerview(it.data.data)
                        mDataList.addAll(it.data.data)
                        mFilterList.addAll(it.data.data)
                        baseRecyclerAdapter?.cleatDataSet()
                        it.data.data.let {
                            baseRecyclerAdapter?.addDataSet(it)
                        }
                        Log.i("coindata", "cccc------>${it.data.data}")
                    }

                }
                Status.ERROR, Status.FAILURE -> {
                    mViewModel?.mShowProgressBar?.value = false
                    putToast("" + it.message)
                }
                else -> {

                }
            }

        })
    }

    private fun fetch() {
        println("Fetching from open trivia database")
        val url = "https://api.spaceflightnewsapi.net/v3/articles"

        val request = Request.Builder().url(url).build()
        //showProgressBar(random_quiz_root_layout)

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: Call, response: Response) {
                // get response body as string
                val body = response.body()?.string()
                // parse response body as json object
                val gson = GsonBuilder().create()
                val trivaRequest = gson.fromJson(body, NewApiData::class.java)
                dismissProgressBar()

                runOnUiThread {

                    mDataList.addAll(trivaRequest)
                    mFilterList.addAll(trivaRequest)
                    //baseRecyclerAdapter?.cleatDataSet()
                    trivaRequest.forEach {
                        baseRecyclerAdapter?.addDataSet(listOf(it))
                    }
                }

                Log.i("resultOf", "get------>$trivaRequest")


            }

            override fun onFailure(call: Call, e: IOException) {
                dismissProgressBar()
                putToast(e.message.toString())
                println("Failed to execute request")
            }

        })

    }

    fun editFilter(getList: String) {
        val data = mDataList.filter {
            !it.title.isNullOrEmpty()

        }.filter {
            it.title?.contains(getList, ignoreCase = true)!!
        }

        Log.i("searchNew", "fffdd--->${data}")

        mFilterList.clear()
        mFilterList.addAll(data)

        baseRecyclerAdapter?.cleatDataSet()
        baseRecyclerAdapter?.addDataSet(mFilterList)

    }

    private fun setUpRecyclerview() {
        baseRecyclerAdapter = BaseRecyclerViewAdapter(
            R.layout.coincap_list_items,
            BR.leaveApprovalItem,
            ArrayList(),
            null,
            this
        )

        getViewDataBinding()?.rvCurrencyData?.adapter = baseRecyclerAdapter
        baseRecyclerAdapter?.notifyDataSetChanged()


    }

    override fun onItemClick(view: View, position: Int, v: CoincapListItemsBinding) {
    }

    override fun onDataBind(
        v: CoincapListItemsBinding,
        onClickListener: View.OnClickListener
    ) {
    }
}