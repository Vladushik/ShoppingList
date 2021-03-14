package com.vladushik.shoppinglist.ui.dialog

import com.vladushik.shoppinglist.data.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item:ShoppingItem)
}