package net.rossharper.kleanplayer.home.view.android.sections

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.section.view.*
import net.rossharper.kleanplayer.R
import net.rossharper.kleanplayer.home.view.ViewGateway
import net.rossharper.kleanplayer.home.view.ViewGateway.Section

class SectionViewHolder(sectionView: View) : RecyclerView.ViewHolder(sectionView) {
    fun bind(section: Section) {
        itemView.sectionTitle.text = section.title
        itemView.recyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.HORIZONTAL  }
            adapter = SectionItemsAdapter().apply { items = section.items }
        }
    }
}

class SectionItemsAdapter : RecyclerView.Adapter<ShowItemViewHolder>() {
    var items: List<ViewGateway.Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShowItemViewHolder {
        val itemView = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item, parent, false)
        return ShowItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShowItemViewHolder?, position: Int) {
        holder!!.bind(items[position] as ViewGateway.Item.ShowItem)
    }
}

class ShowItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ViewGateway.Item.ShowItem) {
        itemView.title.text = item.title
    }
}
