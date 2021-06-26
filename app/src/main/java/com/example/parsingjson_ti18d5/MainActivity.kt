package com.example.parsingjson_ti18d5

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parsingjson_ti18d5.model.DataItem
import com.example.parsingjson_ti18d5.model.ResponseUser
import com.example.parsingjson_ti18d5.network.ApiConfig
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = UserAdapter(mutableListOf())



        rv_user.setHasFixedSize(true)
        rv_user.layoutManager = LinearLayoutManager(this)
        rv_user.adapter = adapter
        getUser()
    }

    private fun getUser(){
        val client = ApiConfig.GetApiService().getListUser("1")

        client.enqueue(object : retrofit2.Callback<ResponseUser> {
            override fun onResponse(call: retrofit2.Call<ResponseUser>, response: retrofit2.Response<ResponseUser>){
                if (response.isSuccessful) {
                    val dataAray = response.body()?.data as List<DataItem>
                    for (data in dataAray){
                        adapter.addUser(data)
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<ResponseUser>, t: Throwable) {
                Toast.makeText( this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }
}

