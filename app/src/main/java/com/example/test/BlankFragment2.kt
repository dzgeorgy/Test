package com.example.test

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_blank2.view.*

class BlankFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank2, container, false)
        val fab = requireActivity().fab
        val fragmentLayout = view.fr
        val transition = MaterialContainerTransform(requireContext()).apply {
            startView = fab
            endView = fragmentLayout
        }
        TransitionManager.beginDelayedTransition(container, transition)
        fab.hide()
        fragmentLayout.visibility = View.VISIBLE
        val returnTransition = MaterialContainerTransform(requireContext()).apply {
            startView = fragmentLayout
            endView = fab
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            TransitionManager.beginDelayedTransition(container, returnTransition)
            fragmentLayout.visibility = View.GONE
            fab.show()
            findNavController().popBackStack()
        }
        return view
    }

}