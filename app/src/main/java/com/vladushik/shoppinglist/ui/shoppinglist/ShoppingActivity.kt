package com.vladushik.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vladushik.shoppinglist.R
import com.vladushik.shoppinglist.adapters.ShoppingItemAdapter
import com.vladushik.shoppinglist.data.db.ShoppingDatabase
import com.vladushik.shoppinglist.data.entities.ShoppingItem
import com.vladushik.shoppinglist.data.repositories.ShoppingRepository
import com.vladushik.shoppinglist.ui.dialog.AddDialogListener
import com.vladushik.shoppinglist.ui.dialog.AddShoppingItemDialog
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory:ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)


        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter  = ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter
        viewModel.getAllShoppingItem().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        fab.setOnClickListener {
            AddShoppingItemDialog(this, object: AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }




}
