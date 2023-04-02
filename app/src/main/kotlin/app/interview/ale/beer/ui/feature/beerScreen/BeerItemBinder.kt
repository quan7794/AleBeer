package app.interview.ale.beer.ui.feature.beerScreen

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.interview.ale.base.ext.enableEdit
import app.interview.ale.base.ext.enableView
import app.interview.ale.base.ext.gone
import app.interview.ale.beer.R
import app.interview.ale.beer.domain.entities.Beer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.HttpException
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import mva3.adapter.ItemBinder
import mva3.adapter.ItemViewHolder


class BeerItemBinder(val onSaveClick: (position: Int, note: String) -> Unit) : ItemBinder<Beer, BeerItemBinder.BeerViewHolder>() {
    override fun createViewHolder(parent: ViewGroup) = BeerViewHolder(inflate(parent, R.layout.beer_item))

    override fun canBindData(item: Any) = item is Beer

    override fun bindViewHolder(holder: BeerViewHolder, item: Beer) {
        with(item) {
            holder.apply {
                tvName.text = name
                tvPrice.text = price
                imImage.loadImage(imageUrl)
                if (note.isNotEmpty()) {
                    etNote.apply {
                        setText(note)
                        hint = ""
                        enableEdit(false)
                        etNoteLayout.hint = ""
                    }
                    btnSave.gone()
                }
            }
        }
    }

    inner class BeerViewHolder(private val item: View) : ItemViewHolder<Beer>(item) {
        var tvName: TextView = item.findViewById(R.id.tvName)
        var tvPrice: TextView = item.findViewById(R.id.tvPrice)
        var imImage: ImageView = item.findViewById(R.id.ivImage)
        var etNote: TextInputEditText = item.findViewById(R.id.etNote)
        var etNoteLayout: TextInputLayout = item.findViewById(R.id.etNoteLayout)
        var btnSave: MaterialButton = item.findViewById<MaterialButton?>(R.id.btnSave).apply {
            setOnClickListener {
                if (etNote.length() != 0) {
                    text = resources.getText(R.string.saving)
                    etNote.enableEdit(false)
                    onSaveClick(absoluteAdapterPosition, etNote.text.toString())
                }
            }
        }

        fun ImageView.loadImage(imageUrl: String) {
            val requestBuilder = Glide.with(item.context)
                .asDrawable()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.image_place_holder)
                .load(imageUrl)
            requestBuilder.preload()
            requestBuilder.into(this)
        }
    }
}
