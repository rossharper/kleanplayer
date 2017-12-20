package net.rossharper.kleanplayer.home.view.android.sections

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.section.view.*
import net.rossharper.kleanplayer.home.view.ViewGateway.Section

class SectionViewHolder(
        sectionView: View,
        private val sectionItemsAdapter: SectionItemsAdapter, // TODO: depending on concretion here
        private val sectionItemSelectionListener: SectionItemSelectionListener
) : RecyclerView.ViewHolder(sectionView) {
    fun bind(section: Section) {
        itemView.sectionTitle.text = section.title
        itemView.recyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.HORIZONTAL  }
            adapter = sectionItemsAdapter.apply {
                items = section.items
                itemSelectionListener = { itemPosition ->
                    sectionItemSelectionListener.invoke(adapterPosition, itemPosition)
                }
            }
        }
    }
}
