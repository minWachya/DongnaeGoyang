package com.example.dongnaegoyang.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.cat_add.CatAddActivity
import com.example.dongnaegoyang.cat_detail.CatDetailActivity
import com.example.dongnaegoyang.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CatListAdapter

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

        // 사이드바
        binding.btnSideBar.setOnClickListener{

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
        finish()
        // dataSet.add(listOf("$i th main", "$i th sub"))
    }
}