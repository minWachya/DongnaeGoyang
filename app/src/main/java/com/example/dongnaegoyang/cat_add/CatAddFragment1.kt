package com.example.dongnaegoyang.cat_add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.dongnaegoyang.R
import com.example.dongnaegoyang.databinding.FragmentCatAdd1Binding

private const val TAG = "mmmCatAddFragment1"

private var _binding: FragmentCatAdd1Binding? = null
private val binding get() = _binding!!

// 고양이 추가: 1단계 프레그먼드
class CatAddFragment1 : Fragment() {
    // 몸집 이미지 배열: 마름, 보통, 뚱뚱
    private val arrImgSize = listOf(R.drawable.ic_size_normal, R.drawable.ic_size_normal, R.drawable.ic_size_normal)
    // 코숏 이미지 배열
    private val arrImgFur = listOf(
        // 마름 SKINNY: 코숏들...
        listOf(R.drawable.ic_fur_cheese, R.drawable.ic_fur_black, R.drawable.ic_fur_mackerel,
            R.drawable.ic_fur_three_colors, R.drawable.ic_fur_tuxedo, R.drawable.ic_fur_chaos, R.drawable.ic_fur_spot),
        // 보통 NORMAL: 코숏들...
        listOf(R.drawable.ic_fur_cheese, R.drawable.ic_fur_black, R.drawable.ic_fur_mackerel,
            R.drawable.ic_fur_three_colors, R.drawable.ic_fur_tuxedo, R.drawable.ic_fur_chaos, R.drawable.ic_fur_spot),
        // 뚱뚱 FAT: 코숏들...
        listOf(R.drawable.ic_fur_cheese, R.drawable.ic_fur_black, R.drawable.ic_fur_mackerel,
            R.drawable.ic_fur_three_colors, R.drawable.ic_fur_tuxedo, R.drawable.ic_fur_chaos, R.drawable.ic_fur_spot)
    )
    // 귀 이미지 배열
    private val arrImgEar = listOf(
        // 치즈 CHEESE: open, fold, tnr
        listOf(R.drawable.ic_ear_open_cheese, R.drawable.ic_ear_fold_cheese, R.drawable.ic_ear_tnr_cheese),
        // 올블랙 BLACK: open, fold, tnr
        listOf(R.drawable.ic_ear_open_black, R.drawable.ic_ear_fold_black, R.drawable.ic_ear_tnr_black),
        // 고등어 MACKEREL: open, fold, tnr
        listOf(R.drawable.ic_ear_open_mackerel, R.drawable.ic_ear_fold_mackerel, R.drawable.ic_ear_tnr_mackerel),
        // 삼색 THREE_COLORS: open, fold, tnr
        listOf(R.drawable.ic_ear_open_three_colors, R.drawable.ic_ear_fold_three_colors, R.drawable.ic_ear_tnr_three_colors),
        // 턱시도 TUXEDO: open, fold, tnr
        listOf(R.drawable.ic_ear_open_tuxedo, R.drawable.ic_ear_fold_tuxedo, R.drawable.ic_ear_tnr_tuxedo),
        // 카오스 CHAOS: open, fold, tnr
        listOf(R.drawable.ic_ear_open_chaos, R.drawable.ic_ear_fold_chaos, R.drawable.ic_ear_tnr_chaos),
        // 얼룩 SPOT: open, fold, tnr
        listOf(R.drawable.ic_ear_open_spot, R.drawable.ic_ear_fold_spot, R.drawable.ic_ear_tnr_spot)
    )
    // 꼬리 이미지 배열
    private val arrImgTail = listOf(
        // 치즈 CHEESE: long, short
        listOf(R.drawable.ic_tail_long_cheese, R.drawable.ic_tail_short_cheese),
        // 올블랙 BLACK: long, short
        listOf(R.drawable.ic_tail_long_black, R.drawable.ic_tail_short_black),
        // 고등어 MACKEREL: long, short
        listOf(R.drawable.ic_tail_long_mackerel, R.drawable.ic_tail_short_mackerel),
        // 삼색 THREE_COLORS: long, short
        listOf(R.drawable.ic_tail_long_three_colors, R.drawable.ic_tail_short_three_colors),
        // 턱시도 TUXEDO: long, short
        listOf(R.drawable.ic_tail_long_tuxedo, R.drawable.ic_tail_short_tuxedo),
        // 카오스 CHAOS: long, short
        listOf(R.drawable.ic_tail_long_chaos, R.drawable.ic_tail_short_chaos),
        // 얼룩 SPOT: long, short
        listOf(R.drawable.ic_tail_long_spot, R.drawable.ic_tail_short_spot)
    )
    // 수염 이미지 배열
    private val arrImgWhisker = listOf(
        listOf(R.drawable.ic_whisker_short, R.drawable.ic_whisker_long),    // 검은 수염
        listOf(R.drawable.ic_whisker_short_w, R.drawable.ic_whisker_long_w) // 흰 수염
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatAdd1Binding.inflate(inflater, container, false)
        val view = binding.root
        val spinnerCatSize = binding.spinnerCatSize
        val spinnerCatFur = binding.spinnerCatFur
        val spinnerCatEar = binding.spinnerCatEar
        val spinnerCatTail = binding.spinnerCatTail
        val spinnerCatWhisker = binding.spinnerCatWhiskers

        // 몸집 선택: 털 색 번경
        spinnerCatSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
                binding.imgCatSize.setImageResource(arrImgSize[position])
        }
        setBtnListener(spinnerCatSize.adapter.count-1, binding.imgViewSizeLeft, binding.imgViewSizeRight, spinnerCatSize)
        // 코숏 선택: 귀, 꼬리, 수염 색 변경
        spinnerCatFur.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.imgCatFur.setImageResource(arrImgFur[spinnerCatSize.selectedItemPosition][position])    // 코숏
                binding.imgCatEar.setImageResource(arrImgEar[position][spinnerCatEar.selectedItemPosition])     // 귀
                binding.imgCatTail.setImageResource(arrImgTail[position][spinnerCatTail.selectedItemPosition])  // 꼬리
                if (position == 1 || position == 5)
                    binding.imgCatWhisker.setImageResource(arrImgWhisker[1][spinnerCatWhisker.selectedItemPosition])  // 흰 수염
                else binding.imgCatWhisker.setImageResource(arrImgWhisker[0][spinnerCatWhisker.selectedItemPosition]) // 검은 수염
            }
        }
        setBtnListener(spinnerCatFur.adapter.count-1, binding.imgViewFurLeft, binding.imgViewFurRight, spinnerCatFur)
        // 귀 모양 선택: 털 색에 맞게 귀 모양 변경
        spinnerCatEar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
                binding.imgCatEar.setImageResource(arrImgEar[spinnerCatFur.selectedItemPosition][position])
        }
        setBtnListener(spinnerCatEar.adapter.count-1, binding.imgViewEarLeft, binding.imgViewEarRight, spinnerCatEar)
        // 꼬리 모양 선택: 털 색에 맞게 꼬리 모양 변경
        spinnerCatTail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) =
                binding.imgCatTail.setImageResource(arrImgTail[spinnerCatFur.selectedItemPosition][position])
        }
        setBtnListener(spinnerCatTail.adapter.count-1, binding.imgViewTailLeft, binding.imgViewTailRight, spinnerCatTail)
        // 수염 선택
        spinnerCatWhisker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (spinnerCatFur.selectedItemPosition == 1 || spinnerCatFur.selectedItemPosition == 5)
                    binding.imgCatWhisker.setImageResource(arrImgWhisker[1][position])  // 흰 수염
                else binding.imgCatWhisker.setImageResource(arrImgWhisker[0][position]) // 검은 수염
            }
        }
        setBtnListener(spinnerCatWhisker.adapter.count-1, binding.imgViewWhiskersLeft, binding.imgViewWhiskersRight, spinnerCatWhisker)

        val bundle1 = arguments?: Bundle()
        // 이전 선택 정보 보여주기
        setPrevInfo(bundle1)

        // 2단계로 이동
        binding.btnOK1.setOnClickListener {
            setFrag(CatAddFragment2(), bundle1)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 고양이 생김새 설정 버튼 리스너 달기
    private fun setBtnListener(max: Int, imageViewLeft: ImageView, imageViewRight: ImageView, spinner: Spinner) {
        var num = 0
        imageViewLeft.setOnClickListener {
            num--
            if (num < 0) num = max
            spinner.setSelection(num)
        }
        imageViewRight.setOnClickListener {
            num++
            if (num > max) num = 0
            spinner.setSelection(num)
        }
    }

    // 이전 정보 보여주기
    private fun setPrevInfo(bundle: Bundle?) {
        if (bundle?.getInt("size") != null) {
            val numSize = bundle.getInt("size") // 몸집
            val numFur = bundle.getInt("fur")   // 코숏
            val numEar = bundle.getInt("ear")   // 귀 모양
            val numTail = bundle.getInt("tail") // 꼬리 모양
            val numWhiskers = bundle.getInt("whiskers")  // 수염
            binding.spinnerCatSize.setSelection(numSize)
            binding.spinnerCatFur.setSelection(numFur)
            binding.spinnerCatEar.setSelection(numEar)
            binding.spinnerCatTail.setSelection(numTail)
            binding.spinnerCatWhiskers.setSelection(numWhiskers)
        }
    }

    // 프레그먼트 이동
    private fun setFrag(catAddFragment: Fragment, bundle: Bundle?) {
        // 1단계 정보 함께 보내기
        if (bundle != null) {
            val numSize = binding.spinnerCatSize.selectedItemPosition       // 몸집
            val numFur = binding.spinnerCatFur.selectedItemPosition         // 코숏
            val numEar = binding.spinnerCatEar.selectedItemPosition         // 귀 모양
            val numTail = binding.spinnerCatTail.selectedItemPosition       // 꼬리 모양
            val numWhiskers = binding.spinnerCatWhiskers.selectedItemPosition   // 수염
            bundle.putInt("size", numSize)  // 몸집
            bundle.putInt("fur", numFur)    // 코숏
            bundle.putInt("ear", numEar)    // 귀 모양
            bundle.putInt("tail", numTail)  // 꼬리 모양
            bundle.putInt("whiskers", numWhiskers)    // 수염
            catAddFragment.arguments = bundle
        }
        // 프레그먼트에 정보 전달 + 이동
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.catAddFrameLayout, catAddFragment).commit()
    }

}