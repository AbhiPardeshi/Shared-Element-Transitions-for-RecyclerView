package com.neostore.sharedelementstransitiondemo

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by webwerks on 21/8/19.
 */
class GridFragment : Fragment() {

    var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recyclerView = inflater.inflate(R.layout.fragment_grid, container, false) as RecyclerView?
        recyclerView.apply {
            recyclerView?.adapter = GridAdapter(this@GridFragment)
        }

        prepareTransitions()
        postponeEnterTransition()

        return recyclerView
    }

    private fun prepareTransitions() {
        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.grid_exit_transition)

        // A similar mapping is set at the ImagePagerFragment with a setEnterSharedElementCallback.
        setExitSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(
                    names: List<String>?,
                    sharedElements: MutableMap<String, View>?
                ) {
                    // Locate the ViewHolder for the clicked position.
                    val selectedViewHolder = recyclerView
                        ?.findViewHolderForAdapterPosition(MainActivity.currentPosition)
                    if (selectedViewHolder == null || selectedViewHolder.itemView == null) {
                        return
                    }

                    // Map the first shared element name to the child ImageView.
                    sharedElements!![names!![0]] =
                        selectedViewHolder.itemView.findViewById(R.id.card_image)
                }
            })
    }
}