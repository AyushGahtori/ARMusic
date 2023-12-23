package io.synople.csmusic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.synople.csmusic.R
import io.synople.csmusic.model.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.block_note.*


class NoteBlockAdapter(
    private val noteBlocks: MutableList<MutableList<NoteBlock>>,
    private val itemClick: (Block) -> Unit
) :
    RecyclerView.Adapter<NoteBlockAdapter.ViewHolder>() {

    class ViewHolder(override val containerView: View, private val itemClick: (Block) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindNote(noteBlocks: MutableList<NoteBlock>) {
            var text = ""
            noteBlocks.forEach {
                text += it.fileName + "\n"
            }
            tvBlockNote.text = text

            rlBlockNote.setOnClickListener { itemClick }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.block_note, parent, false), itemClick)

    override fun onBindViewHolder(holder: NoteBlockAdapter.ViewHolder, position: Int) {
        holder.bindNote(noteBlocks[position])
    }

    override fun getItemCount() = noteBlocks.size
}