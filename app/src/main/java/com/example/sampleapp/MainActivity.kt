package com.example.sampleapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.OnBackStackChangedListener
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE

class MainActivity : AppCompatActivity(),FragmentManager.OnBackStackChangedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragBtn = findViewById<Button>(R.id.btnFirst)
        val secFragBtn = findViewById<Button>(R.id.btnSec)
        val popBtn = findViewById<Button>(R.id.btnPop)

        supportFragmentManager.addOnBackStackChangedListener(this)

        firstFragBtn.setOnClickListener {
           val firsFragment = FirstFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentContainer,firsFragment)
                addToBackStack("firstFrag")
                commit()
            }
        }

        secFragBtn.setOnClickListener {
            val secondFragment = SecondFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragmentContainer,secondFragment)
                addToBackStack("secondFrag")
                commit()
            }
        }

        popBtn.setOnClickListener {
            //supportFragmentManager.popBackStack(null, POP_BACK_STACK_INCLUSIVE)
            supportFragmentManager.popBackStack(0, POP_BACK_STACK_INCLUSIVE)
        }

//        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                onBackPress()
//            }
//        })

    }

    override fun onBackStackChanged() {

        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        if (backStackEntryCount > 0) {
            Log.d("backStack","Fragments in back stack:")
            for (i in 0 until backStackEntryCount) {
                val entry = supportFragmentManager.getBackStackEntryAt(i)
                Log.d("backStack","Fragment: ${entry.name} and ${entry.id}")
            }
        } else {
           Log.d("backStack","Back stack is empty.")
        }
    }
//
//    fun onBackPress(){
//       // Toast.makeText(this,"On back Pressed",Toast.LENGTH_SHORT).show()
//            supportFragmentManager.popBackStack("firstFrag", POP_BACK_STACK_INCLUSIVE)
//    }


        }
