package diken.nyarticles.ui.main.articledetails.photosviewpager

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import diken.nyarticles.R

private const val PHOTOS_URL = "Photo Url"

class PhotoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ImageView(requireContext()).apply {
            arguments?.let {
                Glide.with(requireContext())
                    .load(it.getString(PHOTOS_URL))
                    .placeholder(getGrayDrawable())
                    .error(getGrayDrawable())
                    .into(this)
            }
        }
    }


    private fun getGrayDrawable(): ColorDrawable {
        return ColorDrawable(
            ContextCompat.getColor(
                requireContext(),
                R.color.gray_300
            )
        )
    }


    companion object {
        fun newInstance(photoUrl: String): PhotoFragment {
            return PhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(PHOTOS_URL, photoUrl)
                }
            }
        }
    }
}