package com.example.myapplication



import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.network.Api
import com.example.myapplication.network.login
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener {
            logIn()
        }
    }

    private fun logIn() {
        if(!email_enter.text.isEmpty() && !password_enter.text.isEmpty()) {

            val form = LoginForm(email_enter.text.toString(), password_enter.text.toString())
            login(Api.INSTANCE.userService, form, ::displayTasks, ::displayError)
        }
    }

    private fun displayError(message: String)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun displayTasks(fetchedToken: String)
    {
        PreferenceManager.getDefaultSharedPreferences(context).edit {
            putString(SHARED_PREF_TOKEN_KEY, fetchedToken)
        }

        val intent = Intent(activity?.applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

}
