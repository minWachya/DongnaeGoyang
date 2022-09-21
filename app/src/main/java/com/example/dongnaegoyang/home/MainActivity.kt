package com.example.dongnaegoyang.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.address_search.SearchAddressActivity
import com.example.dongnaegoyang.ui.cat_add.CatAddActivity
import com.example.dongnaegoyang.ui.cat_detail.CatDetailActivity
import com.example.dongnaegoyang.cat_search.SearchCatActivity
import com.example.dongnaegoyang.databinding.ActivityMainBinding
import com.example.dongnaegoyang.login.LoginActivity
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.mainToolbar) //커스텀한 toolbar를 액션바로 사용

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_sidebar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 제목 숨기기
        binding.mainToolbar.setTitle("")

        val layoutManager = GridLayoutManager(this, 2)
        //layoutManager.setReverseLayout(true)
        //layoutManager.setStackFromEnd(true)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager

        adapter = CatListAdapter{ catList -> adapterOnClick(catList) }
        binding.recyclerView.adapter = adapter // RecyclerViewAdapter(dataSet)

        // 리사이클러뷰 간격
        binding.recyclerView.addItemDecoration(VerticalItemDecorator(10))
        binding.recyclerView.addItemDecoration(HorizontalItemDecorator(8))

        // 더미값
        adapter.items.add(CatList(1, 1, 1, 0, 0, "까망", "올블랙"))
        adapter.items.add(CatList(2, 1, 1, 0, 0, "얼룩이", "젖소"))
        adapter.items.add(CatList(0, 1, 0, 0, 1, "치즈", "치즈"))
        adapter.items.add(CatList(3, 1, 0, 1, 1, "삼색이", "카오스"))
        adapter.items.add(CatList(1, 1, 1, 0, 0, "까망2", "올블랙"))
        adapter.items.add(CatList(2, 1, 1, 0, 0, "얼룩이2", "젖소"))
        adapter.items.add(CatList(0, 1, 0, 0, 1, "치즈2", "치즈"))
        adapter.items.add(CatList(3, 1, 0, 1, 1, "삼색이2", "카오스"))

        if(intent.hasExtra("dong")){
            //binding.tvGu.text=intent.getStringExtra("gu")
            binding.tvDong.text=intent.getStringExtra("dong")
        }

        // tab으로 구분
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.e("TAG", "${tab!!.position}")
                layoutManager.scrollToPositionWithOffset(0,20)
                when(tab.position){
                    0 ->{
                        adapter.filter.filter("")
                    }
                    1 -> {
                        adapter.filter.filter("치즈")
                    }
                    2 -> {
                        adapter.filter.filter("올블랙")
                    }
                    3 -> {
                        adapter.filter.filter("고등어")
                    }
                    4 -> {
                        adapter.filter.filter("턱시도")
                    }
                    5 -> {
                        adapter.filter.filter("카오스")
                    }
                    6 -> {
                        adapter.filter.filter("젖소")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.view.setBackgroundColor(Color.TRANSPARENT)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.mainNavigationView.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.btn_notice-> Toast.makeText(this,"공지사항 클릭", Toast.LENGTH_SHORT).show()
                R.id.btn_customer_service-> Toast.makeText(this,"고객지원 클릭", Toast.LENGTH_SHORT).show()
                R.id.btn_setting-> Toast.makeText(this,"설정 클릭", Toast.LENGTH_SHORT).show()
            }
            true
        }

        // 지역 출력되는 칸 누르면 search address 페이지로 이동
        binding.layoutAddress.setOnClickListener {
            val addressIntent = Intent(applicationContext, SearchAddressActivity::class.java)
            addressIntent.putExtra("from", "home")
            /*resultLauncher.launch(addressIntent)*/
            startActivity(addressIntent)
            /*var intent = Intent(this@MainActivity, SearchAddressActivity::class.java)
            startActivity(intent)*/
        }

        // expandable 사료 배급 전 확인
        binding.linearLayout1.setOnClickListener {
            if (binding.layoutExpand.visibility == View.VISIBLE) {
                binding.layoutExpand.visibility = View.GONE
                //binding.linearLayout1.animate().setDuration(200)
            } else {
                binding.layoutExpand.visibility = View.VISIBLE
                //binding.linearLayout1.animate().setDuration(200)
            }
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                binding.recyclerView.animation = AnimationUtils.loadAnimation(binding.recyclerView.context, R.anim.slide_up)
            }
        })

        // floating button
        binding.btnAddCat.setOnClickListener { view ->
            var intent = Intent(this@MainActivity, CatAddActivity::class.java)
            startActivity(intent)
        }

        //사이드바 닉네임 부분
        val navi = binding.mainNavigationView.getHeaderView(0)
        val btnLogin = navi.findViewById<TextView>(R.id.btn_login)
        val btnNickname = navi.findViewById<TextView>(R.id.btn_nickname)
        //TODO : 이 두개 중에 뭘로 설정할 지는 어디서 결정하는 걸까. 둘 중 하나는 null인 듯한데
        btnLogin?.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        btnNickname?.setOnClickListener {
            Toast.makeText(this, "main sidebar nickname clicked", Toast.LENGTH_SHORT).show()
        }

    }

    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    // 네비바 드로어 & 검색 툴바
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
            R.id.btn_search -> { //검색 버튼 눌렀을 때
                var intent = Intent(this@MainActivity, SearchCatActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun adapterOnClick(catList: CatList) {
        var intent = Intent(this@MainActivity, CatDetailActivity::class.java)
        intent.putExtra("name", catList.catName)
        //intent.putExtra("img", catList.catPic)
        startActivity(intent)
        // dataSet.add(listOf("$i th main", "$i th sub"))
    }

}