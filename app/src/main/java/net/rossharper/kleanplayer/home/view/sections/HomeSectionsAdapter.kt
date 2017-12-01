package net.rossharper.kleanplayer.home.view.sections

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import net.rossharper.kleanplayer.R
import net.rossharper.kleanplayer.home.view.SectionViewModel

class HomeSectionsAdapter : RecyclerView.Adapter<SectionViewHolder>() {

    var items: List<SectionViewModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SectionViewHolder {
        val itemView = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.section, parent, false)
        return SectionViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(items[position])
    }
}