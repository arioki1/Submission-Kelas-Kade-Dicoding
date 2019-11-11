package com.arioki.submission.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arioki.submission.R
import com.arioki.submission.adapter.FootbalAdapter
import com.arioki.submission.data.FootballItem
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainUi().setContentView(this)
    }

    class MainUi : AnkoComponent<MainActivity>, AnkoLogger {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            val items: MutableList<FootballItem> = mutableListOf()
            val name = resources.getStringArray(R.array.name)
            val id = resources.getStringArray(R.array.id)
            val description = resources.getStringArray(R.array.description)
            val badge = resources.obtainTypedArray(R.array.badge)
            items.clear()
            for (i in name.indices) {
                items.add(
                    FootballItem(
                        id[i],
                        name[i],
                        badge.getResourceId(i, 0),
                        description[i]
                    )
                )
            }
            badge.recycle()
            verticalLayout {

                recyclerView {
                    lparams(
                        width = matchParent,
                        height = matchParent
                    )
                    layoutManager = LinearLayoutManager(ctx)
                    adapter = FootbalAdapter(ctx, items) {
                        //Penerapan dialog
                        toast(it.name.toString())

                        //Penerapan logging
                        info("Log Data")

                        //Penerapan Intent
                        val data: FootballItem = FootballItem(
                            it.id,
                            it.name,
                            it.badge,
                            it.description
                        )
                        startActivity<DetailLigaActivity>(
                            "FootballItem" to data
                        )

                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnMenuSearch -> startActivity(
                Intent(
                    applicationContext,
                    SearchEventActivity::class.java
                )
            )
            R.id.showFavorite -> startActivity(
                Intent(
                    applicationContext,
                    FavoriteActivity::class.java
                )
            )
        }
        return super.onOptionsItemSelected(item)
    }
}
