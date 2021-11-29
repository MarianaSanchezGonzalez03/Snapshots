package com.example.snapshots

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.snapshots.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  mBinding: ActivityMainBinding

    private lateinit var mActiveFragment:Fragment
    private lateinit var mFragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.bind(layoutInflater)
        setContentView(mBinding.root)

        setupBottomNav()
    }
    private fun setupBottomNav(){
        val mFragmentManager=supportFragmentManager
        val home= Home()
        val addFragment= AddFragment()
        val profileFragment= ProfileFragment()

        mActiveFragment=home

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, profileFragment, ProfileFragment::class.java.name)
            .hide(profileFragment).commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, addFragment, AddFragment::class.java.name)
            .hide(addFragment).commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, home, Home::class.java.name).commit()

        mBinding.bottomNav.setOnNavigationItemSelectedListener{

                when(it.itemId){
                R.id.action_home -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(home).commit()
                    mActiveFragment=home
                    true

                    }
                    R.id.action_add -> {
                        mFragmentManager.beginTransaction().hide(mActiveFragment).show(addFragment).commit()
                        mActiveFragment=AddFragment
                        true}
                    R.id.action_profile -> {
                        mFragmentManager.beginTransaction().hide(mActiveFragment).show(profileFragment).commit()
                        mActiveFragment=ProfileFragment
                        true}
                        else-> false

                }

        }
    }
}