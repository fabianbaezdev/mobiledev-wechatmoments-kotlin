package com.tws.moments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tws.moments.ui.navigation.AppNavigation
import com.tws.moments.ui.theme.WeChatTheme

private const val TAG = "MainActivity##"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            WeChatTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }


    /*   private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        val repository = MomentRepository()
        MainViewModelFactory(repository)
    }

    private val adapter = MomentsAdapter()

    private var reqPageIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindow()

        ScreenAdaptiveUtil.adaptive(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        subscribe()
    }


    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            MomentDividerItemDecoration(
                offsets = dip(10),
                dividerColor = Color.parseColor("#dddddd"),
                startPosition = 1
            )
        )

        binding.recyclerView.adapter = this.adapter

        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshTweets()
        }

        binding.recyclerView.addOnScrollListener(object : LoadMoreListener() {
            override fun onLoadMore() {
                Log.i(TAG, "load more reqPageIndex:$reqPageIndex,pageCount:${viewModel.pageCount}")
                if (reqPageIndex <= viewModel.pageCount - 1) {
                    Log.i(TAG, "internal load more")
                    viewModel.loadMoreTweets(reqPageIndex) {
                        reqPageIndex++
                        adapter.addMoreTweet(it)
                    }
                }
            }
        })
    }

    private fun subscribe() {
        viewModel.userBean.observe(this, Observer {
            adapter.user = it
        })

        viewModel.tweets.observe(this, Observer {
            binding.swipeRefreshLayout.isRefreshing = false
            reqPageIndex = 1
            adapter.tweets = it.toMutableList() as ArrayList<Tweet>
        })
    }

    private fun initWindow() {
        val flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.decorView.systemUiVisibility = flag
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    } */
}
