package net.rossharper.kleanplayer.home.view.android.sections

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.section.view.*
import net.rossharper.kleanplayer.home.view.ViewGateway.Section

class SectionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Section) {
        itemView.textView.text = item.title
    }
}