package net.rossharper.kleanplayer.home.view.android.sections

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item.view.*
import net.rossharper.kleanplayer.home.view.ViewGateway

class ShowItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ViewGateway.Item.ShowItem) {
        itemView.title.text = item.title
    }
}