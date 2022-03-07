package com.example.dongnaegoyang

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.cat_detail.CatDetailActivity
import com.example.dongnaegoyang.databinding.FragmentSearchCatBinding
import com.example.dongnaegoyang.home.CatList
import com.example.dongnaegoyang.home.CatListAdapter
import com.example.dongnaegoyang.home.HorizontalItemDecorator
import com.example.dongnaegoyang.home.VerticalItemDecorator

private const val TAG = "mmmCatSearchFragment"

private var _binding: FragmentSearchCatBinding? = null
private val binding get() = _binding!!
private lateinit var adapter: CatListAdapter

class SearchCatFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchCatBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(activity, 2)
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

        return inflater.inflate(R.layout.fragment_search_cat, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // 검색 버튼 누를 때 호출
            override fun onQueryTextSubmit(query: String?): Boolean {


                return true
            }

            // 검색창에서 글자가 변경이 일어날 때마다 호출
            override fun onQueryTextChange(newText: String?): Boolean {


                return true
            }
        })
    }
    /*override fun onBackPressed() {
        if (!binding.searchView.isIconified) {
            binding.searchView.isIconified = true
        } else {
            super.onBackPressed()
        }
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "SearchFragment"
    }

    fun adapterOnClick(catList: CatList){
        var intent = Intent(activity, CatDetailActivity::class.java)
        intent.putExtra("name", catList.catName)
        intent.putExtra("img", catList.catPic)
        startActivity(intent)
        // finish()
        // dataSet.add(listOf("$i th main", "$i th sub"))
    }
}