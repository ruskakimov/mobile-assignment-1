package com.example.kpop.idol.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class IdolAdapter(private var idols: List<Idol>) :
    RecyclerView.Adapter<IdolAdapter.IdolViewHolder>() {

    class IdolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idolName: TextView = itemView.findViewById(R.id.idol_name)
        val idolKoreanName: TextView = itemView.findViewById(R.id.idol_korean_name)
        val idolBirthday: TextView = itemView.findViewById(R.id.idol_birthday)
        val idolMbti: TextView = itemView.findViewById(R.id.idol_mbti)
        val idolImage: ShapeableImageView = itemView.findViewById(R.id.idol_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdolViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.idol_card, parent, false)
        return IdolViewHolder(view)
    }

    override fun onBindViewHolder(holder: IdolViewHolder, position: Int) {
        val idol = idols[position]
        holder.idolName.text = idol.name
        holder.idolKoreanName.text = idol.koreanName
        holder.idolBirthday.text = idol.birthday
        holder.idolMbti.text = idol.mbti
        holder.idolImage.setImageResource(idol.imageResId)
    }

    override fun getItemCount() = idols.size

    fun updateIdols(newIdols: List<Idol>) {
        this.idols = newIdols
        notifyDataSetChanged()
    }

    fun shuffleIdols() {
        val shuffledIdols = this.idols.shuffled()
        this.updateIdols(shuffledIdols)
    }
}
