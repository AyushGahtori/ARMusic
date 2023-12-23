package io.synople.csmusic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.synople.csmusic.R
import io.synople.csmusic.model.Profile
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.profile_display.*

class ProfileViewAdapter(
    private val studentList: List<Profile>,
    private val onPlayClick: (Profile) -> Unit,
    private val onDownloadClick: (Profile) -> Unit
) : RecyclerView.Adapter<ProfileViewAdapter.ViewHolder>() {

    class ViewHolder(
        override val containerView: View,
        private val onPlayClick: (Profile) -> Unit,
        private val onDownloadClick: (Profile) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItems(item: Profile) {
            tvName.text = item.name

            ivPlay.setOnClickListener { onPlayClick(item) }
            ivDownload.setOnClickListener { onDownloadClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.profile_display, parent, false),
            onPlayClick,
            onDownloadClick
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(studentList[position])
    }

    override fun getItemCount() = studentList.size
}