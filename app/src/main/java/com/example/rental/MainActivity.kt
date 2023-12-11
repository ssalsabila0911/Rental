package com.example.rental

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val buttonNav:BottomNavigationView = findViewById(R.id.buttonNav)
        val home = HomeFragment()
        val favorite = FavoriteFragment()
        val order = OrderFragment()
        val akun = AkunFragment()


        currentFragment(home)

        buttonNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> currentFragment(home)
                R.id.favorite -> currentFragment(favorite)
                R.id.order -> currentFragment(order)
                R.id.akun -> currentFragment(akun)
            }
            true
        }
    }

    private fun currentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }

    }
}
