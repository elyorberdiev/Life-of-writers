package com.example.adiblarhayoti.fragments

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.adiblarhayoti.MainActivity
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.sharedPreferences.MyShared
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.android.synthetic.main.language_dialog.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SettingsFragment : Fragment() {
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

    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_settings, container, false)
        MyShared.init(root.context)
        root.setting_switch.isChecked = MyShared.getTheme()
       val url = "https://play.google.com/store/apps/details?id=uz.mobiler.uzbekistan"

        root.setting_tv3.setOnClickListener {
            Snackbar.make(root, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            val shareBody = "${R.string.app_name}\n${url}"
            share.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(share)

        }

        root.setting_tv4.setOnClickListener {
            val alert = AlertDialog.Builder(root.context).create()
            val view = LayoutInflater.from(root.context).inflate(R.layout.my_dialog, null, false)
            alert.setView(view)
            alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alert.window?.setGravity(Gravity.CENTER)
            alert.show()
        }

        root.setting_tv22.setOnClickListener {
            val alertDialog = AlertDialog.Builder(root.context).create()
            val view =
                LayoutInflater.from(root.context).inflate(R.layout.language_dialog, null, false)
            alertDialog.setView(view)
            if (MyShared.getLang() == "uz") {
                view.radio1.isChecked = true
            } else {
                view.radio2.isChecked = true
            }
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.window?.setGravity(Gravity.CENTER)
            view.radio.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                    when (checkedId) {
                        R.id.radio1 -> {
                            MyShared.setLang("uz")
                        }
                        R.id.radio2 -> {
                            MyShared.setLang("ru")

                        }
                    }
                }

            })

            view.btn1.setOnClickListener {
                alertDialog.dismiss()
            }
            view.btn2.setOnClickListener {
                forRestartIntent2()
                alertDialog.dismiss()
            }

            alertDialog.show()
        }

        root.setting_switch.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked){
                    MyShared.setTheme(true)
                    forRestartIntent2()
                }else{
                    MyShared.setTheme(false)
                    forRestartIntent2()
                }
            }

        })
        return root
    }


    private fun forRestartIntent2() {
        val intent = Intent(root.context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        (activity as MainActivity).finish()
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}