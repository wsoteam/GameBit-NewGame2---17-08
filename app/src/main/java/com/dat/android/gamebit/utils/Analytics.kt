package com.dat.android.gamebit.utils

import com.amplitude.api.Amplitude
import com.amplitude.api.Identify

object Analytics {
    fun open(){
        Amplitude.getInstance().logEvent("open")
    }

    fun openScreen(){
        Amplitude.getInstance().logEvent("open_screen")
    }

    fun openMenu(){
        Amplitude.getInstance().logEvent("open_menu")
    }

    fun getDomain(){
        Amplitude.getInstance().logEvent("get_domain")
    }


    fun setUserDomain(domain : String){
        var identify = Identify().setOnce("domain", domain)
        Amplitude.getInstance().identify(identify)
    }

    fun setUserNaming(naming : String){
        var identify = Identify().setOnce("naming", naming)
        Amplitude.getInstance().identify(identify)
    }

    fun setUserUrl(url : String){
        var identify = Identify().setOnce("url", url)
        Amplitude.getInstance().identify(identify)
    }
}