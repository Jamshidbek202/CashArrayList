package com.jamshidbek.casharraylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import com.jamshidbek.casharraylist.cash.CashSharedPreference
import com.jamshidbek.casharraylist.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var userList:ArrayList<User>
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CashSharedPreference.init(this)

        userList = CashSharedPreference.objectString!!

        val userName = ArrayList<String>()

        for (user in userList) {
            userName.add(user.name!!)

        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userName)
        list.adapter = adapter

        btn_save.setOnClickListener{
            var name = edt_name.text.toString().trim()
            var number = edt_number.text.toString().trim()

            if(name != "" && number != ""){
                userList.add(User(name, number))
                userName.add(name)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Lines may be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}