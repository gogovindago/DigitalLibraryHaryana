package dhe.digital.library.haryana.utility

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    lateinit var baseActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseActivity = activity as BaseActivity
        init(baseActivity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initListeners()
    }

    abstract fun init(baseActivity: BaseActivity);

    abstract fun initData()

    open fun initListeners() {

    }

    open fun onBackPressed() {
        baseActivity.onBackPressed()
    }
}