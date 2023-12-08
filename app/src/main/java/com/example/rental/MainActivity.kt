package com.example.rental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rental.AkunFragment
import com.example.rental.FavoriteFragment
import com.example.rental.HomeFragment
import com.example.rental.OrderFragment
import com.example.rental.R
import com.example.rental.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ganti baris ini menjadi setOnNavigationItemSelectedListener
        binding.buttonNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.favorite -> replaceFragment(FavoriteFragment())
                R.id.order -> replaceFragment(OrderFragment())
                R.id.akun -> replaceFragment(AkunFragment())
                else -> {
                    // Tambahkan tindakan untuk item yang tidak dihandle
                }
            }
            true // Pindahkan ini ke dalam blok setOnNavigationItemSelectedListener
        }

        // Pindahkan baris ini ke luar blok setOnNavigationItemSelectedListener
        replaceFragment(HomeFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
