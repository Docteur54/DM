package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.network.Api
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
        }

        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}