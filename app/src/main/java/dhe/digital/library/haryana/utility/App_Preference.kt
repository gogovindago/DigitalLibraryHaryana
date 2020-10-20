package dhe.digital.library.haryana.utility

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class App_Preference(internal var context: Context) {
    var appSharedPrefs: SharedPreferences
    var prefsEditor: SharedPreferences.Editor

    var EMAIL_ID = " "
    var ADDRESS = "address"
    var USER_ID = "user_id"
    var FIREBASE_REGISTRATION = "firebase_registration"
    var USERNAME = "user_name"
    var MOBILESTATUS = "mobile_status"
    var EMAILSTATUS = "email_status"
    var IMAGE_URL = "image_url"
    var ACCESS_TOKEN = "access_token"
    var COUNTRY_CODE = "country_code"
    var STATUS = "status"
    var ACTIVITYSTATUS = "activitystatus"
    var CHATSTATUS = "chatstatus"

    var DriverJobStatus = "DriverJobStatus"
    var MOBILE_NUMBER = "mobile_number"
    var GENDER = "gender"
    var ONRIDE = false
    var JOBSTATUS = "jobstatus"
    var isISRIDESTARTED = false




    var jobStatus: String?
        get() = appSharedPrefs.getString("JobStatus", "")
        set(jobStatus) {
            prefsEditor.putString(JOBSTATUS, jobStatus).commit()
        }

    var iS_LOGGED_IN: Boolean
        get() = appSharedPrefs.getBoolean("logged_in", false)
        set(flag) {
            prefsEditor.putBoolean("logged_in", flag).commit()
        }

    var onride: Boolean
        get() = appSharedPrefs.getBoolean("On_Ride", false)
        set(flag) {
            prefsEditor.putBoolean("On_Ride", flag).commit()
        }

    var user_id: String?
        get() = appSharedPrefs.getString(USER_ID, "")
        set(user_id) {
            prefsEditor.putString(USER_ID, user_id).commit()
        }

    var accesS_TOKEN: String?
        get() = appSharedPrefs.getString(ACCESS_TOKEN, "")
        set(access_token) {
            prefsEditor.putString(ACCESS_TOKEN, access_token).commit()
        }

    var activitystatus: String?
        get() = appSharedPrefs.getString(ACTIVITYSTATUS, "")
        set(activitystatus) {
            prefsEditor.putString(ACTIVITYSTATUS, activitystatus).commit()
        }

    var chatstatus: String?
        get() = appSharedPrefs.getString(CHATSTATUS, "")
        set(chatstatus) {
            prefsEditor.putString(CHATSTATUS, chatstatus).commit()
        }

    var firebasE_REGISTRATION: String?
        get() = appSharedPrefs.getString(FIREBASE_REGISTRATION, "")
        set(firebase_registration) {
            prefsEditor.putString(FIREBASE_REGISTRATION, firebase_registration).commit()
        }

    var mobilestatus: String?
        get() = appSharedPrefs.getString(MOBILESTATUS, "")
        set(mobilestatus) {
            prefsEditor.putString(MOBILESTATUS, mobilestatus).commit()
        }

    var emailstatus: String?
        get() = appSharedPrefs.getString(EMAILSTATUS, "")
        set(emailstatus) {
            prefsEditor.putString(EMAILSTATUS, emailstatus).commit()
        }

    var mobilE_NUMBER: String?
        get() = appSharedPrefs.getString(MOBILE_NUMBER, "")
        set(mobile_number) {
            prefsEditor.putString(MOBILE_NUMBER, mobile_number).commit()
        }

    var gender: String?
        get() = appSharedPrefs.getString(GENDER, "")
        set(gender) {
            prefsEditor.putString(MOBILE_NUMBER, gender).commit()
        }

    var status: String?
        get() = appSharedPrefs.getString(STATUS, "")
        set(status) {
            prefsEditor.putString(STATUS, status).commit()
        }

    var countrY_CODE: String?
        get() = appSharedPrefs.getString(COUNTRY_CODE, "")
        set(country_code) {
            prefsEditor.putString(COUNTRY_CODE, country_code).commit()
        }

    var completE_ADDRESS: String?
        get() = appSharedPrefs.getString(ADDRESS, "")
        set(complete_address) {
            prefsEditor.putString(ADDRESS, complete_address).commit()
        }

    var userName: String?
        get() = appSharedPrefs.getString(USERNAME, "")
        set(user_name) {
            prefsEditor.putString(USERNAME, user_name).commit()
        }

    var imagE_URL: String?
        get() = appSharedPrefs.getString(IMAGE_URL, "")
        set(image_url) {
            prefsEditor.putString(IMAGE_URL, image_url).commit()
        }

    var emaiL_ID: String?
        get() = appSharedPrefs.getString(EMAIL_ID, "")
        set(user_name) {
            prefsEditor.putString(EMAIL_ID, user_name).commit()
        }

    init {
        this.appSharedPrefs = context.getSharedPreferences(APP_PREFER, Activity.MODE_PRIVATE)
        this.prefsEditor = appSharedPrefs.edit()
    }



    fun clearPrefs() {


        prefsEditor.clear()
        prefsEditor.commit()
    }

    companion object {

        val APP_PREFER = "APP_PREFER"
    }
}










