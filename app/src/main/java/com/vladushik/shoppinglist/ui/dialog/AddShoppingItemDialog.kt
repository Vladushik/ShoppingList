package com.vladushik.shoppinglist.ui.dialog

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.vladushik.shoppinglist.R
import com.vladushik.shoppinglist.data.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_shop_item.*

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_shop_item)
        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()
            if (name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context, "Hey", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
        tvCancel.setOnClickListener {
            cancel()
        }
    }
}