package com.dat.android.gamebit.presentation.black

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dat.android.gamebit.App
import com.dat.android.gamebit.utils.PreferenceProvider

class Client : WebViewClient() {
    var lastUrl = ""

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().acceptCookie()
        CookieManager.getInstance().flush()
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        Log.e("LOL", "check URL ======= $url")
        if (PreferenceProvider.getOfUrl() == "" && url!!.contains("clickl")){
            PreferenceProvider.saveOfUrl(url)
        }

        if (url!!.startsWith("mailto")) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(url!!.replace("mailto:", "")))
            App.getInstance().startActivity(Intent.createChooser(intent, "Mail to Support"))
        } else if (url!!.startsWith("tel:")) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(url)
            App.getInstance().startActivity(intent)
        } else if (url!!.startsWith("https://t.me/joinchat")) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            App.getInstance().startActivity(intent)
        }else {
            PreferenceProvider.setLastURL(url!!)
            view!!.loadUrl(url!!)
        }
        return true
    }
}