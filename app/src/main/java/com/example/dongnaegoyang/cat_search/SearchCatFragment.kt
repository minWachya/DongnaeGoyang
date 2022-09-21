package com.example.dongnaegoyang.cat_search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dongnaegoyang.ui.cat_detail.CatDetailActivity
import com.example.dongnaegoyang.databinding.FragmentSearchCatBinding
import com.example.dongnaegoyang.home.CatList
import com.example.dongnaegoyang.home.HorizontalItemDecorator
import com.example.dongnaegoyang.home.VerticalItemDecorator

private const val TAG = "mmmCatSearchFragment"

private var _binding: FragmentSearchCatBinding? = null
private val binding get() = _binding!!
private lateinit var adapter: SearchCatAdapter

class SearchCatFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchCatBinding.inflate(inflater, container, false)
        val view = binding.root

        val layoutManager = GridLayoutManager(activity, 2)
        layoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = layoutManager

        adapter = SearchCatAdapter{ catList -> adapterOnClick(catList) }
        binding.recyclerView.adapter = adapter // RecyclerViewAdapter(dataSet)

        // 리사이클러뷰 간격
        binding.recyclerView.addItemDecoration(VerticalItemDecorator(10))
        binding.recyclerView.addItemDecoration(HorizontalItemDecorator(8))

        // 더미값
        adapter.items.add(CatList(1, 1, 1, 0, 0, "치즈", "치즈"))
        adapter.items.add(CatList(2, 1, 1, 0, 0, "얼룩이", "젖소"))
        adapter.items.add(CatList(0, 1, 0, 0, 1, "삼색이", "카오스"))
        adapter.items.add(CatList(3, 1, 0, 1, 1, "까망", "올블랙"))
        adapter.items.add(CatList(1, 1, 1, 0, 0, "치즈2", "치즈"))
        adapter.items.add(CatList(2, 1, 1, 0, 0, "얼룩이2", "젖소"))
        adapter.items.add(CatList(0, 1, 0, 0, 1, "삼색이2", "카오스"))
        adapter.items.add(CatList(3, 1, 0, 1, 1, "까망2", "올블랙"))


        // 검색: 한 글자씩 누를 때마다 해당 아이템 출력
        binding.etCatname.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter?.filter?.filter(p0)
            }
        })

        return view
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
        //intent.putExtra("img", catList.catPic)
        startActivity(intent)
        // finish()
        // dataSet.add(listOf("$i th main", "$i th sub"))
    }
}
