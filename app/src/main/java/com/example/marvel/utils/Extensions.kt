package com.example.marvel.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.marvel.R

var isNeedToShow = true

fun Fragment.addTo(activity: AppCompatActivity, container: Int) {
    val transaction = activity.supportFragmentManager.beginTransaction()
    transaction.add(container, this)
    transaction.addToBackStack(this.tag)
    transaction.commit()
}

fun Activity.showAlertDialog() {
    if (isNeedToShow) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setCancelable(false)
        val layoutView: View = this.layoutInflater.inflate(R.layout.error_dialog, null)
        val dialogButton: Button = layoutView.findViewById(R.id.btnDialog)
        dialogBuilder.setView(layoutView)
        val alertDialog = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        if (!alertDialog.isShowing) {
            alertDialog.show()
            isNeedToShow = false
        }
        dialogButton.setOnClickListener {
            alertDialog.dismiss()
            isNeedToShow = true
        }
    }

}
