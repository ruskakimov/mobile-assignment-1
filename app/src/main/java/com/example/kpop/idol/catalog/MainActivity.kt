package com.example.kpop.idol.catalog

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize RecyclerView.
        val viewManager = LinearLayoutManager(this)
        val idolAdapter = IdolAdapter(listOf())
        val recyclerView = findViewById<RecyclerView>(R.id.idols_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = idolAdapter
        }

        // Populate KPOP group spinner with the list of groups.
        val groupSpinner: Spinner = findViewById(R.id.kpop_groups_spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.groups_array,
            android.R.layout.simple_spinner_item
        ).also { adapter: ArrayAdapter<CharSequence> ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            groupSpinner.adapter = adapter
        }

        groupSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedGroup = parent.getItemAtPosition(position).toString()
                println(selectedGroup)
                val idols = getIdols(selectedGroup)
                idolAdapter.updateIdols(idols)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Do nothing.
            }
        }

        // Setup button.
        val shuffleButton = findViewById<Button>(R.id.shuffle_button)
        shuffleButton.setOnClickListener {
            idolAdapter.shuffleIdols()
        }
    }

    private fun getIdols(groupName: String): List<Idol> {
        return when (groupName) {
            "Twice" -> listOf(
                Idol("Nayeon", "임나연", "September 22, 1995", "ISTP", R.drawable.twice_nayeon),
                Idol("Jeongyeon", "유정연", "November 1, 1996", "ISFJ", R.drawable.twice_jeongyeon),
                Idol("Momo", "모모", "November 9, 1996", "INFP", R.drawable.twice_momo),
                Idol("Sana", "사나", "December 29, 1996", "ENFP", R.drawable.twice_sana),
                Idol("Jihyo", "박지효", "February 1, 1997", "ISFP", R.drawable.twice_jihyo),
                Idol("Mina", "미나", "March 24, 1997", "ISFP", R.drawable.twice_mina),
                Idol("Dahyun", "다현", "May 28, 1998", "ISFJ", R.drawable.twice_dahyun),
                Idol("Chaeyoung", "채영", "April 23, 1999", "INFP", R.drawable.twice_chaeyoung),
                Idol("Tzuyu", "쯔위", "June 14, 1999", "ISFP", R.drawable.twice_tzuyu)
            )
            "ITZY" -> listOf(
                Idol("Yeji", "예지", "May 26, 2000", "ESFJ", R.drawable.twice_nayeon),
                Idol("Lia", "리아", "July 21, 2000", "ENFJ", R.drawable.twice_nayeon),
                Idol("Ryujin", "류진", "April 17, 2001", "INTJ", R.drawable.twice_nayeon),
                Idol("Chaeryeong", "채령", "June 5, 2001", "INFJ", R.drawable.twice_nayeon),
                Idol("Yuna", "유나", "December 9, 2003", "ENFP", R.drawable.twice_nayeon)
            )
            "Blackpink" -> listOf(
                Idol("Jisoo", "지수", "January 3, 1995", "INFJ", R.drawable.twice_nayeon),
                Idol("Jennie", "제니", "January 16, 1996", "ISTP", R.drawable.twice_nayeon),
                Idol("Rosé", "로제", "February 11, 1997", "ISFP", R.drawable.twice_nayeon),
                Idol("Lisa", "리사", "March 27, 1997", "ENFP", R.drawable.twice_nayeon)
            )
            else -> listOf()
        }
    }
}

data class Idol(val name: String, val koreanName: String, val birthday: String, val mbti: String, val imageResId: Int)
