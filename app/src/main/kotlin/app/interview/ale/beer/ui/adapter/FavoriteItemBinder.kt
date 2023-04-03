package app.interview.ale.beer.ui.adapter

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import app.interview.ale.base.ext.showToast
import app.interview.ale.base.ext.toDateTimeLeftFormat
import app.interview.ale.beer.R
import app.interview.ale.beer.domain.entities.Beer
import app.interview.ale.beer.util.extension.loadImage
import app.interview.ale.beer.util.extension.refreshFavoriteNote
import app.interview.ale.beer.util.extension.setFormattedDate
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import mva3.adapter.ItemBinder
import mva3.adapter.ItemViewHolder
import timber.log.Timber


class FavoriteItemBinder(
    val onUpdateClick: (beerId: Int, newNote: String) -> Unit,
    val onDeleteClick: (beerId: Int) -> Unit,
) : ItemBinder<Beer, FavoriteItemBinder.FavoriteViewHolder>() {

    val textHolder: MutableMap<Int, String> = mutableMapOf()

    override fun createViewHolder(parent: ViewGroup) = FavoriteViewHolder(
        inflate(parent, R.layout.favorite_item),
        NoteTextWatcher(),
        BeerCountDownTimer(),
        OnBeerActionListener(),
        OnBeerActionListener(isDelete = true)
    )

    override fun canBindData(item: Any) = item is Beer

    override fun bindViewHolder(holder: FavoriteViewHolder, item: Beer) {
        Timber.d("textHolderData: ${textHolder.entries}")
        holder.apply {
            noteTextWatcher.updateBeerId(item.id)
            beerCountDownTimer.updateCountDownData(item.saleOffTime) { timeLeft -> holder.tvCountDown.text = timeLeft }
            onUpdateBeerListener.updateItemId(item.id)
            onDeleteBeerListener.updateItemId(item.id)
        }
        with(item) {
            holder.apply {
                tvName.text = name
                tvPrice.text = price
                imImage.loadImage(imageUrl)
                etNote.refreshFavoriteNote(note, textHolder[item.id])
                tvSaleOffTime.setFormattedDate(saleOffTime)
            }
        }
    }

    inner class FavoriteViewHolder(
        item: View,
        val noteTextWatcher: NoteTextWatcher,
        val beerCountDownTimer: BeerCountDownTimer,
        val onUpdateBeerListener: OnBeerActionListener,
        val onDeleteBeerListener: OnBeerActionListener,
    ) : ItemViewHolder<Beer>(item) {

        val tvName: TextView = item.findViewById(R.id.tvName)
        val tvPrice: TextView = item.findViewById(R.id.tvPrice)
        val imImage: ImageView = item.findViewById(R.id.ivImage)
        val etNote: TextInputEditText = item.findViewById<TextInputEditText?>(R.id.etNote).apply {
            addTextChangedListener(noteTextWatcher)
        }
        val btnUpdate: MaterialButton = item.findViewById(R.id.btnUpdate)
        val btnDelete: MaterialButton = item.findViewById(R.id.btnDelete)

        val tvSaleOffTime = item.findViewById<MaterialTextView>(R.id.tvSaleOffTime)
        val tvCountDown = item.findViewById<MaterialTextView>(R.id.tvCountdown)

        fun enableListener(isEnable: Boolean = true) {
            if (isEnable) {
                etNote.addTextChangedListener(noteTextWatcher)
                beerCountDownTimer.runTimer()
                btnUpdate.setOnClickListener(onUpdateBeerListener)
                btnDelete.setOnClickListener(onDeleteBeerListener)
            } else {
                etNote.removeTextChangedListener(noteTextWatcher)
                beerCountDownTimer.stopTimer()
                btnUpdate.setOnClickListener(null)
                btnDelete.setOnClickListener(null)
            }
        }
    }

    inner class BeerCountDownTimer {
        private var saleOffTime: Long = 0
        private var countDownAction: (timeLeft: String) -> Unit = { }
        private var countDownTimer: CountDownTimer? = null

        fun updateCountDownData(saleOffTime: Long, action: (timeLeft: String) -> Unit) {
            this.saleOffTime = saleOffTime
            this.countDownAction = action
        }

        fun runTimer() {
            countDownTimer = object : CountDownTimer(saleOffTime - System.currentTimeMillis(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    countDownAction.invoke((millisUntilFinished / 1000).toDateTimeLeftFormat() + " to go.")
                }

                override fun onFinish() {
                    countDownAction.invoke("Sale Off")
                }
            }.start()
        }

        fun stopTimer() = countDownTimer?.cancel()
    }

    inner class NoteTextWatcher : TextWatcher {
        private var beerId = 0
        fun updateBeerId(id: Int) {
            this.beerId = id
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
        override fun afterTextChanged(editable: Editable) {}

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            Timber.d("Update '$charSequence' for position: $beerId")
            textHolder[beerId] = charSequence.toString()
        }

    }

    inner class OnBeerActionListener(private val isDelete: Boolean = false) : View.OnClickListener {
        private var itemId = -1
        fun updateItemId(id: Int) {
            itemId = id
        }

        override fun onClick(view: View) {
            if (view !is MaterialButton) return
            when (isDelete) {
                true -> {
                    onDeleteClick(itemId)
                    textHolder.remove(itemId)
                }
                false -> {
                    textHolder[itemId]?.let { onUpdateClick(itemId, it) } ?: run {
                        view.context.showToast("Type somethings before Update")
                    }
                }
            }

        }

    }

}