package com.android.basic_recyclerview

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.basic_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 데이터 원본
        val dataList = mutableListOf(
            DogItems.MyTitle("1"),
            DogItems.MyItem(R.drawable.dog1,"Duke","1"),
            DogItems.MyItem(R.drawable.dog2,"Luna","1"),
            DogItems.MyItem(R.drawable.dog3,"Merry","1"),
            DogItems.MyItem(R.drawable.dog4,"Mung","1"),
            DogItems.MyTitle("2"),
            DogItems.MyItem(R.drawable.dog5,"Charlie","2"),
            DogItems.MyItem(R.drawable.dog6,"Max","2"),
            DogItems.MyItem(R.drawable.dog7,"Bob","2"),
            DogItems.MyItem(R.drawable.dog8,"Sara","2"),
            DogItems.MyItem(R.drawable.dog9,"Ganga","2"),
            DogItems.MyTitle("3"),
            DogItems.MyItem(R.drawable.dog10,"Happy","3"),
            DogItems.MyItem(R.drawable.dog11,"Bella","3"),
            DogItems.MyTitle("4"),
            DogItems.MyItem(R.drawable.dog12,"Daisy","4"),
            DogItems.MyItem(R.drawable.dog13,"Jisoo","4"),
            DogItems.MyItem(R.drawable.dog14,"Ddong","4")
        )

        val adapter = MyAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.recyclerView.addItemDecoration(HeaderItemDecoration(binding.recyclerView) { itemPosition: Int -> dataList[itemPosition] is DogItems.MyTitle })
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,LinearLayout.VERTICAL))

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int){
                val name : String = (dataList[position] as DogItems.MyItem).aName
                Toast.makeText(this@MainActivity, "$name 선택!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}