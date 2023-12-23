package io.synople.csmusic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import io.synople.csmusic.MusicCompiler
import io.synople.csmusic.MusicPlayer
import io.synople.csmusic.R
import io.synople.csmusic.adapters.BlockAdapter
import io.synople.csmusic.fragments.pickerdialogfragments.ForPickerDialogFragment
import io.synople.csmusic.fragments.pickerdialogfragments.IfPickerDialogFragment
import io.synople.csmusic.fragments.pickerdialogfragments.NotePickerDialogFragment
import io.synople.csmusic.model.Block
import io.synople.csmusic.model.MethodBlock
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.renderable_block.*

class MainFragment : Fragment() {

    private lateinit var arFragment: ArFragment
    private lateinit var adapters: MutableList<BlockAdapter>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_main, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapters = mutableListOf()

        arFragment = (childFragmentManager.findFragmentById(R.id.uxFragment) as ArFragment)
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor).apply {
                setParent(arFragment.arSceneView.scene)
            }

            ViewRenderable.builder()
                .setView(context, R.layout.renderable_block)
                .build()
                .thenAccept { modelObject ->
                    val transformableNode = TransformableNode(arFragment.transformationSystem).apply {
                        renderable = modelObject
                        localRotation = Quaternion.axisAngle(Vector3(1.0f, 0f, 0f), 270f)
                    }
                    transformableNode.translationController.isEnabled = false
                    anchorNode.addChild(transformableNode)

                    val notes = mutableListOf<Block>()
                    notes.add(MethodBlock(adapters.size))
                    val adapter = BlockAdapter(notes) {
                        print(it.toString())
                    }
                    adapters.add(adapter)
                    rvBlocks.adapter = adapter
                    rvBlocks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    btnAddNote.setOnClickListener {
                        NotePickerDialogFragment.newInstance().show(fragmentManager!!) {
                            notes.add(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                    btnAddFor.setOnClickListener {
                        ForPickerDialogFragment.newInstance().show(fragmentManager!!) {
                            notes.add(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                    btnAddIf.setOnClickListener {
                        IfPickerDialogFragment.newInstance().show(fragmentManager!!) {
                            notes.add(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                    btnAddMethod.setOnClickListener {
                        val b = AlertDialog.Builder(it.context)
                        b.setTitle("Method")
                        val arr = arrayListOf<CharSequence>()
                        for (x in 0 until adapters.size) {
                            arr.add("M$x")
                        }
                        b.setItems(arr.toTypedArray()) { dialog, which ->
                            dialog.dismiss()
                            notes.add(MethodBlock(which))
                            adapter.notifyDataSetChanged()
                        }
                        b.show()
                    }
                }
        }

        ivPlay.setOnClickListener {
            // pre-process for method
            adapters[0].blocks.forEach {
                if (it is MethodBlock) {
                    val methodNum = it.methodNum
                    it.list = adapters[methodNum].blocks
                }
            }

            (adapters[0].blocks[0] as MethodBlock).list = mutableListOf()

            val musicPlayer = MusicPlayer(context!!) {
                adapters[0].blocks.forEach { block ->
                    if (block.colorStatus == 2) block.colorStatus = 1
                    if (block.id != it) {
                        block.colorStatus = 2
                    }
                }
                adapters[0].notifyDataSetChanged()
            }
            val p = MusicCompiler(adapters[0].blocks).compile()
            musicPlayer.play(p)
        }

        ivSocial.setOnClickListener {
            SocialDialogFragment.newInstance().show(fragmentManager!!) {
                // TODO: Next user tap on arPlane places it.music
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}

