package net.rossharper.kleanplayer.home.view.android.sections

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import net.rossharper.kleanplayer.R
import net.rossharper.kleanplayer.home.view.ViewGateway

class SectionItemsAdapter : RecyclerView.Adapter<ShowItemViewHolder>() {

    var items: List<ViewGateway.Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemSelectionListener : ItemSelectionListener? = null

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShowItemViewHolder {
        val itemView = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item, parent, false)

        return ShowItemViewHolder(itemView).apply {
            itemView.setOnClickListener {
                itemSelectionListener?.invoke(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: ShowItemViewHolder?, position: Int) {
        holder!!.bind(items[position] as ViewGateway.Item.ShowItem)
    }
}