package net.rossharper.kleanplayer.home.view.android.sections

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import net.rossharper.kleanplayer.R
import net.rossharper.kleanplayer.home.view.ViewGateway.Section

class HomeSectionsAdapter : RecyclerView.Adapter<SectionViewHolder>() {

    var sections: List<Section> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var sectionItemSelectionListener : SectionItemSelectionListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SectionViewHolder {
        val sectionView = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.section, parent, false)
        return SectionViewHolder(sectionView, SectionItemsAdapter(), {
            sectionPosition, itemPosition ->
            sectionItemSelectionListener?.invoke(sectionPosition, itemPosition)
        })
    }

    override fun getItemCount(): Int = sections.size

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(sections[position])
    }
}