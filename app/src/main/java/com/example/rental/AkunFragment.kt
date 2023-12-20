package com.example.rental

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AkunFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AkunFragment : Fragment() {
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

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_akun, container, false)
        val btnAkun : TextView = view. findViewById(R.id.tV_akun)

        btnAkun.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this,DetailAkunActivity::class.java))
            }
        }


        val textNama : TextView = view.findViewById(R.id.ProfNama)
        textNama.text = AkunFragment.nama

        val textEmail : TextView = view.findViewById(R.id.tvEmail)
        textEmail.text = AkunFragment.email
        return view
    }

    companion object {
        var nama = "nama"
        var email = "email"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AkunFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AkunFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}