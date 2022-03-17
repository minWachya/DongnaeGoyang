package com.example.dongnaegoyang.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.address_search.SearchAddressActivity
import com.example.dongnaegoyang.cat_add.CatAddActivity
import com.example.dongnaegoyang.cat_detail.CatDetailActivity
import com.example.dongnaegoyang.cat_search.SearchCatActivity
import com.example.dongnaegoyang.databinding.ActivityMainBinding
import com.example.dongnaegoyang.databinding.DrawerMainSidebarBinding
import com.example.dongnaegoyang.databinding.DrawerMainSidebarLoggedInBinding
import com.example.dongnaegoyang.databinding.DrawerMainSidebarLoggedOutBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CatListAdapter
    private lateinit var _binding: DrawerMainSidebarBinding
    private lateinit var binding_: DrawerMainSidebarLoggedInBinding
    private lateinit var binding__: DrawerMainSidebarLoggedOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = GridLayoutManager(this, 2)
        //layoutManager.setReverseLayout(true)
        //layoutManager.setStackFromEnd(true)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager

        adapter = CatListAdapter{ catList -> adapterOnClick(catList) }
        binding.recyclerView.adapter = adapter // RecyclerViewAdapter(dataSet)

        // 리사이클러뷰 간격
        binding.recyclerView.addItemDecoration(VerticalItemDecorator(20))
        binding.recyclerView.addItemDecoration(HorizontalItemDecorator(10))

        // 더미값
        adapter.items.add(CatList(R.drawable.cheese, "치즈", "치즈"))
        adapter.items.add(CatList(R.drawable.milkcow, "얼룩이", "젖소"))
        adapter.items.add(CatList(R.drawable.threecolor, "삼색이", "카오스"))
        adapter.items.add(CatList(R.drawable.blackcat, "까망", "올블랙"))
        adapter.items.add(CatList(R.drawable.cheese, "치즈2", "치즈"))
        adapter.items.add(CatList(R.drawable.milkcow, "얼룩이2", "젖소"))
        adapter.items.add(CatList(R.drawable.threecolor, "삼색이2", "카오스"))
        adapter.items.add(CatList(R.drawable.blackcat, "까망2", "올블랙"))

        if(intent.hasExtra("gu")){
            binding.tvGu.text=intent.getStringExtra("gu")
        }

        // tab으로 구분
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.e("TAG", "${tab!!.position}")
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

        // 사이드바 (화면 기준 왼쪽)
        binding.btnSideBar.setOnClickListener{
            _binding.mainDrawerLayout.openDrawer(GravityCompat.START)
        }
        /*binding_.btnNickname.setOnClickListener {
            logOut()
        }
        binding__.btnLogin.setOnClickListener {
            logIn()
        }*/
        // 고영희 돌봄 가이드
        _binding.linearlayoutCare.setOnClickListener {

        }
        // 공지사항
        _binding.btnNotice.setOnClickListener {

        }
        // 검색 (화면 기준 오른쪽 아이콘)
        binding.btnSearch.setOnClickListener{
            var intent = Intent(this@MainActivity, SearchCatActivity::class.java)
            startActivity(intent)
        }

        // 지역 출력되는 칸 누르면 search address 페이지로 이동
        binding.layoutAddress.setOnClickListener {
            var intent = Intent(this@MainActivity, SearchAddressActivity::class.java)
            startActivity(intent)
        }

        // expandable 사료 배급 전 확인
        binding.linearLayout1.setOnClickListener {
            if (binding.layoutExpand.visibility == View.VISIBLE) {
                binding.layoutExpand.visibility = View.GONE
                //linearLayout1.animate().setDuration(200).rotation(180f)
            } else {
                binding.layoutExpand.visibility = View.VISIBLE
                binding.linearLayout1.visibility = View.VISIBLE
                //linearLayout1.animate().setDuration(200).rotation(0f)
            }
        }

        // floating button
        binding.btnAddCat.setOnClickListener { view ->
            var intent = Intent(this@MainActivity, CatAddActivity::class.java)
            startActivity(intent)
        }
    }

    fun adapterOnClick(catList: CatList){
        var intent = Intent(this@MainActivity, CatDetailActivity::class.java)
        intent.putExtra("name", catList.catName)
        intent.putExtra("img", catList.catPic)
        startActivity(intent)
        // dataSet.add(listOf("$i th main", "$i th sub"))
    }
    private fun logOut(){
        /*main_header_include_logged_in.visibility = View.INVISIBLE
        main_header_include_logged_out.visibility = View.VISIBLE
        main_navigation_btn1.isEnabled = false
        main_navigation_btn1.setTextColor(Color.parseColor("#777777"))
        main_navigation_btn2.isEnabled = false
        main_navigation_btn2.setTextColor(Color.parseColor("#777777"))
        main_navigation_btn3.isEnabled = false
        main_navigation_btn3.setTextColor(Color.parseColor("#777777"))*/
    }

    private fun logIn(){
        /*main_header_include_logged_in.visibility = View.VISIBLE
        main_header_include_logged_out.visibility = View.INVISIBLE
        main_navigation_btn1.isEnabled = true
        main_navigation_btn1.setTextColor(Color.parseColor("#ffffff"))
        main_navigation_btn2.isEnabled = true
        main_navigation_btn2.setTextColor(Color.parseColor("#ffffff"))
        main_navigation_btn3.isEnabled = true
        main_navigation_btn3.setTextColor(Color.parseColor("#ffffff"))*/
    }
}