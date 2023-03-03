package com.example.naversearchtest.bind

import android.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("setOnQueryTextListener")
fun SearchView.setOnQueryTextListener(searchByKeyword : (String?) -> Unit){
    this.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { keyword ->
                searchByKeyword(keyword)
            }
            return false
        }
    })
}