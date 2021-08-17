package com.dat.android.gamebit.utils

import java.lang.Exception

object URLMaker {

    private const val DOMAIN_NAME = "https://longtails.biz/8zz9GF?"
    private const val DELIMETER = "/"


    //[web_id]-[id_account]-[appsflyer_id]-[advertising_id]-[sub_id_1]
    //[web_id]-[sub_id_1]
    //https://longtails.biz/8zz9GF?afid=$id&gadid=1057b684-c970-4d87-900f-a75269795258&webid=10500&sub_id_1=kek

    //afid (aps id) - uniq id of aps for user
    //gadid  - advert id (google)
    //gadid - advert id (google)

    fun createLink(naming : String, gadid : String, afid : String) : String {
        var sub_id_1 = ""
        var sub_id_2 = ""
        var sub_id_3 = ""
        var sub_id_4 = ""
        var sub_id_5 = ""
        var sub_id_6 = ""

        try {
            sub_id_1 = naming.split(DELIMETER)[0]
        }catch (ex : Exception){

        }

        try {
            sub_id_2 = naming.split(DELIMETER)[1]
        }catch (ex : Exception){

        }

        try {
            sub_id_3 = naming.split(DELIMETER)[2]
        }catch (ex : Exception){

        }

        try {
            sub_id_4 = naming.split(DELIMETER)[3]
        }catch (ex : Exception){

        }

        try {
            sub_id_5 = naming.split(DELIMETER)[4]
        }catch (ex : Exception){

        }

        try {
            sub_id_6 = naming.split(DELIMETER)[5]
        }catch (ex : Exception){

        }


        var url = "${DOMAIN_NAME}afid=$afid&gadid=$gadid&sub_id_1=$sub_id_1&sub_id_2=$sub_id_2&sub_id_3=$sub_id_3&sub_id_4=$sub_id_4&sub_id_5=$sub_id_5&sub_id_6=$sub_id_6"
        return url
    }

}