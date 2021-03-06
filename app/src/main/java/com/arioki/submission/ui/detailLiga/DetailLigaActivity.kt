/*
 * *
 *   Created by Yoga Setiawan on 12/8/19 11:31 AM
 *   Copyright (c) 2019 . All rights reserved.
 *   Last modified 11/28/19 5:04 AM
 *   Github : https://github.com/arioki1/Submission-Kelas-Kade-Dicoding.git
 *
 */

package com.arioki.submission.ui.detailLiga

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.arioki.submission.App
import com.arioki.submission.R
import com.arioki.submission.adapter.PagerAdapter
import com.arioki.submission.data.FootballItem
import com.arioki.submission.data.LeaguesItem
import com.arioki.submission.ext.logger
import com.arioki.submission.ui.listTeam.ListTeamFragment
import com.arioki.submission.ui.match.MatchFragment
import com.arioki.submission.ui.standingsEvent.StandingsEventFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_liga.*

class DetailLigaActivity : AppCompatActivity(), DetailLigaView {
    override fun getDetailLigaFailed() {
        shimmerInfoLeague.visibility = View.GONE
    }

    override fun getDetaiLigaDone(it: LeaguesItem) {
        it.run {
            iv_str_league.text = strLeague
            iv_country.text = strCountry
            iv_date_first_event.text = dateFirstEvent
            iv_gender.text = strGender

            Picasso.get()
                .load("$strBadge/preview")
                .into(ivLogo, object : Callback {
                    override fun onSuccess() {
                        shimmerInfoLeague.visibility = View.GONE
                        llInfoLeague.visibility = View.VISIBLE
                    }

                    override fun onError(e: Exception?) {
                        "Error".logger()
                    }
                })
        }
    }

    override fun startShimmer() {
        shimmerInfoLeague.startShimmer()
    }

    var data: FootballItem? = null
    lateinit var presenter: DetailLigaPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_detail_liga)
        data = intent.getParcelableExtra("FootballItem")
        initTab()
        presenter = DetailLigaPresenter(this, getId(), App.instances.repository)
        presenter.getDetailLiga()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getId(): Int {
        val id = data?.id.toString()
        return id.toInt()
    }

    private fun initTab() {
        val pages = listOf(
            MatchFragment(),
            StandingsEventFragment(),
            ListTeamFragment()
        )
        val title = listOf(
            "Match",
            "Standings",
            "Team"
        )
        viewPagerDetailLiga.adapter =
            PagerAdapter(supportFragmentManager, pages, title)
        tabsDetailLiga.setupWithViewPager(viewPagerDetailLiga)
    }
}
