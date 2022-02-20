package com.example.dongnaegoyang

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.databinding.ActivityMainBinding
import com.example.dongnaegoyang.home.CatList
import com.example.dongnaegoyang.home.HorizontalItemDecorator
import com.example.dongnaegoyang.home.VerticalItemDecorator

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
        adapter.items.add(CatList(R.drawable.cheese, "치즈"))
        adapter.items.add(CatList(R.drawable.milkcow, "얼룩이"))
        adapter.items.add(CatList(R.drawable.threecolor, "삼색이"))
        adapter.items.add(CatList(R.drawable.blackcat, "까망"))
        adapter.items.add(CatList(R.drawable.cheese, "치즈2"))
        adapter.items.add(CatList(R.drawable.milkcow, "얼룩이2"))
        adapter.items.add(CatList(R.drawable.threecolor, "삼색이2"))
        adapter.items.add(CatList(R.drawable.blackcat, "까망2"))

        // expandable 사료 배급 전 확인
        binding.linearLayout1.setOnClickListener {
            if (binding.layoutExpand.visibility == View.VISIBLE) {
                binding.layoutExpand.visibility = View.GONE
                //linearLayout1.animate().setDuration(200).rotation(180f)
            } else {
                binding.layoutExpand.visibility = View.VISIBLE
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
        var intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("name", catList.catName)
        intent.putExtra("img", catList.catPic)
        startActivity(intent)
        finish()
        // dataSet.add(listOf("$i th main", "$i th sub"))
    }
}