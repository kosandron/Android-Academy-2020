package com.android.fundamentals.workshop02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.fundamentals.R
import com.android.fundamentals.data.models.Actor
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class WS02ActorsAdapter : RecyclerView.Adapter<ActorsViewHolder>() {

    // Do not change.
    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .circleCrop()

    private var actors = listOf<Actor>()

    override fun getItemViewType(position: Int): Int {
        return when(actors.size) {
            0 -> _EMPTY
            else ->  _ACTORS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return when (viewType) {
            _EMPTY -> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_actors_empty, parent, false))
            else -> DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_actors_data, parent, false))

        }
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {holder.onBind(imageOption, actors[position])}
            is EmptyViewHolder-> {}
        }
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
    }
}

abstract class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class EmptyViewHolder(itemView: View) : ActorsViewHolder(itemView)
private class DataViewHolder(itemView: View) : ActorsViewHolder(itemView) {
    private val avatar: ImageView? = itemView.findViewById(R.id.iv_actor_avatar)
    private val name: TextView? = itemView.findViewById(R.id.tv_actor_name)
    private val oscarState: TextView? = itemView.findViewById(R.id.tv_actor_oscar_state)

    fun onBind(options: RequestOptions, actor: Actor) {
        Glide.with(context)
            .load(actor.avatar)
            .apply(options)
            .into(avatar)


        name?.text = actor.name
        oscarState?.text = context.getString(
                R.string.fragment_actors_avatar_oscar_state_text,
                actor.hasOscar.toString()
        )
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private const val _EMPTY = 0
private const val _ACTORS = 1