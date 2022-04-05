package diken.nyarticles.ui.main.articledetails.photosviewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import diken.nyarticles.network.response.viewedarticleresponse.Media

class PhotosViewPagerAdapter(
    fragment: Fragment,
    private val mediaList: List<Media>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return mediaList.flatMap { it.mediaMetadata }.size
    }

    override fun createFragment(position: Int): Fragment {
        return PhotoFragment.newInstance(
            mediaList.flatMap { media -> media.mediaMetadata.map { it.url } }[position]
        )
    }
}

