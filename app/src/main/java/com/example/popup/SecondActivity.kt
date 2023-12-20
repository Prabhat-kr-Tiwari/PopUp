package com.example.popup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SecondActivity : AppCompatActivity() {
    //create button variable of type button
    private lateinit var button: Button
    //handling back button clicl
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            //showing dialog and then closing the application..
            showDialog()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // adding onbackpressed callback listener.
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

        //intializing the button
        button=findViewById<Button>(R.id.btn_send)
        //adding the setOnCLick Listener
        button.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

    }
    private fun showDialog(){
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Are you sure?")
            setMessage("want to close the application ?")
            setPositiveButton("Yes") { _, _ -> finishAffinity() }
            setNegativeButton("No", null)
            show()
        }
    }
}