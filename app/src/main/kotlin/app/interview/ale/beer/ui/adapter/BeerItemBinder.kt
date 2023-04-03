package app.interview.ale.beer.ui.adapter

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import app.interview.ale.base.ext.enableEdit
import app.interview.ale.base.ext.showToast
import app.interview.ale.base.ext.toDateTimeLeftFormat
import app.interview.ale.beer.R
import app.interview.ale.beer.domain.entities.Beer
import app.interview.ale.beer.util.extension.loadImage
import app.interview.ale.beer.util.extension.refreshBeerNote
import app.interview.ale.beer.util.extension.setFormattedDate
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import mva3.adapter.ItemBinder
import mva3.adapter.ItemViewHolder
import timber.log.Timber


class BeerItemBinder(val onSaveClick: (position: Int, note: String) -> Unit) : ItemBinder<Beer, BeerItemBinder.BeerViewHolder>() {
    private val textHolder: MutableMap<Int, String> = mutableMapOf()

    override fun createViewHolder(parent: ViewGroup) = BeerViewHolder(inflate(parent, R.layout.beer_item), NoteTextWatcher(), BeerCountDownTimer())
    override fun canBindData(item: Any) = item is Beer
    override fun bindViewHolder(holder: BeerViewHolder, item: Beer) {
        Timber.d("textHolderData: ${textHolder.entries}")
        holder.noteTextWatcher.updatePosition(holder.bindingAdapterPosition)
        holder.beerCountDownTimer.updateCountDownData(item.saleOffTime) { timeLeft -> holder.tvCountDown.text = timeLeft }
        textHolder[holder.bindingAdapterPosition]?.let { holder.etNote.setText(it) }
        with(item) {
            holder.apply {
                tvName.text = name
                tvPrice.text = price
                imImage.loadImage(imageUrl)
                etNote.refreshBeerNote(note, textHolder[holder.bindingAdapterPosition])
                btnSave.refreshBeerNote(note)
                tvSaleOffTime.setFormattedDate(saleOffTime)
            }
        }
    }

    inner class BeerViewHolder(item: View, val noteTextWatcher: NoteTextWatcher, val beerCountDownTimer: BeerCountDownTimer) : ItemViewHolder<Beer>(item) {
        val tvName: TextView = item.findViewById(R.id.tvName)
        val tvPrice: TextView = item.findViewById(R.id.tvPrice)
        val imImage: ImageView = item.findViewById(R.id.ivImage)
        val etNote: TextInputEditText = item.findViewById<TextInputEditText?>(R.id.etNote).apply { addTextChangedListener(noteTextWatcher) }
        val btnSave: MaterialButton = item.findViewById<MaterialButton?>(R.id.btnSave).apply {
            setOnClickListener {
                if (etNote.length() != 0) {
                    text = resources.getText(R.string.saving)
                    etNote.enableEdit(false)
                    onSaveClick(bindingAdapterPosition, etNote.text.toString())
                } else context.showToast("Type somethings before Save")
            }
        }
        val tvSaleOffTime: MaterialTextView = item.findViewById(R.id.tvSaleOffTime)
        val tvCountDown: MaterialTextView = item.findViewById(R.id.tvCountdown)

        fun enableListener(isEnable: Boolean = true) {
            if (isEnable) {
                etNote.addTextChangedListener(noteTextWatcher)
                beerCountDownTimer.runTimer()
            } else {
                etNote.removeTextChangedListener(noteTextWatcher)
                beerCountDownTimer.stopTimer()
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

        fun stopTimer() {
            countDownTimer?.cancel()
            countDownTimer = null
        }
    }

    inner class NoteTextWatcher : TextWatcher {
        private var beforeText: String = ""
        private var position = 0
        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            beforeText = charSequence.toString()
        }

        override fun afterTextChanged(editable: Editable) {}

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            if (charSequence.toString() == beforeText) return
            Timber.d("Update '$charSequence' for position: $position")
            textHolder[position] = charSequence.toString()
        }
    }
}