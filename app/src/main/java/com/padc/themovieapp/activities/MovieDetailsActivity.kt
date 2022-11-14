package com.padc.themovieapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.padc.themovieapp.R
import com.padc.themovieapp.data.models.MovieModel
import com.padc.themovieapp.data.models.MovieModelImpl
import com.padc.themovieapp.data.vos.ActorVO
import com.padc.themovieapp.data.vos.GenreVO
import com.padc.themovieapp.data.vos.MovieVO
import com.padc.themovieapp.mvp.presenters.MainDetailsPresenterImpl
import com.padc.themovieapp.mvp.presenters.MovieDetailsPresenter
import com.padc.themovieapp.mvp.views.MovieDetailsView
import com.padc.themovieapp.mvvm.MovieDetailsViewModel
import com.padc.themovieapp.utils.IMAGE_BASE_URL
import com.padc.themovieapp.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.view_holder_movie.*
import kotlinx.android.synthetic.main.activity_movie_details.tvMovieName as tvMovieName1

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_MOVIE_ID  = "EXTRA_MOVIE_ID"
        fun newIntent(context: Context,movieId:Int): Intent {
            val intent =  Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID,movieId)
            return intent
        }

    }

    //ViewPods
    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorsViewPod: ActorListViewPod

    //View Model
    private lateinit var mViewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0)

        movieId?.let {
          setUpViewModel(it)
        }

        setUpViewPods()
        setUpListeners()
        observeLiveData()

    }

    private fun observeLiveData() {
        mViewModel.movieDetailsLivedData?.observe(this){
            it?.let { movie -> blindData(movie)}
        }

        mViewModel.castLiveData.observe(this,actorsViewPod::setData)
        mViewModel.crewLiveData.observe(this,actorsViewPod::setData)
    }

    private fun setUpViewModel(movieId: Int) {
        mViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        mViewModel.getInitialData(movieId)
    }


    private fun blindData(movie: MovieVO) {

        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieDetail)

        tvMovieName.text = movie.title ?: ""
        tvRelease.text = movie.releaseDate?.substring(0,4)
        tvRating.text = movie.voteAverage?.toString() ?: ""
        movie.voteCount?.let {
            tvNumberOfVotes.text = "$it VOTES"
        }
        rbMovieDetail.rating = movie.getRatingBasedOnFiveStar()

        blindGenres(movie, movie.genres ?: listOf())

        tvOverView.text = movie.overview ?: ""
        tvOriginalTitle.text = movie.originalTitle ?: ""
        tvType.text = movie.getGenreAsCommaSeparatedString()
        tvProduction.text = movie.getProductionCountriesAsCommaSeparatedString()
        tvPremiere.text = movie.releaseDate ?: ""
        tvDescription.text = movie.overview ?: ""

    }

    private fun blindGenres(movie: MovieVO, genres: List<GenreVO>) {

        movie.genres?.count()?.let {

            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""

            if(it<3){
                tvThirdGenre.visibility = View.GONE
            }else if(it<2){
                tvSecondGenre.visibility = View.GONE
            }
        }

    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setUpViewPods() {
        actorsViewPod = vpActors as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        creatorsViewPod = vpCreators as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }
}