package com.example.dongnaegoyang

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.dongnaegoyang.databinding.ActivitySearchCatBinding
import com.example.dongnaegoyang.home.CatListAdapter

class SearchCatActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchCatBinding
    private lateinit var adapter: CatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 툴바 달기
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 검색: 한 글자씩 누를 때마다 해당 아이템 출력
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter?.getFilter()?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.getFilter()?.filter(newText);
                return true
            }

        })
    }

    // 툴바에서 뒤로가기 버튼 클릭 시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {  // 뒤로가기 이모지(<-) 클릭
                Toast.makeText(applicationContext, "뒤로가기 클릭", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}