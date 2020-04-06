package com.itaycohen.interviewnews.ui_layer.articles.screen_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.itaycohen.interviewnews.R
import com.itaycohen.interviewnews.data_layer.articles.models.TopHeadlinesModel
import com.itaycohen.interviewnews.ui_layer.utils.DateUtil
import kotlinx.android.synthetic.main.article_list_item.view.*

class ArticleListAdapter(
) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    var headlines: TopHeadlinesModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = headlines?.articlesList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val headline = headlines!!.articlesList[position]
        holder.itemView.apply{
            Glide.with(imageView)
                .load(headline.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.image_icon)
                .error(R.drawable.broken_image_icon)
                .into(imageView)
            titleTextView.text = headline.title
            timestampTextView.text = DateUtil.format(headline.publishedAt)
            descriptionTextView.text = headline.description
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val title = itemView.titleTextView.text.toString()
                itemView.findNavController().navigate(ArticleListFragmentDirections.nextDestinationAction())
            }
        }
    }
}
