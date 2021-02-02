package com.example.wongnai.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wongnai.R
import com.example.wongnai.data.Coins
import com.example.wongnai.data.api.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit private var apiService: ApiService
    var name = arrayListOf("")
    var desc = arrayListOf("")
    var url = arrayListOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = ApiService()
        getCoins()
    }

    private fun getCoins(){
        val call: Call<Coins> = apiService.getCoins()

        call.enqueue(object : Callback<Coins> {
            override fun onFailure(call: Call<Coins>, t: Throwable) {
                Log.e("API",t.message.toString())
            }

            override fun onResponse(call: Call<Coins>, response: Response<Coins>) {
                print("hello")
                if(response.isSuccessful){
                    val item = response.body()
                    var temp =""
                    for(i in 0 until item!!.data.coins.size){
                        name.add(item!!.data.coins[i].name)
                        temp = item!!.data.coins[i].description
                        if(temp!=null)
                            temp = temp.replace("<p>","")
                        desc.add(temp)
                        url.add(item!!.data.coins[i].iconUrl)
                    }
                    val n = name.drop(1)
                    val d = desc.drop(1)
                    val imageurl = url.drop(1)
                    val myListAdapter = MyListAdapter(this@MainActivity,n,d,imageurl)
                    list_view.adapter = myListAdapter
                }
            }
        })
    }
}