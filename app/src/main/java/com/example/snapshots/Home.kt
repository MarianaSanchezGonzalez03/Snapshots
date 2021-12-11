package com.example.snapshots

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.snapshots.databinding.FragmentHome2Binding
import com.example.snapshots.databinding.ItemSapshotsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {

    private  lateinit var mFirebaseAdapter: FirebaseRecyclerAdapter<Snapshots, SnapshotHolder>
private lateinit var mBinding: FragmentHome2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding_=FragmentHome2Binding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val query= FirebaseDatabase.getInstance().references.child( "snapshots")
        val option= FirebaseRecyclerOptions.Builder<Snapshots>().setQuery(query, Snapshots:: class.java).build()

        mFirebaseAdapter= object : FirebaseRecyclerAdapter<Snapshots>, SnapshotHolder>(options){

            private lateinit var  mContex: Context
            override fun onCreate(savedInstanceState: Bundle?) {
                mContext=parent.context
                val view= LayoutInflater.from(mContex).inflate(R.layout.item_stores, parent, false)
                return SnapshotHolder(view)

            }
            override fun onBindViewHolder(holder: SnapshotHolder, position: Int, model:Snapshots){

                val snapshot=   getItem(position)
                with(holder){
                    setListener(snapshot)
                    binding.tvTitle.text=snapshot.title
                    Glide.with(mContex)
                        .load(snapshot.photoUrl)
                        .diskCacheStrategy(DiskCakeStrategy.ALL)
                        .centerCrop()
                        .into(binding.imgPhoto)
                }

            }

            override fun onDataChanged(){
                super.onDataChanged()
                mBinding.progressBar.visibility= View.GONE
            }
            override fun onError(error: DatabaseError){
                super.onError(error)
               Toast.makeText(mContex, error.message, Toast.LENGTH_LONG).show()
            }
        }
    }
inner class SnapshotHolder(view: View): RecyclerView.ViewHolder(view){
    val binding=ItemSapshotsBinding.bind(view)
    fun setListener(Snapshots){

    }
}
}