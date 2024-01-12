package com.android.fundamentals.workshop01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.domain.ActorsDataSource

class WS01ActorsFragment : Fragment() {

    private var someRecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         someRecycler = view.findViewById<RecyclerView>(R.id.rv_actors)
         someRecycler?.adapter = WS01ActorsAdapter()
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    private fun updateData() {
        (someRecycler?.adapter as? WS01ActorsAdapter)?.apply {
            bindActors(ActorsDataSource().getActors())
        }
    }

    companion object {
        fun newInstance() = WS01ActorsFragment()
    }
}