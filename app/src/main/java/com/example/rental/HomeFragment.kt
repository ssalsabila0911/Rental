package com.example.rental

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val menuMotor:ImageView = view.findViewById(R.id.btnMotor)
        val menuDes:ImageView = view.findViewById(R.id.btnDes)
        val menuMaps:ImageView = view.findViewById(R.id.btnPeta)

        menuMotor.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, ListMotorActivity::class.java))

            }
        }

        menuDes.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, DesListActivity::class.java))

            }
        }

        menuMaps.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, MapsActivity::class.java))
            }
        }

        val textNama : TextView = view.findViewById(R.id.NmProfile)
        textNama.text = nama

        return view
    }

    companion object {
        var nama = "nama"
        var email = "email"
        var nohp = "nohp"
        var password = "password"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}