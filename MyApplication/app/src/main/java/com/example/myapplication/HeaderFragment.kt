package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.network.Api
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.header_fragment.*
import kotlinx.android.synthetic.main.header_fragment.textView
import kotlinx.android.synthetic.main.header_fragment.view.*
import kotlinx.android.synthetic.main.task_fragment.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class HeaderFragment : Fragment() {

    private val coroutineScope = MainScope()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.header_fragment, container, false)
        coroutineScope.launch{
            val name = Api.userService.getInfo().body()?.firstname
            view?.textView?.text = name
        }
        return view
    }

    override fun onResume() {
        coroutineScope.launch{
            val name = Api.userService.getInfo().body()?.firstname
            view?.textView?.text = name.toString()
            val url = Api.userService.getInfo().body()?.avatar ?: "https://goo.gl/gEgYUd"
            //val url = "https://goo.gl/gEgYUd"
            Glide.with(this@HeaderFragment).load(url).fitCenter().circleCrop().into(image_view)

        }
        image_view.isClickable = true
        image_view.setOnClickListener {
            val selectAvatarIntent = Intent(activity?.baseContext, UserInfoActivity::class.java)
            startActivity(selectAvatarIntent)
        }

        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}