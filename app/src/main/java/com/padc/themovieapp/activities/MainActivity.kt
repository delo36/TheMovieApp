package com.padc.themovieapp.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padc.themovieapp.R
import com.padc.themovieapp.adapters.BannerAdapter
import com.padc.themovieapp.adapters.ShowcaseAdapter
import com.padc.themovieapp.data.models.MovieModel
import com.padc.themovieapp.data.models.MovieModelImpl
import com.padc.themovieapp.data.models.MovieModelImpl.getMoviesByGenre
import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.data.vos.GenreVO
import com.padc.themovieapp.data.vos.MovieVO
import com.padc.themovieapp.delegates.BannerViewHolderDelegate
import com.padc.themovieapp.delegates.MovieViewHolderDelegate
import com.padc.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padc.themovieapp.dummy.dummyDataList
import com.padc.themovieapp.mvp.presenters.MainPresenter
import com.padc.themovieapp.mvp.presenters.MainPresenterImpl
import com.padc.themovieapp.mvp.views.MainView
import com.padc.themovieapp.mvvm.MainViewModel
import com.padc.themovieapp.viewpods.ActorListViewPod
import com.padc.themovieapp.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_details.*

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate,ShowcaseViewHolderDelegate,MovieViewHolderDelegate{

    //ViewPods

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter
    lateinit var mBestPopularMovieListViewPod : MovieListViewPod
    lateinit var mMoviesByGenreViewPod : MovieListViewPod
    lateinit var mActorListViewPod : ActorListViewPod

  //ViewModel
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()

        setUpToolbar()
        setUpViewPods()
        setUpBannerViewPager()
        setUpShowcaseRecyclerView()
        setUpListener()

        //Observe Live Data
        observeLiveData()

    }

    private fun observeLiveData() {
        mViewModel.nowPlayingMovieLiveData?.observe(this,mBannerAdapter::setNewData)
        mViewModel.popularMovieLiveData?.observe(this,mBestPopularMovieListViewPod::setData)
        mViewModel.topRatedMovieLiveData?.observe(this,mShowcaseAdapter::setNewData)
        mViewModel.genresLiveData.observe(this,this::setUpGenreTabLayout)
        mViewModel.moviesByGenreLiveData.observe(this,mMoviesByGenreViewPod::setData)
        mViewModel.actorsLiveData.observe(this,mActorListViewPod::setData)
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mViewModel.getInitialData()
    }


    private fun setUpGenreTabLayout(genreList : List<GenreVO>) {
        genreList.forEach{
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }
    }




    private fun setUpViewPods(){
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)

        mMoviesByGenreViewPod = vpMoviesByGenre as MovieListViewPod
        mMoviesByGenreViewPod.setUpMovieListViewPod(this)

        mActorListViewPod = vpActorHomeScreen as ActorListViewPod
    }

    private fun setUpShowcaseRecyclerView() {
        mShowcaseAdapter = ShowcaseAdapter(this)
        rvShowCases.adapter = mShowcaseAdapter
        rvShowCases.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setUpListener() {

        //Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mViewModel.getMovieByGenre(tab?.position ?: 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setUpToolGenreTabLayout() {
        dummyDataList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it
                tabLayoutGenre.addTab(this)
            }
        }
    }

    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(this)
        viewPagerBanner.adapter = mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.searchBar -> {
                startActivity(MovieSearchActivity.newIntent(applicationContext))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

}