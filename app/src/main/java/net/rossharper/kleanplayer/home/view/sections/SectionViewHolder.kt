package net.rossharper.kleanplayer.home.view.sections

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.section.view.*
import net.rossharper.kleanplayer.home.view.SectionViewModel

class SectionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: SectionViewModel) {
        itemView.textView.text = item.title
    }
}