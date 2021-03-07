package com.example.bank

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {

    var tvGetResponse: TextView? = null

    init {
        System.loadLibrary("native-lib")
    }

    companion object {
        @JvmStatic
        external fun baseUrlFromJNI(): String
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewsAndWidgets()
        tvGetResponse!!.movementMethod = ScrollingMovementMethod()


    }

    private fun initViewsAndWidgets() {
        tvGetResponse = findViewById(R.id.tvGetResponse)

    }

    fun httpGetJson(view: View) {


        thread {

            val result = ""
            var display = StringBuilder()
            try{

                val url = URL(baseUrlFromJNI())
                val urlConnection: URLConnection = url.openConnection()
                val inputStream: InputStream = urlConnection.getInputStream()
                val result = inputStream.bufferedReader().readText()

                val gson = Gson()
                val arrayAccountType = object : TypeToken<Array<Account>>() {}.type
                var Accounts: Array<Account> = gson.fromJson(result, arrayAccountType)
                for(curr in Accounts){

                    display
                            .append(curr.accountName)
                            .append("\n")
                            .append(curr.amount)
                            .append("\n")
                            .append(curr.iban)
                            .append("\n")
                            .append(curr.currency)
                            .append("\n")
                            .append("\n")

                }

                val file = "Save.txt"
                val data = display.toString()
                val fileOutputStream: FileOutputStream
                try {
                    fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                    fileOutputStream.write(data.toByteArray())
                }catch (e: Exception){
                    e.printStackTrace()


                }

            }

            catch(e : Exception){
                display.append("There was an error please check your connection or use stored data\n")
            }

            tvGetResponse!!.text = display.toString()
        }




    }

    fun Stored(view: View) {

        var display = StringBuilder()

        try{
            var fileInputStream: FileInputStream? = null
            fileInputStream = openFileInput("Save.txt")
            var inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null
            stringBuilder.append("<-- You are offline -->\n")
            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
                stringBuilder.append("\n")
            }
            display.append(stringBuilder.toString())
        }
        catch(e : Exception){
            display.append("No data")
        }

        tvGetResponse!!.text = display.toString()

    }


}