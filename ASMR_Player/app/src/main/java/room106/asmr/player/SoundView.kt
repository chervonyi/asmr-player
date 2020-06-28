package room106.asmr.player

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.*


class SoundView: LinearLayout {

    //region Constructors
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion

    // Views
    private lateinit var mIconView: ImageButton
    private lateinit var mTitleView: TextView
    private lateinit var mPlayButton: ImageButton
    private lateinit var mControlPanel: LinearLayout
    private lateinit var mVolumeSeekBar: SeekBar
    private lateinit var mStereoSeekBar: SeekBar
    private lateinit var mSwitchDynamicStereo: Switch

    private var controlPanelIsVisible = false
    private var controlPanelMeasuredHeight = 0

    // States
    private var isFree = false
    private var isPlaying = false

    constructor(context: Context?,
                title: String,
                isFree: Boolean): super(context) {

        View.inflate(context, R.layout.sound_layout, this)

        // Connect views
        mIconView = findViewById(R.id.soundIcon)
        mTitleView = findViewById(R.id.soundTitle)
        mControlPanel = findViewById(R.id.controlPanel)
        mPlayButton = findViewById(R.id.buttonPlay)
        mVolumeSeekBar = findViewById(R.id.seekBarVolume)
        mStereoSeekBar = findViewById(R.id.seekBarStereo)
        mSwitchDynamicStereo = findViewById(R.id.switchDynamicStereo)

        // Icon
        mIconView.setOnClickListener(onClickIconListener)

        // Title
        mTitleView.text = title

        // Play Button
        this.isFree = isFree
        mPlayButton.setOnClickListener(onClickPlayButton)

        if (isFree) {
            mPlayButton.setImageResource(R.drawable.ic_play)
        } else {
            mPlayButton.setImageResource(R.drawable.ic_lock)
        }

        // Control panel
        mControlPanel.visibility = View.INVISIBLE
        mControlPanel.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        controlPanelMeasuredHeight = mControlPanel.measuredHeight
        mVolumeSeekBar.setOnSeekBarChangeListener(onVolumeSeekBarChangeListener)
        mStereoSeekBar.setOnSeekBarChangeListener(onStereoSeekBarChangeListener)
        mSwitchDynamicStereo.setOnCheckedChangeListener(onDynamicStereoSwitchChange)
    }

    private val onClickIconListener = OnClickListener {
        controlPanelIsVisible = !controlPanelIsVisible

        if (controlPanelIsVisible) {
            // Show panel
            mControlPanel.visibility = View.VISIBLE

            val valueAnimator =
                ValueAnimator.ofInt(0, controlPanelMeasuredHeight)

            valueAnimator.addUpdateListener {
                val animatedValue = valueAnimator.animatedValue as Int
                val layoutParams = mControlPanel.layoutParams
                layoutParams.height = animatedValue
                mControlPanel.layoutParams = layoutParams
            }

            valueAnimator.duration = 300
            valueAnimator.start()

        } else {
            // Hide panel
            val valueAnimator =
                ValueAnimator.ofInt(controlPanelMeasuredHeight, 0)

            valueAnimator.addUpdateListener {
                val animatedValue = valueAnimator.animatedValue as Int
                val layoutParams = mControlPanel.layoutParams
                layoutParams.height = animatedValue
                mControlPanel.layoutParams = layoutParams
            }

            valueAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mControlPanel.visibility = View.INVISIBLE
                }
            })

            valueAnimator.duration = 300
            valueAnimator.start()
        }
    }

    private val onClickPlayButton = OnClickListener {
        // TODO - Add - "if (isFree || isProVersion)"
        if (isFree) {
            isPlaying = !isPlaying

            if (isPlaying) {
                mPlayButton.setImageResource(R.drawable.ic_pause)
            } else {
                mPlayButton.setImageResource(R.drawable.ic_play)
            }
        }
    }

    private val onVolumeSeekBarChangeListener = object: SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(p0: SeekBar?) { }
        override fun onStopTrackingTouch(p0: SeekBar?) { }
        override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
            // TODO - Implement
            Log.d(TAG,"Volume changed: $progress")
        }
    }

    private val onStereoSeekBarChangeListener = object: SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(p0: SeekBar?) { }
        override fun onStopTrackingTouch(p0: SeekBar?) { }
        override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
            // TODO - Implement
            Log.d(TAG,"Stereo changed: $progress")
        }
    }

    private val onDynamicStereoSwitchChange = CompoundButton.OnCheckedChangeListener {
            compoundButton: CompoundButton,
            isChecked: Boolean ->
        // TODO - Implement
        Log.d(TAG, "Switch changed: $isChecked")
    }

    companion object {
        const val TAG = "SoundView"
    }

}