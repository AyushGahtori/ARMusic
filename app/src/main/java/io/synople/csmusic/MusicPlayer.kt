package io.synople.csmusic

import android.content.Context
import android.media.MediaPlayer
import io.synople.csmusic.model.NoteBlock

class MusicPlayer(private var context: Context, private val onPlay: (String) -> Unit) :
    MediaPlayer.OnCompletionListener {
    private var noteRes: MutableList<MutableList<Int>> = mutableListOf()
    private var noteBlocks: MutableList<MutableList<NoteBlock>> = mutableListOf()
    private lateinit var mediaPlayer: MediaPlayer

    private var currPlaying = 0

    fun play(noteBlockList: MutableList<MutableList<NoteBlock>>) {
        noteBlocks = noteBlockList

        noteBlockList.forEach {
            val tempRes = mutableListOf<Int>()
            it.forEach { noteBlock ->
                tempRes.add(stringToRes(noteBlock.fileName))
            }

            noteRes.add(tempRes)
        }

        currPlaying = 0

        mediaPlayer = MediaPlayer.create(context, noteRes[0][0])
        if (noteRes[0].size > 1) {
            for (x in 1 until noteRes[0].size) {
                val tempMP = MediaPlayer.create(context, noteRes[0][x])
                tempMP.start()
                tempMP.setOnCompletionListener { finMP -> finMP?.release() }
            }
        }
        mediaPlayer.setOnCompletionListener(this)
        onPlay(noteBlockList[0][0].id)
        mediaPlayer.start()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        mp?.release()
        if (++currPlaying < noteRes.size) {
            val nextMp = MediaPlayer.create(context, noteRes[currPlaying][0])
            if (noteRes[currPlaying].size > 1) {
                for (x in 1 until noteRes[currPlaying].size) {
                    val tempMP = MediaPlayer.create(context, noteRes[currPlaying][x])
                    tempMP.start()
                    tempMP.setOnCompletionListener { finMP -> finMP?.release() }
                }
            }
            nextMp.setOnCompletionListener(this)
            onPlay(noteBlocks[currPlaying][0].id)
            nextMp.start()
        }
    }
}