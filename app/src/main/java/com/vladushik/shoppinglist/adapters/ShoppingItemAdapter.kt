package com.vladushik.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladushik.shoppinglist.R
import com.vladushik.shoppinglist.data.entities.ShoppingItem
import com.vladushik.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(var items:List<ShoppingItem>, private val viewModel: ShoppingViewModel) : RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curShoppingItem = items[position]
        holder.itemView.textViewName.text = curShoppingItem.name
        holder.itemView.textViewAmount.text = curShoppingItem.amount.toString()
        holder.itemView.imageViewDelete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }
        holder.itemView.imageViewPlus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }
        holder.itemView.imageViewMinus.setOnClickListener {
            if (curShoppingItem.amount > 0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}